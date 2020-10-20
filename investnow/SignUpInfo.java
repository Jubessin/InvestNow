package com.example.android.investnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.android.investnow.SignUp.user_name;
import static com.example.android.investnow.SignUp2.email_address;
import static com.example.android.investnow.SignUp3.user_number;

public class SignUpInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_info);
        Button button = (Button) findViewById(R.id.continue_button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpInfo.this, SignUp3.class));
                System.out.println(user_name);
                System.out.println(email_address);
                System.out.println(user_number);
            }
        });
    }
}
