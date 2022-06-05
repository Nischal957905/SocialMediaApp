package com.example.meta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FinalTally extends AppCompatActivity {

    Button logReverseMap;
    TextInputEditText dbPushEmail;
    TextInputEditText dbPushPassword;
    private FirebaseAuth allowAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_tally);

        logReverseMap = (Button) findViewById(R.id.mapReverseLog);
        allowAccess = FirebaseAuth.getInstance();

        dbPushEmail = (TextInputEditText) findViewById(R.id.dbSaveEmail);
        dbPushPassword = (TextInputEditText) findViewById(R.id.dbSavePass);

        logReverseMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushDetails();
            }
        });
    }

    private void pushDetails() {
        String firstObtainName = getIntent().getStringExtra("dbFirstName");
        String lastObtainName = getIntent().getStringExtra("dbLastName");
        String emailObtain = dbPushEmail.getText().toString().trim();
        String passObtain = dbPushPassword.getText().toString().trim();

        if(emailObtain.isEmpty()) {
            dbPushEmail.setError("Email address is required.");
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailObtain).matches()){
            dbPushEmail.setError("Valid email address is required.");
        }

        if(passObtain.isEmpty()) {
            dbPushPassword.setError("Password field cannot be empty.");
        }
        else if(!emailObtain.isEmpty() && !passObtain.isEmpty()){
            allowAccess.createUserWithEmailAndPassword(emailObtain, passObtain).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        User validUser = new User(firstObtainName, lastObtainName, emailObtain);
                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(validUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(FinalTally.this, "You have been registered.", Toast.LENGTH_SHORT).show();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent logReverseMapper = new Intent(FinalTally.this,MainActivity.class);
                                            startActivity(logReverseMapper);
                                            finish();
                                        }
                                    },2000);
                                }
                                else{
                                    Toast.makeText(FinalTally.this, "Something went wrong. Try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(FinalTally.this, "Something went wrong. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}