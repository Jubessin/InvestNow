package com.example.android.investnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.investnow.SignUp.user_name;

public class SignUp2 extends AppCompatActivity {
    public static String email_address;
    TextView textView;
    private static final String filename = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        Button button = (Button) findViewById(R.id.continue_button2);
        final EditText email = (EditText) findViewById(R.id.email);
        textView = (TextView) findViewById(R.id.email_greeting);
        String greeting = "Hey " + user_name + ", what's your email?";
        textView.setText(greeting);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_address = email.getText().toString();
                if (!isEmailValid(email_address)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please provide a valid email.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (isEmailValid(email_address)) {
                    startActivity(new Intent(SignUp2.this, SignUpInfo.class));
                }



            }
        });


    }

    public final static boolean isEmailValid(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
