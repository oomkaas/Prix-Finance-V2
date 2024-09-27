package com.st10079970.prixfinance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    EditText email, password;
    TextView forgotPass, orLoginWith;
    Button login, loginBiometrics;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.txtEmail);
        password = root.findViewById(R.id.txtPassword);
        forgotPass = root.findViewById(R.id.forgotPass);
        login = root.findViewById(R.id.btnLogin);
        loginBiometrics = root.findViewById(R.id.btnLoginBiometrics);
        orLoginWith = root.findViewById(R.id.lblOrLoginWith);



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


        return root;
    }
}
