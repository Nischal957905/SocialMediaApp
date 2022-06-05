package com.example.meta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class GetTallied extends AppCompatActivity {

    Button btnFinalize;
    TextInputEditText saveNameDb;
    TextInputEditText saveLastNameDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_tallied);

        btnFinalize = (Button) findViewById(R.id.finalizeBtn);
        saveNameDb = (TextInputEditText) findViewById(R.id.dbNameSaver);
        saveLastNameDb = (TextInputEditText) findViewById(R.id.dbLastNameSaver);

        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetailsDb();
            }
        });
    }

    private void saveDetailsDb() {
        String obtainNameFirst = saveNameDb.getText().toString().trim();
        String obtainNameLast = saveLastNameDb.getText().toString().trim();



        if(obtainNameLast.isEmpty()) {
            saveLastNameDb.setError("Your Last name cannot be empty.");
        }
        if(obtainNameFirst.isEmpty()) {
            saveNameDb.setError("Your first name cannot be empty.");
        }
        else if(!obtainNameFirst.isEmpty() && !obtainNameLast.isEmpty()){
            Intent finishRegi = new Intent(GetTallied.this,FinalTally.class);
            finishRegi.putExtra("dbFirstName",obtainNameFirst);
            finishRegi.putExtra("dbLastName",obtainNameLast);
            startActivity(finishRegi);
        }
    }
}