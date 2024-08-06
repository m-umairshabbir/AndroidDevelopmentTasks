package com.example.taskstodo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView first;
        TextView second;
        TextView third;
        TextView fourth;
        TextView fifth;
        TextView sixth;
        TextView seventh;
        TextView eight;
        ImageView a;
        TextView slogan;

        Animation top,middle,bottom;

        //    Hiding bars
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //    Animations
//      Hooks
            first=findViewById(R.id.todo1);
            second =findViewById(R.id.todo2);
            third =findViewById(R.id.todo3);
            fourth =findViewById(R.id.todo4);
            fifth =findViewById(R.id.todo5);
            sixth =findViewById(R.id.todo6);
            seventh =findViewById(R.id.todo7);
            eight =findViewById(R.id.todo8);
            a=findViewById(R.id.logoimage);
            slogan=findViewById(R.id.slogan);


//      Loading    Animations
            top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
            middle= AnimationUtils.loadAnimation(this,R.anim.middle_animation);
            bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
//      Setting animations
            first.setAnimation(middle);
            second.setAnimation(top);
            third.setAnimation(bottom);
            fourth.setAnimation(top);
            fifth.setAnimation(middle);
            sixth.setAnimation(bottom);
            seventh.setAnimation(top);
            eight.setAnimation(middle);
            a.setAnimation(bottom);
            slogan.setAnimation(bottom);

//      Handler to move on main page
            new Handler().postDelayed(() -> {
                SharedPreferences pref=getSharedPreferences("session",MODE_PRIVATE);
                Boolean check=pref.getBoolean("flag",false);
                Intent next;
                if(check){
                    next=new Intent(splash.this,MainActivity.class);


                }else{
                    next=new Intent(splash.this, landingPage.class);

                }
                startActivity(next);

            },3000);




        }
}