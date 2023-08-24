package com.mycompany.flsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mycompany.flsapp.Model.DataMerging;
import com.mycompany.flsapp.Fragments.CitiesAndCountSel;
import com.mycompany.flsapp.Fragments.CalendarFragment;
import com.mycompany.flsapp.Interfaces.ItemClickListener;
import com.mycompany.flsapp.ViewModel.MainMenuViewModel;

public class MainMenuActivity extends AppCompatActivity implements ItemClickListener {

    private MainMenuViewModel viewModel;

    CitiesAndCountSel bottomSheetFragment = new CitiesAndCountSel();
    CalendarFragment calendarFragment = new CalendarFragment();

    private TextView txtOut;
    private TextView txtIn;
    private View view;
    private View view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();

        viewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);

        viewModel.getSelectedCityOut().observe(this, selectedCityOut -> {
            if (selectedCityOut != null) {
                txtOut.setText(selectedCityOut);
            }
        });

        viewModel.getSelectedCityIn().observe(this, selectedCityIn -> {
            if (selectedCityIn != null) {
                txtIn.setText(selectedCityIn);
            }
        });
    }

    public void OpenSearch(View view)
    {
        if (this.view.getId() == view.getId()) {
            viewModel.setClickedViewId(view.getId()); // Сохраняем id View на которую кликнули
        }
        else if(this.view2.getId() == view.getId())
        {
            viewModel.setClickedViewId(view.getId());
        }
        bottomSheetFragment.show(getSupportFragmentManager(), "dialogFragment");
    }


    @Override
    public void onItemClick(DataMerging.Data data) {
        bottomSheetFragment.dismiss();
        if (viewModel.getClickedViewId().getValue() != null) {
            if (viewModel.getClickedViewId().getValue() == R.id.view) {
                viewModel.setSelectedCityOut(data.getCityName());
            } else if (viewModel.getClickedViewId().getValue() == R.id.view2) {
                viewModel.setSelectedCityIn(data.getCityName());
            }
        }
    }

    public void OpenCalendar(View view)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, calendarFragment)
                .addToBackStack(null)
                .commit();
    }

    private void init()
    {
        this.txtIn = findViewById(R.id.txtCityIn);
        this.txtOut = findViewById(R.id.txtCityOut);
        this.view = findViewById(R.id.view);
        this.view2 = findViewById(R.id.view2);
    }
}