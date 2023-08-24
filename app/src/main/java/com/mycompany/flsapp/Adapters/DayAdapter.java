package com.mycompany.flsapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.R;

import java.util.ArrayList;

public class DayAdapter extends RecyclerView.Adapter<DayViewHolder> {

    private final ArrayList<String> dayDataList;
    int firstDayMonth;

    public DayAdapter(ArrayList<String> dayDataList, int firstDayMonth) {
        this.dayDataList = dayDataList;
        this.firstDayMonth = (firstDayMonth);
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        if (position < (firstDayMonth) ){
            // Эта ячейка пуста
            holder.bindDay("");
        } else {
            String day = dayDataList.get(position - firstDayMonth);
            holder.bindDay(day);
        }
    }

    @Override
    public int getItemCount() {
        return dayDataList.size() + firstDayMonth;
    }

    public void updateData(ArrayList<String> dayDataList, int firstDataMonth) {
        this.dayDataList.clear();
        this.dayDataList.addAll(dayDataList);
        this.firstDayMonth = firstDayMonth;
        notifyDataSetChanged();
    }
}
