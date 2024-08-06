package com.example.ad1datapassinginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView getName=findViewById(R.id.getname);
        TextView getNumber=findViewById(R.id.getnumber);

        Intent i=getIntent();
        String name=i.getStringExtra("name");
        String number=i.getStringExtra("number");

        getName.setText(name);
        getNumber.setText(number);
    }
}