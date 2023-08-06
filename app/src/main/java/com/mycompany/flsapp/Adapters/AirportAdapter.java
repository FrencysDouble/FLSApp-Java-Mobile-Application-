package com.mycompany.flsapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.API.DataMerging;
import com.mycompany.flsapp.R;

import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.AirportViewHolder> {

    private List<DataMerging.Data> dataList;

    public AirportAdapter(List<DataMerging.Data> dataList) {
        this.dataList = dataList;
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
        DataMerging.Data data = dataList.get(position);
        holder.tvAirportName.setText(data.getCityName());
        holder.tvCountryName.setText(data.getCountryName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public class AirportViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAirportName;
        public TextView tvCountryName;

        public AirportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAirportName = itemView.findViewById(R.id.txt_view_airport);
            tvCountryName = itemView.findViewById(R.id.txt_view_countries);
        }
    }
}