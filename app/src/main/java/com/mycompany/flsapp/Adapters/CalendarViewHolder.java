package com.mycompany.flsapp.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.R;

import java.util.ArrayList;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    private final TextView txt_year;
    private final TextView txt_month;
    private final RecyclerView dayRecyclerView;
    private DayAdapter dayAdapter;

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_year=itemView.findViewById(R.id.txt_year);
        txt_month=itemView.findViewById(R.id.txt_month);
        dayRecyclerView = itemView.findViewById(R.id.itemDayRV);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        // Используйте GridLayout для отображения календаря
        GridLayoutManager layoutManager = new GridLayoutManager(itemView.getContext(), 7);
        dayRecyclerView.setLayoutManager(layoutManager);
        dayAdapter = new DayAdapter(new ArrayList<>(), 0);
    }


    public void bindMonthYear(String month, String year, int firstDataMonth ,ArrayList<String> dayDataList) {
        txt_year.setText(year);
        txt_month.setText(month);
        dayAdapter.updateData(dayDataList, firstDataMonth);
        dayRecyclerView.setAdapter(dayAdapter);


    }
}
