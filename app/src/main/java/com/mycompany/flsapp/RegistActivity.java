package com.mycompany.flsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.mycompany.flsapp.ViewModel.RegistViewModel;

public class RegistActivity extends AppCompatActivity {
    protected EditText logTxt,emailTxt,passTxt;
    protected Intent next;
    protected View root;
    private RegistViewModel registViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();
    }

    private void init()
    {
        logTxt = findViewById(R.id.loginTxt_on);
        emailTxt = findViewById(R.id.emailTxt_on);
        passTxt = findViewById(R.id.passTxt_on);
        root = findViewById(R.id.log_site_up);

        next = new Intent(this, MainMenuActivity.class);
    }


    public void createAccount(View view) {
        if (TextUtils.isEmpty(emailTxt.getText().toString())) {
            Snackbar.make(root, "Enter your email", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(logTxt.getText().toString())) {
            Snackbar.make(root, "Enter your login", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (passTxt.getText().toString().length() < 5) {
            Snackbar.make(root, "Password is less then 5 symbols", Snackbar.LENGTH_SHORT).show();
            return;
        } else {
            String email = emailTxt.getText().toString();
            String password = passTxt.getText().toString();
            String username = logTxt.getText().toString();
            registViewModel.createUser(email, password, username).observe(this, registrationSuccess -> {
                if (registrationSuccess) {
                    Snackbar.make(root, "Account is created", Snackbar.LENGTH_SHORT).show();
                    startActivity(next);
                } else {
                    Snackbar.make(root, "Failed to create account", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

}