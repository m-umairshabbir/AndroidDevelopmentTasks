package com.example.taskstodo;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_frag extends Fragment {

    private EditText fnameEditText, usernameEditText, emailEditText, passEditText, cpassEditText;
    private Button registerButton;
    private FirebaseAuth mAuth;

    public register_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_frag, container, false);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        fnameEditText = view.findViewById(R.id.fname);
        usernameEditText = view.findViewById(R.id.username);
        emailEditText = view.findViewById(R.id.email);
        passEditText = view.findViewById(R.id.pass);
        cpassEditText = view.findViewById(R.id.cpass);
        registerButton = view.findViewById(R.id.rbtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        return view;
    }

    private void registerUser() {
        String fname = fnameEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passEditText.getText().toString().trim();
        String confirmPassword = cpassEditText.getText().toString().trim();

        // Perform input validation
        if (fname.isEmpty()) {
            fnameEditText.setError("Full name is required");
            fnameEditText.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            usernameEditText.setError("Username is required");
            usernameEditText.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passEditText.setError("Password is required");
            passEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passEditText.setError("Password must be at least 6 characters long");
            passEditText.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            cpassEditText.setError("Passwords do not match");
            cpassEditText.requestFocus();
            return;
        }

        // Register the user in Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Registration success, update UI or perform any other actions
                        FirebaseUser user = mAuth.getCurrentUser();
                        // You can redirect the user to another fragment or activity here
                        Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // Registration failed, display a message to the user
                        Toast.makeText(requireContext(), "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
