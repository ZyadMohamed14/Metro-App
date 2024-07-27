package com.example.metroapp;

import static com.example.metroapp.AppKeys.TRAVELEXISTS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    // Set the duration of the splash screen in milliseconds
    private static final int SPLASH_DURATION = 3000;
    SharedPreferences preferences;// 3 seconds
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Use a Handler to post a delayed action for transitioning to the main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                preferences = getSharedPreferences(AppKeys.METRO_FILE, Context.MODE_PRIVATE);

                boolean isTravelExist = preferences.getBoolean(TRAVELEXISTS, false);
                Toast.makeText(SplashScreen.this, "isTravelExist" + isTravelExist, Toast.LENGTH_SHORT).show();
                if (isTravelExist) {
                    intent = new Intent(SplashScreen.this, RoutActivity.class);
                    startActivity(intent);
                    finish();  // Finish the current activity to prevent it from continuing
                        // Exit the method to prevent further execution
                }else{
                    intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();  // Finish the current activity to prevent it from continuing
                }
            }
        }, SPLASH_DURATION);
    }

}