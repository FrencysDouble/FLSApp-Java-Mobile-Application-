package com.mycompany.flsapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.flsapp.Fragments.BottomSheetFragment;

public class MainMenuActivity extends AppCompatActivity {

    BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void OpenSearch(View view) {
        bottomSheetFragment.show(getSupportFragmentManager(), "dialogFragment");
    }
}