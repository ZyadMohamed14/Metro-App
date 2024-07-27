package com.example.metroapp;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {

    private List<Station> stationList;
    TextToSpeech tts;

    public StationAdapter(List<Station> stationList, TextToSpeech tts) {
        this.stationList = stationList;
        this.tts = tts;

    }



    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_item, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.tvStartStation.setText("Start: " + station.getStart());
        holder.tvEndStation.setText("End: " + station.getEnd());
        holder.tvPath.setText("Path: " + station.getPath().toString());
        holder.tvTime.setText("Time: " + station.getTime() + " mins");
        holder.tvNoOfStations.setText("No. of Stations: " + station.getNoOfStations());
        holder.tvTicketPrice.setText("Ticket Price: " + station.getTiketPrice() + " EGP");
        holder.tvDirection.setText("Direction: " + station.getDirection());
        holder.itemView.setOnClickListener(v -> {
            tts.speak(station.getDirection(), TextToSpeech.QUEUE_FLUSH, null, null);
        });

    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public static class StationViewHolder extends RecyclerView.ViewHolder {
        TextView tvStartStation, tvEndStation, tvPath, tvTime, tvNoOfStations, tvTicketPrice, tvDirection;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStartStation = itemView.findViewById(R.id.tvStartStation);
            tvEndStation = itemView.findViewById(R.id.tvEndStation);
            tvPath = itemView.findViewById(R.id.tvPath);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvNoOfStations = itemView.findViewById(R.id.tvNoOfStations);
            tvTicketPrice = itemView.findViewById(R.id.tvTicketPrice);
            tvDirection = itemView.findViewById(R.id.tvDirection);
        }
    }
}
