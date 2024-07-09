package com.example.metroapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.metroapp.databinding.ActivityResultBinding;

import java.util.ArrayList;
import java.util.Collections;

public class ResultActivity extends AppCompatActivity {
      ActivityResultBinding binding;
    StringBuilder output = new StringBuilder();
      ArrayList<String>transfers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Collections.addAll(transfers,"Sadat","Nasser","Al-Shohadaa","Attaba","CairoUniversity","KitKate");
        ArrayList<String>line =  getIntent().getStringArrayListExtra("path");
        if (line == null) {
            Log.e("ResultActivity", "Path list is null");
            return;
        }
        Log.e("benzoblack", "MetroApp.isRouthInTheSameLine"+MetroApp.isRouthInTheSameLine);
        if(MetroApp.isRouthInTheSameLine){
         printSingleRouht(line);
        }else {
            printMultiRouht(line);
        }


    }

    private void printSingleRouht(ArrayList<String>line){
        String fristElement = line.get(0);
        String lastElement = line.get(line.size() - 1);
        int startIndex = line.indexOf(fristElement);
        int endIndex = line.indexOf(lastElement);
        binding.starttv.setText(fristElement);
        binding.endtv.setText(lastElement);
        int numberOfStations = Math.abs(endIndex - startIndex);
        int timePerStation = 2;
        int expectedTime = timePerStation * numberOfStations;
        binding.numberOfStationtv.setText(numberOfStations + "");
        binding.timetv.setText(expectedTime + " Minutes");
        // Calculate metro ticket price based on fare structure
        double ticketPrice;
        if (numberOfStations <= 9) {
            ticketPrice = 6.0; // Base fare for up to 8 stations
        } else if (numberOfStations <= 16) {
            ticketPrice = 8.0; // Fare for 9 to 16 stations
        } else {
            ticketPrice = 12.0; // Fare for more than 16 stations
        }
        binding.pricetv.setText("ticket price: £EGP" + ticketPrice);

        // Print stations traveled (optional)
        String direction=MetroApp.direction;

        binding.directiontv.setText("Your Direction is " + direction);
        binding.linetv.setText("Your Rout is " + line);
        MetroApp.isRouthInTheSameLine=false;
        MetroApp.direction="";
    }

    private void printMultiRouht(ArrayList<String> line){
        String fristElement = line.get(0);
        String lastElement = line.get(line.size() - 1);
        int startIndex = line.indexOf(fristElement);
        int endIndex = line.indexOf(lastElement);
        binding.starttv.setText(fristElement);
        binding.endtv.setText(lastElement);
        int numberOfStations = Math.abs(endIndex - startIndex);
        int timePerStation = 2;
        int expectedTime = timePerStation * numberOfStations;
        binding.numberOfStationtv.setText(numberOfStations + "");
        binding.timetv.setText(expectedTime + " Minutes");

        // Calculate metro ticket price based on fare structure
        double ticketPrice;
        if (numberOfStations <= 9) {
            ticketPrice = 6.0; // Base fare for up to 8 stations
        } else if (numberOfStations <= 16) {
            ticketPrice = 8.0; // Fare for 9 to 16 stations
        } else {
            ticketPrice = 12.0; // Fare for more than 16 stations
        }
        binding.pricetv.setText("ticket price: £EGP" + ticketPrice);

        // Print stations traveled (optional)
        String direction = "";
        String trasnfer = MetroApp.transfersStation != null ? MetroApp.transfersStation : "";

        if (MetroApp.isRouthInTheFirstLine || MetroApp.isRouthInTheSecondLine || MetroApp.isRouthInTheThirdLine || MetroApp.isRouthInTheFourthLine) {
            binding.directiontv.setText("Your Direction is " + direction);
        } else {
            if (!trasnfer.equals("")) {
                direction = "Your Direction is to " + (MetroApp.startDirection != null ? MetroApp.startDirection : "") +
                        " and you Will Change Direction at " + trasnfer +
                        " and Take the Direction to " + (MetroApp.endDirection != null ? MetroApp.endDirection : "");
                binding.directiontv.setText(direction);
            } else {
                direction = (MetroApp.transBuilder != null ? MetroApp.transBuilder.toString() : "") +
                        " and Take the Direction to " + (MetroApp.endDirection != null ? MetroApp.endDirection : "");
                String regex = "(?=And Change Your Direction At|And Then Change Direction At)";

                // Split the input string based on the regex
                String[] parts = direction.split(regex);

                // Build the output with the desired format
                output.setLength(0); // Clear the output StringBuilder before using it
                for (int i = 0; i < parts.length; i++) {
                    if (i == 0) {
                        output.append(parts[i].trim());
                    } else {
                        output.append("\n-").append(parts[i].trim());
                    }
                }
                binding.directiontv.setText(output.toString());
                Log.d("benz", direction);
            }
        }

        binding.linetv.setText("Your Rout is " + line);

        // Reset the MetroApp variables
        MetroApp.isRouthInTheMultiLine = false;
        MetroApp.startDirection = "";
        MetroApp.endDirection = "";
        if (MetroApp.transBuilder != null) {
            MetroApp.transBuilder.setLength(0); // Clear the StringBuilder
        }
    }
}

