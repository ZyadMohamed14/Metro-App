package com.example.metroapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

public class Station implements Parcelable {
    String start;
    String end;
    List<String> path;
    double time;
    int noOfStations;
    double ticketPrice;
    String direction;

    public Station(List<String> path, String direction) {
        this.path = path;
        this.direction = direction;
        calcData();
    }

    protected Station(Parcel in) {
        start = in.readString();
        end = in.readString();
        path = in.createStringArrayList();
        time = in.readDouble();
        noOfStations = in.readInt();
        ticketPrice = in.readDouble();
        direction = in.readString();
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    private void calcData() {
        String start = path.get(0);
        String end = path.get(path.size()-1);
        int startIndex = path.indexOf(start);
        int endIndex = path.indexOf(end);
        noOfStations = Math.abs(endIndex - startIndex);

        Log.d("Station", "startIndex: " + startIndex);
        Log.d("Station", "endIndex: " + endIndex);
        Log.d("Station", "noOfStations: " + noOfStations);

        if (noOfStations <= 9) {
            ticketPrice = 6.0; // Base fare for up to 8 stations
        } else if (noOfStations <= 16) {
            ticketPrice = 8.0; // Fare for 9 to 16 stations
        } else {
            ticketPrice = 12.0; // Fare for more than 16 stations
        }

        Log.d("Station", "ticketPrice: " + ticketPrice);

        int timePerStation = 2;
        time = timePerStation * noOfStations;

        Log.d("Station", "time: " + time);
    }
    public String getStart() {
        return path.get(0);
    }

    public String getEnd() {
        return path.get(getPath().size()-1);
    }

    public List<String> getPath() {
        return path;
    }

    public int getNoOfStations() {

        return noOfStations;
    }

    public double getTime() {
      return time;
    }

    public double getTiketPrice() {

        return ticketPrice;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(start);
        dest.writeString(end);
        dest.writeStringList(path);
        dest.writeDouble(time);
        dest.writeInt(noOfStations);
        dest.writeDouble(ticketPrice);
        dest.writeString(direction);
    }
}
