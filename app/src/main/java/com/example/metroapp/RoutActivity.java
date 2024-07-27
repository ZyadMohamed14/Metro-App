package com.example.metroapp;


import static com.example.metroapp.AppKeys.STATIONS_KEY;
import static com.example.metroapp.AppKeys.TRAVELEXISTS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RoutActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private RecyclerView stationsRv;
    private StationAdapter stationAdapter;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    TextToSpeech tts;
    boolean isTravelCanceled;
    Gson gson;
    String stationsJson;


    ArrayList<Station>stations=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tts=new TextToSpeech(this,this);
        preferences = getSharedPreferences(AppKeys.METRO_FILE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();
        stationsRv = findViewById(R.id.stationRv);
        stationsRv.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    protected void onStart() {
        super.onStart();

        // Restore list of stations
        isTravelCanceled = preferences.getBoolean(TRAVELEXISTS, false);
        if(isTravelCanceled){
            Toast.makeText(this, "Data From json", Toast.LENGTH_SHORT).show();
             gson = new Gson();
            String stationsJson = preferences.getString(STATIONS_KEY, null);
            Type type = new TypeToken<ArrayList<Station>>() {}.getType();
            if (stationsJson != null) {
                stations.clear();
                stations = gson.fromJson(stationsJson, type);

            } else {
                Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Data From Intent", Toast.LENGTH_SHORT).show();
            stations.clear();
            stations = getIntent().getParcelableArrayListExtra("stations");
        }
        stationAdapter = new StationAdapter(stations,tts);
        stationsRv.setAdapter(stationAdapter);

    }
    private void saveUiState() {
        editor.putBoolean(TRAVELEXISTS, true);
        // Convert stations list to JSON and save

        stationsJson = gson.toJson(stations);
        editor.putString(STATIONS_KEY, stationsJson);
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        if (isTravelCanceled) {
            resetData();
        }else{
            Toast.makeText(this, "You should end The Travel", Toast.LENGTH_SHORT).show();
            return;
        }

        super.onBackPressed();

    }

    public void endTravel(View view) {
        resetData();
        Intent intent =new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        if (!isTravelCanceled) {
            saveUiState();
        }
        super.onPause();
    }



    @Override
    protected void onDestroy() {
        if (!isTravelCanceled) {
            saveUiState();
        }
        super.onDestroy();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void resetData() {

        // Remove specific keys
        isTravelCanceled = true;
        stations.clear();
        stationsJson = gson.toJson(stations);
        editor.putString(STATIONS_KEY,stationsJson);
        editor.putBoolean(TRAVELEXISTS,false);
        stations.clear();
        stationAdapter.setStationList(stations);
        stationAdapter.notifyDataSetChanged();
        editor.apply();
    }
    @Override
    public void onInit(int status) {

    }
}