/*
private void printMultiRouht2(ArrayList<String>line){
        String fristElement = line.get(0);
        String lastElement = line.get(line.size()-1);
        int startIndex = line.indexOf(fristElement);
        int endIndex = line.indexOf(lastElement);
        binding.starttv.setText(fristElement);
        binding.endtv.setText(lastElement);
        int numberOfStations = Math.abs(endIndex - startIndex);
        int timePerStation = 2;
        int expectedTime = timePerStation * numberOfStations;
        binding.numberOfStationtv.setText(numberOfStations+"");
        binding.timetv.setText(expectedTime+" Minutes");
        // Calculate metro ticket price based on fare structure
        double ticketPrice;
        if (numberOfStations <= 9) {
            ticketPrice = 6.0; // Base fare for up to 8 stations
        } else if (numberOfStations <= 16) {
            ticketPrice = 8.0; // Fare for 9 to 16 stations
        } else {
            ticketPrice = 12.0; // Fare for more than 16 stations
        }
        binding.pricetv.setText("ticket price: £EGP"+ ticketPrice);

        // Print stations traveled (optional)
        String direction = "";
        String trasnfer;
        if(MetroApp.isRouthInTheFirstLine||MetroApp.isRouthInTheSecondLine||MetroApp.isRouthInTheThirdLine||MetroApp.isRouthInTheFourthLine){

            binding.directiontv.setText("Your Direction is "+direction);
        }else {

            trasnfer = MetroApp.transfersStation;
            if(!trasnfer.equals("")||trasnfer==null){
                direction = "Your Direction is to "+MetroApp.startDirection +" and you Will Change Direction at "+ trasnfer
                        +" and Take the Direction to "+MetroApp.endDirection;
                binding.directiontv.setText(direction);
            }else{
                direction = MetroApp.transBuilder
                        +" and Take the Direction to "+MetroApp.endDirection;
                String regex = "(?=And Change Your Direction At|And Then Change Direction At)";

                // Split the input string based on the regex
                String[] parts = direction.split(regex);

                // Build the output with the desired format

                for (int i = 0; i < parts.length; i++) {
                    if (i == 0) {
                        output.append(parts[i].trim());
                    } else {
                        output.append("\n-").append(parts[i].trim());
                    }
                }
                binding.directiontv.setText(output.toString());
                Log.d("benz",direction);
            }



        }

        binding.linetv.setText("Your Rout is " + line );

        MetroApp.isRouthInTheMultiLine=false;
        MetroApp.startDirection="";
        MetroApp.endDirection ="";
        MetroApp.transBuilder.delete(0, MetroApp.transBuilder.length());

    }
 */