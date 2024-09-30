package com.st10079970.prixfinance;

import android.content.Intent;
import android.os.Bundle;
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

    EditText email, password, confirmPassword;
    Button register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.registration_tab_fragment, container, false);

        email = root.findViewById(R.id.txtEmail);
        password = root.findViewById(R.id.txtPassword);
        confirmPassword = root.findViewById(R.id.txtConfirmPassword);
        register = root.findViewById(R.id.btnRegister);

        register.setOnClickListener(v -> registerUser());

        return root;
    }

    private void registerUser() {
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

        UserCreateDto newUser = new UserCreateDto(emailText, passwordText, null);
        UsersApiService usersApiService = RetrofitClient.usersApiService;
        Call<User> call = usersApiService.createUser(newUser);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
                    // Redirect to Dashboard
                    Intent intent = new Intent(getActivity(), Dashboard.class);
                    startActivity(intent);
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