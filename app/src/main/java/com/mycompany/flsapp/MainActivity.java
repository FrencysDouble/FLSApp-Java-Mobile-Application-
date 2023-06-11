package com.mycompany.flsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Intent reg;
    private Intent log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg  = new Intent(this,RegistActivity.class);
        log = new Intent(this,LoginActivity.class);
    }

    public void upBtn(View view)
    {
        startActivity(reg);
    }

    public void inBtn(View view)
    {
        startActivity(log);
    }

    public void Test(View view) {
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
    }
}