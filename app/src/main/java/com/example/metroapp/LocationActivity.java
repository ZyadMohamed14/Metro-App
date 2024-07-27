package com.example.metroapp;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mumayank.com.airlocationlibrary.AirLocation;

public class LocationActivity extends AppCompatActivity implements AirLocation.Callback {
    AirLocation airLocation;
    TextView currentLocationTv,endLocationTv;
    EditText endDirectionEt;
    String nearstStationToCurrentLocation,nearstStationToEndLocation;

    MetroApp metroApp;
    ArrayList<Station> stations = new ArrayList<>();
    ArrayList<ArrayList<String>> rouths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        currentLocationTv=findViewById(R.id.currentLocationtv);
        endLocationTv =findViewById(R.id.endDirectionTv);
        endDirectionEt = findViewById(R.id.editTextText);
        airLocation = new AirLocation(this, this, true, 0, "");



    }

    public void getCurrentLocation(View view) {
        airLocation.start();
    }
    public void confirmEndDirection(View view) {
        nearstStationToEndLocation = endDirectionEt.getText().toString();
        if (nearstStationToEndLocation.isEmpty()) {
            Toast.makeText(this, "You should enter a district", Toast.LENGTH_SHORT).show();
            return;
        }
        nearstStationToEndLocation = endDirectionEt.getText().toString() + " egypt";
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocationName(nearstStationToEndLocation, 1);
            if (addresses != null && !addresses.isEmpty()) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                nearstStationToEndLocation = findNearstStation(latitude, longitude);
                endLocationTv.setText("Nearest Station According to end Location is " + nearstStationToEndLocation);
            } else {
                Toast.makeText(this, "No location found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("benz", e.getMessage());
        }
    }
    public void confirmRout(View view) {
        if(nearstStationToEndLocation.isEmpty()||nearstStationToCurrentLocation.isEmpty()){
            Toast.makeText(this, "Please Enter Vaild Data", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent=new Intent(this, RoutActivity.class);

        metroApp=new MetroApp(nearstStationToCurrentLocation,nearstStationToEndLocation);
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
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        } else {
            Toast.makeText(this, "Please Enter Vaild Data", Toast.LENGTH_SHORT).show();

        }
    }
    public double[] splitCoordinates(String coordinateString) {
        // Split the string by '/'
        String[] parts = coordinateString.split(",");

        // Check if the split was successful and we have exactly two parts
        if (parts.length == 2) {
            try {
                // Parse latitude and longitude
                double latitude = Double.parseDouble(parts[0]);
                double longitude = Double.parseDouble(parts[1]);

                // Return the coordinates as a double array
                return new double[]{latitude, longitude};
            } catch (NumberFormatException e) {
                System.out.println("Error in parsing the numbers: " + e.getMessage());
            }
        } else {
            System.out.println("Input text is not in the expected format.");
        }

        // Return null if parsing failed or format was incorrect
        return null;
    }
    private String findNearstStation(double lat, double lon) {
        Location myCurrentLocation = new Location("");
        myCurrentLocation.setLatitude(lat);
        myCurrentLocation.setLongitude(lon);

        Map<String, String> metroLine1Coordinates = CairoLines.metroLine1Coordinates();
        Map<String, String> metroLine2Coordinates = CairoLines.metroLine2Coordinates();
        Map<String, String> metroLine3Coordinates = CairoLines.metroLine3Coordinates();

        double nearestDistance = Double.MAX_VALUE;
        String nearestStation = "";

        // Check all stations in Metro Line 1
        for (Map.Entry<String, String> entry : metroLine1Coordinates.entrySet()) {
            String stationName = entry.getKey();
            String coordinates = entry.getValue();
            double[] latLong = splitCoordinates(coordinates);
            Location stationLocation = new Location("");
            stationLocation.setLatitude(latLong[0]);
            stationLocation.setLongitude(latLong[1]);

            double distance = myCurrentLocation.distanceTo(stationLocation);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestStation = stationName;
            }
        }

        // Check all stations in Metro Line 2
        for (Map.Entry<String, String> entry : metroLine2Coordinates.entrySet()) {
            String stationName = entry.getKey();
            String coordinates = entry.getValue();
            double[] latLong = splitCoordinates(coordinates);
            Location stationLocation = new Location("");
            stationLocation.setLatitude(latLong[0]);
            stationLocation.setLongitude(latLong[1]);

            double distance = myCurrentLocation.distanceTo(stationLocation);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestStation = stationName;
            }
        }

        // Check all stations in Metro Line 3
        for (Map.Entry<String, String> entry : metroLine3Coordinates.entrySet()) {
            String stationName = entry.getKey();
            String coordinates = entry.getValue();
            double[] latLong = splitCoordinates(coordinates);
            Location stationLocation = new Location("");
            stationLocation.setLatitude(latLong[0]);
            stationLocation.setLongitude(latLong[1]);

            double distance = myCurrentLocation.distanceTo(stationLocation);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestStation = stationName;
            }
        }

        // Display the nearest station
        Toast.makeText(this, "Nearest station: " + nearestStation, Toast.LENGTH_SHORT).show();

        return nearestStation;
    }
    @Override
    public void onSuccess(@NonNull ArrayList<Location> locations) {
        double latitudeOfCurrentLocation = locations.get(0).getLatitude();
        double longitudeOfCurrentLocation = locations.get(0).getLongitude();
        nearstStationToCurrentLocation = findNearstStation(latitudeOfCurrentLocation, longitudeOfCurrentLocation);
        currentLocationTv.setText("Nearst Station According to your Location is " + nearstStationToCurrentLocation);
    }

    @Override
    public void onFailure(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        airLocation.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}