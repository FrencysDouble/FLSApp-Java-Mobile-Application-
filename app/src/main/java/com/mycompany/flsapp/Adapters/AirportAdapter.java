package com.mycompany.flsapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.flsapp.API.DataAirports;
import com.mycompany.flsapp.R;

import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.AirportViewHolder> {

    private List<DataAirports> airportList;

    public AirportAdapter(List<DataAirports> airportList) {
        this.airportList = airportList;
    }

    @NonNull
    @Override
    public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.airport_item, parent, false);
        return new AirportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportViewHolder holder, int position) {
        DataAirports dataAirports= airportList.get(position);
        holder.tvAirportName.setText(DataAirports.getName());
    }

    @Override
    public int getItemCount() {
        return airportList.size();
    }

    public class AirportViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAirportName;

        public AirportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAirportName = itemView.findViewById(R.id.txt_veiw_aiport);
        }
    }

}
