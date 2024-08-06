package com.example.taskstodo;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

     Button taskBtn,openAiBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        taskBtn = findViewById(R.id.taskTodo);
        openAiBtn = findViewById(R.id.reachus);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, new tasks_todo_frag());

        loadFrag(new tasks_todo_frag(), 0);

        taskBtn.setOnClickListener(v -> {
            loadFrag(new tasks_todo_frag(), 1);
        });
        openAiBtn.setOnClickListener(v -> {
            loadFrag(new open_ai_frag(), 1);
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