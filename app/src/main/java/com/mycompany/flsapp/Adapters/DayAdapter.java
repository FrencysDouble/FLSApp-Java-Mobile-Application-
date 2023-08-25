package com.mycompany.flsapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

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
    Context context;

    public DayAdapter(ArrayList<String> dayDataList, int firstDayMonth, CalendarViewModel calendarViewModel) {
        this.dayDataList = dayDataList;
        this.firstDayMonth = (firstDayMonth);
        this.calendarViewModel = calendarViewModel;
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
                Log.d("Position CLick","Click" + position);
                animateItemClick(holder.itemView,clickedPos);
            }
        });

        int backgroundColor = getBackgroundColor(position);
        holder.itemView.setBackgroundColor(backgroundColor);
        }


    @SuppressLint("NotifyDataSetChanged")
    private void animateItemClick(View view, int position) {
        Drawable drawable = view.getBackground();
        if (isSelected(position)) {
            // Если элемент уже выбран, снимаем выбор и убираем его из списка выбранных
            drawable.setTint(ContextCompat.getColor(context, R.color.defaultColor));
            ((TextView) view.findViewById(R.id.cellDayText)).setTextColor(ContextCompat.getColor(context, R.color.defaultColor));
            selectedItems.remove(Integer.valueOf(position));

            // Если элемент, который был снят с выбора, был первым выбранным элементом,
            // установим новый первый выбранный элемент (если такой есть)
            if (firstSelectedPosition == position) {
                firstSelectedPosition = -1;
                if (selectedItems.size() > 0) {
                    firstSelectedPosition = selectedItems.get(0);
                }
            } else if (secondSelectedPosition == position) {
                secondSelectedPosition = -1;
            }
        } else {
            // Если элемент не был выбран ранее, устанавливаем его в выбранное состояние
            drawable.setTint(ContextCompat.getColor(context, R.color.selectedColor));
            ((TextView) view.findViewById(R.id.cellDayText)).setTextColor(ContextCompat.getColor(context, R.color.selectedColor));
            selectedItems.add(position);

            // Если еще нет первого выбранного элемента, устанавливаем его
            if (firstSelectedPosition == -1) {
                firstSelectedPosition = position;
            } else if (secondSelectedPosition == -1) {
                secondSelectedPosition = position;
                // Закрашиваем все элементы между первым и вторым выбранными элементами
                int start = Math.min(firstSelectedPosition, secondSelectedPosition);
                int end = Math.max(firstSelectedPosition, secondSelectedPosition);
                for (int i = start; i <= end; i++) {
                    selectedItems.add(i);
                }
            } else {
                // Если уже выбраны два элемента, снимаем выбор с одного из них
                firstSelectedPosition = -1;
                secondSelectedPosition = -1;
                selectedItems.clear();
            }
        }
        notifyDataSetChanged();
    }

    private int getBackgroundColor(int position) {
        if (isSelected(position)) {
            return ContextCompat.getColor(context, R.color.selectedColor);
        } else if (firstSelectedPosition != -1 && secondSelectedPosition != -1) {
            // Проверяем, находится ли элемент между firstSelectedPosition и secondSelectedPosition
            int min = Math.min(firstSelectedPosition, secondSelectedPosition);
            int max = Math.max(firstSelectedPosition, secondSelectedPosition);
            if (position > min && position < max) {
                return ContextCompat.getColor(context, R.color.selectedColor);
            }
        }
        return ContextCompat.getColor(context, R.color.defaultColor);
    }



    private boolean isSelected(int position) {
        return selectedItems.contains(position);
    }


    @Override
    public int getItemCount() {
        return dayDataList.size() + firstDayMonth;
    }
}
