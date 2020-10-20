package com.example.android.investnow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.android.investnow.ForgotPsw.getLastActivity;
import static com.example.android.investnow.ForgotPsw.last_activity;

public class AccountInfo extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        listView = (ListView) findViewById(R.id.account_info_list);
        String[]strings = {"Change Interests", "Change Password"};
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings) );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println('f');
                if(position == 0){
                    startActivity(new Intent(AccountInfo.this, UserInterests.class));
                    finish();
                }
                if(position == 1){
                    getLastActivity(this.getClass());
                    startActivity(new Intent(AccountInfo.this, ForgotPsw.class));
                }
            }
        });
    }
}
