package com.st10079970.prixfinance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.st10079970.prixfinance.Api.Models.User;
import com.st10079970.prixfinance.Api.RetrofitClient;
import com.st10079970.prixfinance.Api.Services.UsersApiService;
import com.st10079970.prixfinance.Api.Services.UserCreateDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterTabFragment extends Fragment {

    private EditText email, password, confirmPassword;
    private Button register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.registration_tab_fragment, container, false);

        // Initialize EditText fields using findViewById
        email = root.findViewById(R.id.txtEmail2);  // Ensure IDs match XML layout
        password = root.findViewById(R.id.editText);
        confirmPassword = root.findViewById(R.id.txtConfirmPassword);
        register = root.findViewById(R.id.btnRegister);

        // Set OnClickListener for register button
        register.setOnClickListener(v -> registerUser());

        return root;
    }

    private void registerUser() {
        // Get text input from EditText fields
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String confirmPasswordText = confirmPassword.getText().toString().trim();

        // Input validation
        if (emailText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordText.equals(confirmPasswordText)) {
            Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new user DTO for API request
        UserCreateDto newUser = new UserCreateDto(emailText, passwordText, null);
        UsersApiService usersApiService = RetrofitClient.usersApiService;
        Call<User> call = usersApiService.createUser(newUser);

        // Make asynchronous API request to register user
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
                    // Navigate to MainActivity
                    try {
                        Intent intent = new Intent(requireActivity(), MainActivity.class);
                        startActivity(intent);
                        requireActivity().finish(); // Optional: close the current activity
                    } catch (Exception e) {
                        Log.e("RegisterTabFragment", "Error during navigation to MainActivity", e);
                        Toast.makeText(requireContext(), "Navigation error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Registration failed. Please enter valid data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}