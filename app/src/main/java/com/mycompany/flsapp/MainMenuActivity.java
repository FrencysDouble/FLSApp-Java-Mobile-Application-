package com.mycompany.flsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mycompany.flsapp.Fragments.Fragment_to;

public class MainMenuActivity extends AppCompatActivity {
    EditText ed_to;
    Fragment_to fragment_to = new Fragment_to();
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
        fragment_to.show(getSupportFragmentManager(), "dialogFragment");
    }
}