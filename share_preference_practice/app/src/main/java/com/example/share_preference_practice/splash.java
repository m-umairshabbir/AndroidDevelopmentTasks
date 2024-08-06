package com.example.share_preference_practice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                Boolean checkIsLoggedIn =pref.getBoolean("flag",false);
                Intent iNext;
                if(checkIsLoggedIn){
                    iNext=new Intent(splash.this,home_screen.class);

                }else{
                    iNext=new Intent(splash.this,MainActivity.class);
                }
                startActivity(iNext);

            }
        },3000);

    }

}