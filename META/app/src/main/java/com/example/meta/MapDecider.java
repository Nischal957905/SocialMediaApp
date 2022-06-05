package com.example.meta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MapDecider extends AppCompatActivity {

    TextView logMapper;
    TextView signMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_decider);

        logMapper = (TextView) findViewById(R.id.mapperLog);
        signMapper = (TextView) findViewById(R.id.mapperSign);

        logMapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apperanceLog = new Intent(MapDecider.this,MainActivity.class);
                startActivity(apperanceLog);
            }
        });

        signMapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apperanceSign = new Intent(MapDecider.this,GetTallied.class);
                startActivity(apperanceSign);
            }
        });
    }
}