package com.mycompany.flsapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mycompany.flsapp.Model.DataMerging;
import com.mycompany.flsapp.Adapters.AirportAdapter;
import com.mycompany.flsapp.Interfaces.ItemClickListener;
import com.mycompany.flsapp.R;
import com.mycompany.flsapp.ViewModel.FragmentsViewModel.CitAndCountSelViewModel;

import java.io.IOException;
import java.util.ArrayList;

public class CitiesAndCountSel extends BottomSheetDialogFragment implements ItemClickListener {
    AirportAdapter airportAdapter;
    SearchView searchView;
    CitAndCountSelViewModel citiesAndCountSelVM;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.getBehavior().setPeekHeight(1800);
        return dialog;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.bottom_sheet_fragment, container, false);
        citiesAndCountSelVM = new ViewModelProvider(this).get(CitAndCountSelViewModel.class);

        RecyclerView airportListRec = view.findViewById(R.id.airportList);
        searchView = view.findViewById(R.id.searchView);
        searchView.setFocusable(false);
        searchView.setIconified(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        airportListRec.setLayoutManager(layoutManager);

        airportAdapter = new AirportAdapter(new ArrayList<>(), this);
        airportListRec.setAdapter(airportAdapter);


        citiesAndCountSelVM.getDataListLiveData().observe(this, newDataList -> {
            airportAdapter.SetData(newDataList);
        });
        try {
            citiesAndCountSelVM.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String Text) {
                airportAdapter.filterData(Text);
                return true;
            }
        });
        airportListRec.setAdapter(airportAdapter);
        return view;
    }


    @Override
    public void onItemClick(DataMerging.Data data) {
        if (getActivity() instanceof ItemClickListener) {
            ((ItemClickListener) getActivity()).onItemClick(data);
        }
    }
}
