package com.example.mylistview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv=findViewById(R.id.list);

        String[] friends={"Allah","zia Ullah(RIP)","Dr Kaleem Ahmad","Fahad Ali Babar","Hamza","me DataScientist"};

        ArrayAdapter<String> friendAdapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item,friends);
        lv.setAdapter(friendAdapter);

    }
}