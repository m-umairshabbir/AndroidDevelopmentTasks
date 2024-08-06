package com.example.listview;

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

//        ListView lv=findViewById(R.id.lv);
//        String[] friends={"Zia (RIP)", "Dr Kaleem","Fahad Ali Babar","Hamza","Umair","Awais","Huzii","Zulli","Zeshan","Data Scientist Me!!"};
//
//        ArrayAdapter<String> friendsAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_gallery_item,friends);
//        lv.setAdapter(friendsAdapter);

    }
}