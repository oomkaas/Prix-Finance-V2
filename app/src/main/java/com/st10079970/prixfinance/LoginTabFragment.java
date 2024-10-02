package com.st10079970.prixfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.st10079970.prixfinance.Api.Models.User;
import com.st10079970.prixfinance.Api.RetrofitClient;
import com.st10079970.prixfinance.Api.Services.UsersApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragment extends Fragment {

    EditText email, password;
    TextView forgotPass, orLoginWith;
    Button login, loginBiometrics;
    float v = 0;
    public static String userGuid; // Global variable to store user GUID

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.txtEmail);
        password = root.findViewById(R.id.txtPassword);
        forgotPass = root.findViewById(R.id.forgotPass);
        login = root.findViewById(R.id.btnLogin);
        loginBiometrics = root.findViewById(R.id.btnLoginBiometrics);
        orLoginWith = root.findViewById(R.id.lblOrLoginWith);

        // Animations (as before)
        setUpAnimations();

        login.setOnClickListener(v -> loginUser());

        return root;
    }

    private void setUpAnimations() {
        email.setTranslationX(800);
        password.setTranslationX(800);
        forgotPass.setTranslationX(800);
        login.setTranslationX(800);
        loginBiometrics.setTranslationX(800);
        orLoginWith.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgotPass.setAlpha(v);
        login.setAlpha(v);
        loginBiometrics.setAlpha(v);
        orLoginWith.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        forgotPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        loginBiometrics.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        orLoginWith.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
    }

    private void loginUser() {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        // Input validation
        if (emailText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        UsersApiService usersApiService = RetrofitClient.usersApiService;
        Call<List<User>> call = usersApiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    boolean validCredentials = false;
                    for (User user : users) {
                        // Check if the email matches
                        if (user.getEmail().equals(emailText)) {
                            // Verify the password
                            if (passwordText.equals(user.getPasswordHash())) {
                                userGuid = user.getUserId().toString();
                                validCredentials = true;
                                break;
                            }
                        }
                    }

                    if (validCredentials) {
                        // Ensure getActivity() is not null
                        if (getActivity() != null) {
                            Intent intent = new Intent(getActivity(), Dashboard.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Failed to retrieve users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}