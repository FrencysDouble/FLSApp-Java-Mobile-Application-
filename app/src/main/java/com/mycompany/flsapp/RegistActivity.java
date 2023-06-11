package com.mycompany.flsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mycompany.flsapp.Model.User;

import java.util.Objects;

public class RegistActivity extends AppCompatActivity {
    protected FirebaseAuth mAuth;
    protected FirebaseDatabase db;
    protected DatabaseReference users;
    protected EditText logTxt,emailTxt,passTxt;
    protected Intent next;
    protected View root;

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

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        next = new Intent(this, MainMenuActivity.class);
    }


    public void createAccount(View view) {
        if(TextUtils.isEmpty(emailTxt.getText().toString()))
        {
            Snackbar.make(root, "Enter your email",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(logTxt.getText().toString()))
        {
            Snackbar.make(root, "Enter your login",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(passTxt.getText().toString().length() < 5)
        {
            Snackbar.make(root, "Password is less then 5 symbols",Snackbar.LENGTH_SHORT).show();
            return;
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(emailTxt.getText().toString(),passTxt.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            User user = new User();
                            user.setName(logTxt.getText().toString());
                            user.setEmail(emailTxt.getText().toString());
                            user.setPassword(passTxt.getText().toString());
                            users.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user);
                            Snackbar.make(root,"Account is created",Snackbar.LENGTH_SHORT).show();
                            startActivity(next);
                        }
                    });
        }
    }

}