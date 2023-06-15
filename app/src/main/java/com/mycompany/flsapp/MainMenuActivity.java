package com.mycompany.flsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.flsapp.Fragments.BottomSheetFragment;

public class MainMenuActivity extends AppCompatActivity {
    EditText ed_to;
    BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
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
        bottomSheetFragment.show(getSupportFragmentManager(), "dialogFragment");
    }
}