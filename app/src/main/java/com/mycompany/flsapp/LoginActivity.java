package com.mycompany.flsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.mycompany.flsapp.ViewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    protected EditText emailTxt, passTxt;
    Intent next;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getLoginSuccess().observe(this, loginSuccess -> {
            if (loginSuccess) {
                startActivity(next);
            } else {
                Snackbar.make(root, "Wrong email or password", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        emailTxt = findViewById(R.id.emailTxt_in);
        passTxt = findViewById(R.id.passTxt_in);

        next = new Intent(this, MainMenuActivity.class);

        root = findViewById(R.id.log_site_in);
    }

    public void loginAccount(View view) {
        if (TextUtils.isEmpty(emailTxt.getText().toString())) {
            Snackbar.make(root, "Enter your email", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (passTxt.getText().toString().length() < 5) {
            Snackbar.make(root, "Password is less than 5 symbols", Snackbar.LENGTH_SHORT).show();
            return;
        }

        loginViewModel.login(emailTxt.getText().toString(), passTxt.getText().toString());
    }
}