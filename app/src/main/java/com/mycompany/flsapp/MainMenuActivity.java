package com.mycompany.flsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {
    EditText ed_to,ed_on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }

    private void init()
    {
        ed_to = findViewById(R.id.ed_text_to);
        ed_on = findViewById(R.id.ed_text_on);
    }

    public void openSearch1(View view) {
        Toast.makeText(this,"Sex",Toast.LENGTH_SHORT).show();
    }
}