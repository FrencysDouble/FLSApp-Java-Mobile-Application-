package com.mycompany.flsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    protected FirebaseAuth mAuth;
    protected FirebaseDatabase db;
    protected DatabaseReference users;
    protected EditText emailTxt,passTxt;
    Intent next;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init()
    {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        emailTxt = findViewById(R.id.emailTxt_in);
        passTxt = findViewById(R.id.passTxt_in);

        next = new Intent(this, MainMenuActivity.class);

        root = findViewById(R.id.log_site_in);
    }


    public void loginAccount(View view) {
        if(TextUtils.isEmpty(emailTxt.getText().toString()))
        {
            Snackbar.make(root, "Enter your email",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(passTxt.getText().toString().length() < 5)
        {
            Snackbar.make(root, "Password is less then 5 symbols",Snackbar.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailTxt.getText().toString(),passTxt.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(next);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(root,"Wrong email or password",Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}