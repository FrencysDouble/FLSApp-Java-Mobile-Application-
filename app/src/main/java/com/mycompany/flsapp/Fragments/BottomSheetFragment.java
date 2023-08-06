package com.mycompany.flsapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mycompany.flsapp.API.APIManager;
import com.mycompany.flsapp.API.DataMerging;
import com.mycompany.flsapp.Adapters.AirportAdapter;
import com.mycompany.flsapp.Interfaces.DataCallback;
import com.mycompany.flsapp.R;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment implements DataCallback {
    AirportAdapter airportAdapter;
    List<DataMerging.Data> dataList;
    public BottomSheetFragment() {
    }


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

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        airportAdapter = new AirportAdapter(new ArrayList());
        APIManager apiManager = new APIManager();
        apiManager.GetCountryAndCitiesData(this);

        RecyclerView airportListRec = view.findViewById(R.id.airportList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        airportListRec.setLayoutManager(layoutManager);
        airportListRec.setAdapter(airportAdapter);

        return view;
    }

    @Override
    public void onCountriesCityDataRecieved(List<DataMerging.Data> dataList) {
        this.dataList = dataList;
        Log.d("BottomSheetFragment", "Received Merging data: " + dataList.toString());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                airportAdapter.SetData(dataList);
            }
        });
    }

    @Override
    public void onCountriesCityDataFailed(Throwable throwable) {
        Log.d("BottomSheetFragment", "API error: " + throwable.getMessage());
    }
}
