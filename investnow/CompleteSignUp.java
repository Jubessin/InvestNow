package com.example.android.investnow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Transport;

import static com.example.android.investnow.SignUp.isAlpha;
import static com.example.android.investnow.SignUp.user_name;
import static com.example.android.investnow.SignUp2.email_address;
import static com.example.android.investnow.SignUp2.isEmailValid;
import static com.example.android.investnow.SignUp3.user_number;
import static com.example.android.investnow.SignUp6.userPrefs;
import static com.example.android.investnow.SignUp7.user_password;


public class CompleteSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private static final String nfilename = "user_full_name";
    public static final String efilename = "user_email";
    private static final String pfilename = "user_phone_number";
    public static final String pwfilename = "user_password";
    private static final String cfilename = "user_country";
    private static final String afilename = "user_age";
    private static final String gfilename = "user_goal";
    TextView textView;
    private EditText name, number, email_a, password;
    CheckBox terms_box, email_box;
    private String country, age, goal;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_sign_up);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        textView = (TextView) findViewById(R.id.complete_greeting);
        String greeting = user_name + ", is this okay?";
        textView.setText(greeting);
        name = (EditText) findViewById(R.id.name_confirm);
        number = (EditText) findViewById(R.id.number_confirm);
        email_a = (EditText) findViewById(R.id.email_confirm);
        name.setText(user_name);
        email_a.setText(email_address);
        number.setText(user_number);
        terms_box = (CheckBox) findViewById(R.id.terms);
        terms_box.setBackgroundResource(R.drawable.checkbox);
        terms_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences;
            }
        });

        email_box = (CheckBox) findViewById(R.id.receive_mail);
        email_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences;
            }
        });
        // Spinner element
        final Spinner cspinner = (Spinner) findViewById(R.id.country_confirm);
        // Spinner click listener
        cspinner.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Canada");
        categories.add("United States");
        categories.add("United Kingdom");

        // Creating adapter for cspinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to cspinner
        cspinner.setAdapter(dataAdapter);



        // Spinner element
        Spinner aspinner = (Spinner) findViewById(R.id.age_confirm);
        // Spinner click listener
        aspinner.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> acategories = new ArrayList<String>();
        acategories.add("13 - 19");
        acategories.add("20 - 26");
        acategories.add("27 - 35");
        acategories.add("36+");

        // Creating adapter for aspinner
        ArrayAdapter<String> adataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, acategories);

        // Drop down layout style - list view with radio button
        adataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to aspinner
        aspinner.setAdapter(adataAdapter);




    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(view.getId());
        if ((parent.getSelectedItem().toString().matches("Canada" )) || (parent.getSelectedItem().toString().matches("United States")) || parent.getSelectedItem().toString().matches("United Kingdom")) {
            country = parent.getItemAtPosition(position).toString();
        }

        else {
            age = parent.getItemAtPosition(position).toString();
        }
        System.out.println(country);
        System.out.println(age);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void createAccount(String email, String password, String user_name) {
        final String n = user_name;
        final String u_email = email_a.getText().toString();
        String u_phone = number.getText().toString();
        final Mail sender = new Mail("investmefiy@gmail.com", "JustinEkene123");
        if (isNetworkAvailable()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if (user != null) {

                                    UserProfileChangeRequest builder = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(n)
                                            .build();
                                    user.updateProfile(builder)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG", "User profile updated.");
                                            }
                                        }
                                    });

                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d("Email", "Email sent.");
                                                        Toast.makeText(getApplicationContext(), "Verification Email Sent.", Toast.LENGTH_LONG).show();
                                                        startActivity(new Intent(CompleteSignUp.this, MainActivity.class));
                                                        finish();
                                                    }
                                                }
                                            });
                                }


                                }
//
                            else {
                                // If sign in fails, display a message to the user.
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                Log.e("Error", "onComplete: ", e);
                            }

                            // ...
                        }
                    });
        }
        else {
            Toast.makeText(this, "Unable to sign-up without network connection.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        button = (Button) findViewById(R.id.complete_button);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String u_name = name.getText().toString();
                final String u_email = email_a.getText().toString();
                String u_phone = number.getText().toString();


                try {
                    FileOutputStream nfos = openFileOutput(nfilename, MODE_PRIVATE);
                    nfos.write(u_name.getBytes());
                    nfos.close();

                    FileOutputStream pfos = openFileOutput(pfilename, MODE_PRIVATE);
                    pfos.write(u_phone.getBytes());
                    pfos.close();

                    FileOutputStream cfos = openFileOutput(cfilename, MODE_PRIVATE);
                    cfos.write(country.getBytes());
                    cfos.close();

                    FileOutputStream afos = openFileOutput(afilename, MODE_PRIVATE);
                    afos.write(age.getBytes());
                    afos.close();

                    SharedPreferences.Editor editor = getSharedPreferences("User_Goals", MODE_PRIVATE).edit();
                    editor.putString("User_Prefs", userPrefs);
                    editor.apply();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (u_name.isEmpty()){
                    Toast toast = (Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT));
                    toast.show();
                }

                if ((!isAlpha(u_name)) || (u_name.length() > 14)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a valid name", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (!isEmailValid(u_email)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please provide a valid email.", Toast.LENGTH_SHORT);
                    toast.show();
                }

                if ((!u_name.isEmpty()) && (isAlpha(u_name)) && (u_name.length() <= 14) && (isEmailValid(u_email)) && terms_box.isChecked()) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Looper.prepare();


                            try {
                                createAccount(u_email, user_password, u_name);
                            } catch (Exception e) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Email not sent.", Toast.LENGTH_SHORT);
                                toast.show();
                                Log.e("SendMail", e.getMessage(), e);
                            }

                        }
                    }).start();

                }






            }


        });
    }




    }


