package com.mycompany.flsapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.Data.CalendarData;
import com.mycompany.flsapp.R;
import com.mycompany.flsapp.ViewModel.FragmentsViewModel.CalendarViewModel;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private ArrayList<CalendarData> monthYearDataList;
    private CalendarViewModel calendarViewModel;

    public CalendarAdapter(ArrayList<CalendarData> monthYearDataList, CalendarViewModel calendarViewModel) {
        this.monthYearDataList = monthYearDataList;
        this.calendarViewModel = calendarViewModel;
    }

    public void setDataCalendar(ArrayList<CalendarData> data)
    {
        this.monthYearDataList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_item,parent,false);
        return  new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        CalendarData data = monthYearDataList.get(position);
        String year = data.getYear();
        String month = data.getMonth();
        int firstDayWeek = data.getFirstDayOfWeek();
        ArrayList<String> dayDataList = data.getDayDataList();
        holder.bindMonthYear(month, year,firstDayWeek,dayDataList,calendarViewModel,position);

    }

    @Override
    public int getItemCount() {
        return monthYearDataList.size();
    }

}

