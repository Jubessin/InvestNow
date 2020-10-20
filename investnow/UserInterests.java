package com.example.android.investnow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.investnow.SignUp6.userPrefs;

public class UserInterests extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interests);
        String []s = new String[] {"Where can I invest?", "How much should I invest?", "Who can I talk to about investing?", "Ways to save money", "Stocks"};
        listView = (ListView) findViewById(R.id.interests_list);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, s));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ListView listView = (ListView) arg0;
                TextView tv = (TextView) listView.getChildAt(arg2);
                String s = tv.getText().toString();
            }

        });
        SharedPreferences gsp = getSharedPreferences("Interests", 0);
        if(gsp.getString("Goals", "").contains("Where")) {
            listView.setItemChecked(0, true);
        }
        if(gsp.getString("Goals", "").contains("How")){
            listView.setItemChecked(1, true);
        }
        if(gsp.getString("Goals", "").contains("Who")){
            listView.setItemChecked(2, true);
        }
        if(gsp.getString("Goals", "").contains("Ways")){
            listView.setItemChecked(3, true);
        }
        if(gsp.getString("Goals", "").contains("Stocks")){
            listView.setItemChecked(4, true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SparseBooleanArray spa = listView.getCheckedItemPositions();
        System.out.println(listView.getAdapter().getCount());
        StringBuffer str = new StringBuffer();
        for(int i = 0; i < listView.getAdapter().getCount(); i++){
            if(spa.get(i)){
                String s = ((TextView) listView.getChildAt(i)).getText().toString() + "\n";
                str = str.append(s);
                userPrefs = str.toString();

            }
        }
        System.out.println(userPrefs);
        if (listView.getCheckedItemCount() > 0) {
            SharedPreferences sp = getSharedPreferences("Interests", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Goals", userPrefs);
            editor.apply();
            startActivity(new Intent(UserInterests.this, AccountInfo.class));
            finish();
            return false;
        }
        else{
            Toast.makeText(getApplicationContext(), "Please select one or more items", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        SparseBooleanArray spa = listView.getCheckedItemPositions();
        System.out.println(listView.getAdapter().getCount());
        StringBuffer str = new StringBuffer();
        for(int i = 0; i < listView.getAdapter().getCount(); i++){
            if(spa.get(i)){
                String s = ((TextView) listView.getChildAt(i)).getText().toString() + "\n";
                str = str.append(s);
                userPrefs = str.toString();


            }
        }
        System.out.println(userPrefs);
        if (listView.getCheckedItemCount() > 0) {
            SharedPreferences sp = getSharedPreferences("Interests", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Goals", userPrefs);
            editor.apply();
            startActivity(new Intent(UserInterests.this, AccountInfo.class));
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Please select one or more items", Toast.LENGTH_SHORT).show();
        }
    }
}
