package com.example.ad2splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //    Animations
        Animation top,middle,bottom;
                top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
                middle= AnimationUtils.loadAnimation(this,R.anim.middle_animation);
                bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //      Hooks
        TextView first =findViewById(R.id.todo1);
        TextView second =findViewById(R.id.todo2);
        TextView third =findViewById(R.id.todo3);
        TextView fourth =findViewById(R.id.todo4);
        TextView fifth =findViewById(R.id.todo5);
        TextView sixth =findViewById(R.id.todo6);
        TextView seventh =findViewById(R.id.todo7);
        TextView eightth =findViewById(R.id.todo8);
        ImageView a=findViewById(R.id.logoimage);
        TextView slogan=findViewById(R.id.slogan);

        first.setAnimation(middle);
        second.setAnimation(top);
        third.setAnimation(bottom);
        fourth.setAnimation(top);
        fifth.setAnimation(middle);
        sixth.setAnimation(bottom);
        seventh.setAnimation(top);
        eightth.setAnimation(middle);
        a.setAnimation(bottom);
        slogan.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inext=new Intent(getApplicationContext(),loginSignUp.class);
                startActivity(inext);
            }
        },3000);


    }
}