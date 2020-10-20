package com.example.android.investnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.investnow.SignUp.user_name;

public class SignUp3 extends AppCompatActivity {
    public static String user_number;
    TextView textView;
    private static final String filename = "user_p_number";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);
        textView = (TextView) findViewById(R.id.number_greeting);
        String greeting = "Hey " + user_name + ", what's your phone number?";
        textView.setText(greeting);
        Button button = (Button) findViewById(R.id.continue_button4);
        final EditText p_number = (EditText) findViewById(R.id.p_number);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_number = p_number.getText().toString();
                startActivity(new Intent(SignUp3.this, SignUp4.class));
            }
        });
    }
}
