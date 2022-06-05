package com.example.meta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView takeMapSign;
    Button mapAfterLogin;
    TextInputEditText checkEmailInput;
    TextInputEditText checkPassInput;
    private FirebaseAuth allowAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takeMapSign = (TextView) findViewById(R.id.logMapSign);
        mapAfterLogin = (Button) findViewById(R.id.complyBtn);
        checkEmailInput = (TextInputEditText) findViewById(R.id.checkEmailVal);
        checkPassInput = (TextInputEditText) findViewById(R.id.checkPassVal);
        allowAccess = FirebaseAuth.getInstance();

        takeMapSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logSignMapper = new Intent(MainActivity.this,GetTallied.class);
                startActivity(logSignMapper);
            }
        });

        mapAfterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String obtainValEmail = checkEmailInput.getText().toString().trim();
                String obtainValPass = checkPassInput.getText().toString().trim();

                if(obtainValPass.isEmpty()) {
                    checkPassInput.setError("This field needs to be filled.");
                }
                if(obtainValEmail.isEmpty()){
                    checkEmailInput.setError("This field needs to be filled.");
                }
                else{
                    allowAccess.signInWithEmailAndPassword(obtainValEmail,obtainValPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Opps! Looks like wrong Inputs.", Toast.LENGTH_SHORT).show();
                                    }
                                    if(task.isSuccessful()){
                                        Intent mapLoginAfter = new Intent(MainActivity.this,AfterLoginProfile.class);
                                        startActivity(mapLoginAfter);
                                    }

                                }
                            });
                }
            }
        });
    }
}