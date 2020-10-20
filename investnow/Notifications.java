package com.example.android.investnow;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.RingtonePreference;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import com.google.android.gms.common.util.Strings;

import static android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS;
import static android.provider.Settings.System.DEFAULT_RINGTONE_URI;
import static android.provider.Settings.System.RINGTONE;
import static android.provider.Settings.System.canWrite;
import static com.example.android.investnow.ForgotPsw.getLastActivity;

public class Notifications extends AppCompatActivity {
    ListView listView;
    Switch setting1, setting2;
    String chosenRingtone = "";
    public Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        setting1 = (Switch) findViewById(R.id.setting1);
        setting2 = (Switch) findViewById(R.id.setting2);
        SharedPreferences sp = getSharedPreferences("Notifications", 0);
        setting1.setChecked(sp.getBoolean("R_Emails", true));
        setting2.setChecked(sp.getBoolean("R_Notifs", true));
    }


    @Override
    public void onBackPressed() {
        System.out.println(setting1.isChecked());
        SharedPreferences sp = getSharedPreferences("Notifications", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("R_Emails", setting1.isChecked());
        editor.putBoolean("R_Notifs", setting2.isChecked());
        editor.apply();
        super.onBackPressed();
    }
}
