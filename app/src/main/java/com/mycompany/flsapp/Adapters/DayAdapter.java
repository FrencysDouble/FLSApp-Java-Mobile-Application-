package com.mycompany.flsapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.Data.CalendarData;
import com.mycompany.flsapp.Data.DataPositions;
import com.mycompany.flsapp.R;
import com.mycompany.flsapp.ViewModel.FragmentsViewModel.CalendarViewModel;

import java.util.ArrayList;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayViewHolder>{

    private final ArrayList<String> dayDataList;
    int firstDayMonth;
    CalendarViewModel calendarViewModel;
    private List<Integer> selectedItems = new ArrayList<>();
    private int firstSelectedPosition = -1;
    private int secondSelectedPosition = -1;
    private int monthPosition;
    Context context;

    public DayAdapter(ArrayList<String> dayDataList, int firstDayMonth, CalendarViewModel calendarViewModel,int monthPosition) {
        this.dayDataList = dayDataList;
        this.firstDayMonth = (firstDayMonth);
        this.calendarViewModel = calendarViewModel;
        this.monthPosition = monthPosition;
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
        if (position < (firstDayMonth)) {
            // Эта ячейка пуста
            holder.bindDay("");
        } else {
            String day = dayDataList.get(position - firstDayMonth);
            holder.bindDay(day);
        }

        Context context = holder.itemView.getContext();
        this.context = context;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPos = holder.getAdapterPosition();
                Log.d("Position CLick", "Click" + clickedPos);
                calendarViewModel.toggleDaySelection(monthPosition,clickedPos);
            }
        });

        int backgroundColor = getBackgroundColor(position);
        holder.itemView.setBackgroundColor(backgroundColor);
    }

    private int getBackgroundColor(int position) {
        if (calendarViewModel != null) {
            ArrayList<CalendarData> calendarDataList = calendarViewModel.getCalendarList().getValue();
            if (calendarDataList != null && monthPosition >= 0 && monthPosition < calendarDataList.size()) {
                CalendarData currentMonthCalendar = calendarDataList.get(monthPosition);

                ArrayList<DataPositions> dataPositionsList = currentMonthCalendar.getPositions();

                for (DataPositions dataPositions : dataPositionsList) {
                    if (dataPositions.getPositions().contains(position)) {
                        return ContextCompat.getColor(context, R.color.selectedColorL);
                    }
                }
            }
        }
        return ContextCompat.getColor(context, R.color.defaultColor);
    }


    @Override
    public int getItemCount() {
        return dayDataList.size() + firstDayMonth;
    }
}
