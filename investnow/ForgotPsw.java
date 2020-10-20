package com.example.android.investnow;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;

import static com.example.android.investnow.SignUp2.isEmailValid;

public class ForgotPsw extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button button;
    public static Class last_activity;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_psw);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.forgot_psw_email);
        button = (Button) findViewById(R.id.forgot_psw_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmailValid(email.getText().toString())) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please provide a valid email.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(ForgotPsw.this, getLastActivity(last_activity)));
                                        finish();
                                        Log.d("Email", "Email sent.");
                                    }
                                }
                            });
                }
            }
        });


    }
    public static Class getLastActivity(Class p_activity){
        last_activity = p_activity;
        return last_activity;
    }
}
