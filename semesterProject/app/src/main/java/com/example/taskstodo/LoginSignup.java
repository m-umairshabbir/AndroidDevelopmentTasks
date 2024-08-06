package com.example.taskstodo;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginSignup extends AppCompatActivity {

    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, new login_frag());

        loadFrag(new login_frag(), 0);

        login.setOnClickListener(v -> {
            loadFrag(new login_frag(), 1);
        });
        register.setOnClickListener(v -> {
            loadFrag(new register_frag(), 1);
        });
    }
    public void loadFrag(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 0)
            ft.add(R.id.container, fragment);
        else ft.replace(R.id.container, fragment);


        ft.commit();
    }
}