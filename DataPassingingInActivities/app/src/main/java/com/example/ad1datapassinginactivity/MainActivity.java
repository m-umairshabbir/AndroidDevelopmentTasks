package com.example.ad1datapassinginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        EditText nameET=findViewById(R.id.nameET);
        EditText numberET=findViewById(R.id.numberET);
        Button btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameET.getText().toString();
                String number=numberET.getText().toString();

              Intent inext=new Intent(getApplicationContext(),MainActivity2.class);
                    inext.putExtra("name",name);
                    inext.putExtra("number",number);
                    startActivity(inext);
            }
        });




    }
}