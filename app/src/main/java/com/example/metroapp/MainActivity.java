package com.example.metroapp;

import static com.example.metroapp.MetroApp.searchStations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  Spinner startSpinner, endSpinner;
  ArrayList<String>stations=new ArrayList<>();
  // static ArrayList<String> kitKatCairoUniversityLine = new ArrayList<>(
  //            Arrays.asList("CairoUniversity", "Tawikfia", "WadiElNail", "GamatElDawel", "BolaqElDakror", "KitKate")
  //    );
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
        startSpinner = findViewById(R.id.startStations);
        endSpinner = findViewById(R.id.endStations);
        fillData();
    }

    public void fillData(){
        stations.add("Please Select");
        stations.add("MetroLine1");
        stations.addAll(CairoLines.cairoLine1());
        stations.add("MetroLine2");
        stations.addAll(CairoLines.cairoLine2());
        stations.add("MetroLine3");
        stations.addAll(CairoLines.cairoLine3());
        stations.add("CarioUniversityBranch");
        stations.addAll(kitKatCairoUniversityLine);


     //   ArrayAdapter adapter=new ArrayAdapter(this, R.layout.item,stations);
        MetroAdapter adapter = new MetroAdapter(this,stations);
       startSpinner.setAdapter(adapter);
        endSpinner.setAdapter(adapter);


    }

    public void calc(View view) {
        // MetroAdapter adapter = new MetroAdapter(this,stations);
       String start = startSpinner.getSelectedItem().toString();
        String end = endSpinner.getSelectedItem().toString();
       boolean isVaildData= MetroApp.isVaildData(start,end);
       if(isVaildData){
           List<String>path=searchStations(start, end);

           // Convert the path to an ArrayList which is Serializable
           ArrayList<String> serializablePath = new ArrayList<>(path);

           Intent a = new Intent(this, ResultActivity.class);
           a.putStringArrayListExtra("path", serializablePath);
           startActivity(a);

       }else {
           Toast.makeText(this, "Please Enter Vaild Data", Toast.LENGTH_SHORT).show();
       }
    }
}
/*
private void printMultiRouht(ArrayList<String> line) {


    }
 */