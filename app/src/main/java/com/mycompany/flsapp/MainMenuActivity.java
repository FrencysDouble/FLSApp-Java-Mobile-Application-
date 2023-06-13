package com.mycompany.flsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.flsapp.Fragments.Fragment_to;

public class MainMenuActivity extends AppCompatActivity {
    EditText ed_to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }

    private void init()
    {
        ed_to = findViewById(R.id.ed_text_to);
    }

    public void openSearch1(View view) {
        Fragment_to fragment_to = new Fragment_to();
        fragment_to.show(getSupportFragmentManager(), "dialogFragment");
    }
}