package com.mycompany.flsapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.Model.DataMerging;
import com.mycompany.flsapp.Interfaces.ItemClickListener;
import com.mycompany.flsapp.R;

import java.util.ArrayList;
import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.AirportViewHolder> {

    private List<DataMerging.Data> dataList;
    private List<DataMerging.Data> filteredData = new ArrayList<>();
    private final ItemClickListener itemClickListener;

    public AirportAdapter(List<DataMerging.Data> dataList, ItemClickListener itemClickListener) {
        this.dataList = dataList;
        this.filteredData.addAll(dataList);
        this.itemClickListener = itemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<DataMerging.Data> dataList)
    {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.airport_item, parent, false);
        return new AirportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportViewHolder holder, int position) {
        if (position < filteredData.size()) {
            DataMerging.Data data = filteredData.get(position);
            holder.bindData(data);
        }
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterData(String query) {
        filteredData.clear();
        if (query.isEmpty()) {
            filteredData.addAll(dataList);
            notifyDataSetChanged();
        } else {
            for (DataMerging.Data data : dataList) {
                if (data.getCityName() != null && data.getCityName().toLowerCase().contains(query.toLowerCase())) {
                    if (!filteredData.contains(data)) {
                        filteredData.add(data);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }



    public class AirportViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAirportName;
        public TextView tvCountryName;
        public View view;

        public AirportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAirportName = itemView.findViewById(R.id.txt_view_airport);
            tvCountryName = itemView.findViewById(R.id.txt_view_countries);
            view = itemView.findViewById(R.id.view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        DataMerging.Data data = filteredData.get(position);
                        itemClickListener.onItemClick(data);
                    }
                }
            });
        }
        public void bindData(DataMerging.Data data) {
            tvAirportName.setText(data.getCityName());
            tvCountryName.setText(data.getCountryName());
        }
    }
}