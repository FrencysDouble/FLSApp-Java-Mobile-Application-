package com.mycompany.flsapp.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.R;

public class DayViewHolder extends RecyclerView.ViewHolder {
    private final TextView cellText;
    public DayViewHolder(@NonNull View itemView) {
        super(itemView);
        cellText=itemView.findViewById(R.id.cellDayText);
        int gridItemHeight = itemView.getResources().getDimensionPixelSize(R.dimen.grid_spacing); // Замените на свой ресурс
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.height = gridItemHeight;
    }

    public void bindDay(String day) {
        cellText.setText(day);
    }
}
