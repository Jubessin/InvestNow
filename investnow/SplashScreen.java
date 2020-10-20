package com.example.android.investnow;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    public int timer = 0;
    public boolean bool = false;
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private FirebaseAuth mAuth;
    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (mAuth.getCurrentUser() == null) {
                    Intent mainIntent = new Intent(SplashScreen.this, MenuScreen.class);
                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();
                }
                else{
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    SplashScreen.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}


//    public void inc (){
//        timer += 1;
//    }
//
//    Thread t = new Thread(new Runnable() {
//        public void run() {
//            while (timer < 100000){
//                timer += 1;
//                System.out.println(timer);
//            }
//
//            if (timer == 100000){
//                bool = true;
//            }
//
//            if (bool){
//                Log.d("Dibud", "Ready");
//                timer += 1;
//                startActivity(new Intent(SplashScreen.this, MainActivity.class));
//            }
//        }
//    });
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        t.run();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        timer = 0;
//    }
