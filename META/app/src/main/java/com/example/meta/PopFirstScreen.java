package com.example.meta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class PopFirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_first_screen);

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent openThePopUp = new Intent(PopFirstScreen.this,MapDecider.class);
                startActivity(openThePopUp);
                finish();
            }
        },4000);
    }
}