package com.mycompany.flsapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.Adapters.CalendarAdapter;
import com.mycompany.flsapp.R;
import com.mycompany.flsapp.ViewModel.FragmentsViewModel.CalendarViewModel;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    CalendarViewModel calendarViewModel = new CalendarViewModel();
    CalendarAdapter calendarAdapter;

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("CalendarFragment","Launch");

       View rootView =  inflater.inflate(R.layout.calendar_fragment, container, false);
       View rootView2 = inflater.inflate(R.layout.calendar_item,container,false);

        RecyclerView.RecycledViewPool sharedViewPool = new RecyclerView.RecycledViewPool();

        RecyclerView monthYearRecyclerView = rootView.findViewById(R.id.month_recyclerV);
        RecyclerView itemDayRV = rootView2.findViewById(R.id.itemDayRV);

        monthYearRecyclerView.setRecycledViewPool(sharedViewPool);
        itemDayRV.setRecycledViewPool(sharedViewPool);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        monthYearRecyclerView.setLayoutManager(layoutManager);
        monthYearRecyclerView.setHasFixedSize(true);

        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);

        calendarAdapter = new CalendarAdapter(new ArrayList<>());
        monthYearRecyclerView.setAdapter(calendarAdapter);

        calendarViewModel.getCalendarList().observe(getViewLifecycleOwner(), newDataList ->{
            calendarAdapter.setDataCalendar(newDataList);
        });

        calendarViewModel.loadData();
        return rootView;
    }

}
