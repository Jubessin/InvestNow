package com.example.android.investnow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.android.investnow.SignUp6.userPrefs;

public class SaveInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_info);
    }


    public void saveLastClicked(String title, String date, String content, String image){
        try {
            FileOutputStream fileOutputStream;
            fileOutputStream = getApplicationContext().openFileOutput("Title", MODE_PRIVATE);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
//        try {
//            dfos = openFileOutput(fdate, MODE_PRIVATE);
//            dfos.write(date.getBytes());
//            dfos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//
////            FileOutputStream tfos = openFileOutput(ftitle, MODE_PRIVATE);
////            tfos.write("F".getBytes());
////            tfos.close();
//
////            FileOutputStream dfos = openFileOutput(fdate, MODE_PRIVATE);
////            dfos.write(date.getBytes());
////            dfos.close();
////
////            FileOutputStream cfos = openFileOutput(fcontent, MODE_PRIVATE);
////            cfos.write(content.getBytes());
////            cfos.close();
////
////            FileOutputStream ifos = openFileOutput(fimage, MODE_PRIVATE);
////            ifos.write(image.getBytes());
////            ifos.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
