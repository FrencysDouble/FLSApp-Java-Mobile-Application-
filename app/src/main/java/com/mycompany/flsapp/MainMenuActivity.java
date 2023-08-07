package com.mycompany.flsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.flsapp.API.DataMerging;
import com.mycompany.flsapp.Fragments.BottomSheetFragment;
import com.mycompany.flsapp.Interfaces.ItemClickListener;

public class MainMenuActivity extends AppCompatActivity implements ItemClickListener {

    BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
    TextView txtOut;
    TextView txtIn;
    View view;
    View view2;
    int clickedViewId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }

    public void init()
    {
        this.txtIn = findViewById(R.id.txtCityIn);
        this.txtOut = findViewById(R.id.txtCityOut);
        this.view = findViewById(R.id.view);
        this.view2 = findViewById(R.id.view2);
    }


    public void OpenSearch(View view) {
        if (this.view.getId() == view.getId()) {
            this.clickedViewId = view.getId(); // Сохраняем id View на которую кликнули
        }
        else if(this.view2.getId() == view.getId())
        {
            this.clickedViewId = view2.getId();
        }
            bottomSheetFragment.show(getSupportFragmentManager(), "dialogFragment");
    }

    @Override
    public void onItemClick(DataMerging.Data data) {
        bottomSheetFragment.dismiss();
        if (clickedViewId == R.id.view) {
            // Клик был на view
            txtOut.setText(data.getCityName());
        } else if (clickedViewId == R.id.view2) {
            // Клик был на view2
            txtIn.setText(data.getCityName());
        }
    }
}