package com.mycompany.flsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mycompany.flsapp.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getNavigateToRegistration().observe(this,intent -> {
            if(intent != null)
            {
                startActivity(new Intent(this,RegistActivity.class));
            }
        });

        mainViewModel.getNavigateToLogin().observe(this,intent -> {
            if(intent != null)
            {
                startActivity(new Intent(this,LoginActivity.class));
            }
        });

        mainViewModel.getNavigateTest().observe(this,intent -> {
            if(intent != null)
            {
                startActivity(new Intent(this,MainMenuActivity.class));
            }
        });


    }

    public void upBtn(View view)
    {
        mainViewModel.navigateToRegistration();
    }

    public void inBtn(View view)
    {
        mainViewModel.navigateToLogin();
    }

    public void Test(View view) {
        mainViewModel.navigateTest();
    }
}