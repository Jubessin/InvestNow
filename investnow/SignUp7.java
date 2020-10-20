package com.example.android.investnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.investnow.SignUp.user_name;

public class SignUp7 extends AppCompatActivity {
    public static String user_password;
    private String password;
    private boolean checked;

    private TextView textView;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up7);
        Button button = (Button) findViewById(R.id.continue_button8);
        final EditText create_psw = (EditText) findViewById(R.id.create_password);
        final EditText check_psw = (EditText) findViewById(R.id.create_password2);
        textView = (TextView) findViewById(R.id.psw_greeting);
        String greeting = "Hey " + user_name + ", please create a password.";
        textView.setText(greeting);
        checkBox = (CheckBox) findViewById(R.id.show_psw);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkBox.isChecked()) {
                    create_psw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    check_psw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    check_psw.setSelection(check_psw.getText().length());
                }
                if (checkBox.isChecked()) {
                    System.out.println("NOTCHECKED");
                    create_psw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    check_psw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    check_psw.setSelection(check_psw.getText().length());
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = create_psw.getText().toString();
                if (check_psw.getText().toString().matches(password)){
                    if (password.length() >= 8) {
                        user_password = password;
                        startActivity(new Intent(SignUp7.this, CompleteSignUp.class));
                    }
                    else {
                        Toast toast = (Toast.makeText(getApplicationContext(), "Please use up to 8 characters.", Toast.LENGTH_SHORT));
                        toast.show();
                    }
                }
                else {
                    check_psw.setText("");
                    Toast toast = (Toast.makeText(getApplicationContext(), "Sorry, the passwords do not match.", Toast.LENGTH_SHORT));
                    toast.show();
                }

            }
        });
    }


}
