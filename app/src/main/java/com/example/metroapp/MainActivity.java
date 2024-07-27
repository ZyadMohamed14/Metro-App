package com.example.metroapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import mumayank.com.airlocationlibrary.AirLocation;

public class MainActivity extends AppCompatActivity implements  TextToSpeech.OnInitListener {


    Spinner startSpinner, endSpinner;
    ArrayList<String> adapterStations = new ArrayList<>();
    ArrayList<Station> stations = new ArrayList<>();
    ArrayList<ArrayList<String>> rouths = new ArrayList<>();

    SharedPreferences preferences;
    SpinnerAdapter adapter;
    TextToSpeech tts;
    Intent intent;

    private boolean isTtsInitialized = false;
    SharedPreferences.Editor editor;


    List<String> kitKatCairoUniversityLine = Arrays.asList("Tawikfia", "WadiElNail", "GamatElDawel", "BolaqElDakror");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        tts = new TextToSpeech(this, this);
        startSpinner = findViewById(R.id.startStations);
        endSpinner = findViewById(R.id.endStations);
        intent = new Intent(this, RoutActivity.class);
        preferences = getSharedPreferences(AppKeys.METRO_FILE, Context.MODE_PRIVATE);
        editor = preferences.edit();

        fillData();


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (isTtsInitialized) {
            String welcome = "For a better experience, press the map button to find your route.";
            tts.speak(welcome, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Log.e("TTS", "TextToSpeech is not initialized yet");
        }
    }

    public void fillData() {
        adapterStations.add("Please Select");
        adapterStations.add("MetroLine1");
        adapterStations.addAll(CairoLines.cairoLine1());
        adapterStations.add("MetroLine2");
        adapterStations.addAll(CairoLines.cairoLine2());
        adapterStations.add("MetroLine3");
        adapterStations.addAll(CairoLines.cairoLine3());
        adapterStations.add("CarioUniversityBranch");
        adapterStations.addAll(kitKatCairoUniversityLine);


        //   ArrayAdapter adapter=new ArrayAdapter(this, R.layout.item,stations);
        adapter = new SpinnerAdapter(this, adapterStations);
        startSpinner.setAdapter(adapter);
        endSpinner.setAdapter(adapter);
        int startStation = preferences.getInt("startStation", 0);
        int endStation = preferences.getInt("endStation", 0);
        Log.d("MyPrefs", "Start Station: " + startStation);
        startSpinner.setSelection(startStation);
        endSpinner.setSelection(endStation);

    }

    public void findMyRout(View view) {
        // MetroAdapter adapter = new MetroAdapter(this,stations);

        String start = startSpinner.getSelectedItem().toString();
        String end = endSpinner.getSelectedItem().toString();
        MetroApp metroApp = new MetroApp(start, end);
        boolean isVaildData = metroApp.isVaildData();
        if (isVaildData) {
            metroApp.searchPath();
            rouths = metroApp.getRoutes();
            if (rouths.size() == 1) {
                Station station = new Station(rouths.get(0), metroApp.getDirection());
                stations.add(station);
            } else {
                Station station = new Station(rouths.get(0), metroApp.getDirectionForFirstRoute().toString());
                stations.add(station);
                Station station2 = new Station(rouths.get(1), metroApp.getDirectionForSecondRoute().toString());
                stations.add(station2);
            }
            intent.putExtra("stations", stations);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Please Enter Vaild Data", Toast.LENGTH_SHORT).show();

        }


    }

    public void revresdata(View view) {
        int startPosition = startSpinner.getSelectedItemPosition();
        int endPosition = endSpinner.getSelectedItemPosition();
        startSpinner.setSelection(endPosition);
        endSpinner.setSelection(startPosition);
    }




    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int langResult = tts.setLanguage(Locale.US);
            if (langResult == TextToSpeech.LANG_MISSING_DATA ||
                    langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported or missing data");
            } else {
                isTtsInitialized = true;
            }
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }


    public void suggetRout(View view) {
        Intent a= new Intent(this,LocationActivity.class);
        startActivity(a);
    }
}
