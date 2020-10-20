package com.example.android.investnow;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.android.investnow.CompleteSignUp.efilename;
import static com.example.android.investnow.CompleteSignUp.pwfilename;
import static com.example.android.investnow.MainActivity.logged;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String u_email;
    private String u_password;
    private static final String ufilename = "user_full_name";
    EditText email_address, i_password;
    Button button;
    CheckBox checkBox;
    TextView full_name, f_password;
    public String exclamation = "!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        if (!isNetworkAvailable()){
            Toast.makeText(this, "Signing in requires network connection.", Toast.LENGTH_SHORT).show();
        }

        full_name = (TextView) findViewById(R.id.user_name);
        f_password = (TextView) findViewById(R.id.forgot_password);
        f_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPsw.getLastActivity(LogIn.class);
                startActivity(new Intent(LogIn.this, ForgotPsw.class));

            }
        });
        mAuth = FirebaseAuth.getInstance();
        try {
            FileInputStream fis = openFileInput(ufilename);
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();

            String lines;
            while ((lines = br.readLine()) != null){
                sb.append(lines);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void signIn(String email, final String password) {
        if (isNetworkAvailable()) {
            if ((email.isEmpty() || password.isEmpty())) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please enter required information.", Toast.LENGTH_SHORT);
                toast.show();

            }
            if ((!email.isEmpty() && !password.isEmpty())) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        if (user.isEmailVerified()) {
                                            startActivity(new Intent(LogIn.this, MainActivity.class));
                                            finish();

                                            logged = true;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Please verify your email.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    i_password.setText("");
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        }
        else{
            Toast.makeText(this, "Unable to sign-in without network connection.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        email_address = (EditText) findViewById(R.id.email_input);
        i_password = (EditText) findViewById(R.id.password_input);
        checkBox = (CheckBox) findViewById(R.id.show_psw2);
//        try {
//            FileInputStream fis = openFileInput(pwfilename);
//            InputStreamReader isr = new InputStreamReader(fis);
//
//            BufferedReader br = new BufferedReader(isr);
//            StringBuffer sb = new StringBuffer();
//
//            String lines;
//            while ((lines = br.readLine()) != null){
//                sb.append(lines);
//            }
//            u_password = sb.toString();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            FileInputStream fis = openFileInput(efilename);
//            InputStreamReader isr = new InputStreamReader(fis);
//
//            BufferedReader br = new BufferedReader(isr);
//            StringBuffer sb = new StringBuffer();
//
//            String lines;
//            while ((lines = br.readLine()) != null){
//                sb.append(lines);
//            }
//            u_email = sb.toString();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        button = (Button) findViewById(R.id.log_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(email_address.getText().toString(), i_password.getText().toString());
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()){
                    i_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    checkBox.setButtonDrawable(R.mipmap.ic_visibility);
                }
                else{
                    i_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    checkBox.setButtonDrawable(R.mipmap.ic_visibility_off);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LogIn.this, MenuScreen.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(LogIn.this, MenuScreen.class));
        finish();
        return true;
    }
}
