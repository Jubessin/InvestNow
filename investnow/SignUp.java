package com.example.android.investnow;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class SignUp extends AppCompatActivity {
    public String in_user_name;
    public String cap;
    public static String user_name;
    public String greeting = "Hello, what's your name?";
    EditText first_name;
    private static final String filename = "user_full_name";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_close){
            startActivity(new Intent(SignUp.this, MenuScreen.class));
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUp.this, MenuScreen.class));
        finish();
    }

    public void capFirstChar(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        user_name = name;
        System.out.println(user_name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Button button = (Button) findViewById(R.id.continue_button);
        first_name = (EditText) findViewById(R.id.first_name);
        textView = (TextView) findViewById(R.id.greeting);
        textView.setText(greeting);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();



                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            in_user_name = first_name.getText().toString();

                            if (!hasActiveInternetConnection(getApplicationContext())) {
                                Toast.makeText(getApplicationContext(), "Unable to sign-up without network connection.", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignUp.this, MenuScreen.class));
                            }

                            else {

                                if (in_user_name.isEmpty()) {
                                    Toast toast = (Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT));
                                    toast.show();
                                }

                                if ((!isAlpha(in_user_name)) || (in_user_name.length() > 14)) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a valid name", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                if ((!in_user_name.isEmpty()) && (isAlpha(in_user_name)) && in_user_name.length() <= 14) {
                                    capFirstChar(in_user_name);
                                    startActivity(new Intent(SignUp.this, SignUp2.class));
                                }
                            }

                        }
                    });



//                hasActiveInternetConnection(getApplicationContext());
            }
        }).start();
    }

    public boolean hasActiveInternetConnection (Context context){
        if (isNetworkAvailable()) try {
            HttpURLConnection urlc = (HttpURLConnection)
                    (new URL("http://clients3.google.com/generate_204")
                            .openConnection());
            return (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
        } catch (IOException e) {
            Log.e("T", "Error checking internet connection", e);
        }
        else {
            Log.d("T", "No network available!");
        }

        return false;
    }


        }
