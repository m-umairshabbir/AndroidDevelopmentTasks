package com.example.taskstodo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class landingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Initialize views
        Button getStartedButton = findViewById(R.id.getStartedButton);
        // Set click listener for the "Get Started" button
        getStartedButton.setOnClickListener(v -> {
            Intent intent = new Intent(landingPage.this, LoginSignup.class);
            startActivity(intent);
            finish();
        });

    }
}
