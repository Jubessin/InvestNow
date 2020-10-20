package com.example.android.investnow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp6 extends AppCompatActivity {
    TextView textView;
    ListView listView;
    public static String userPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up6);
        textView = (TextView) findViewById(R.id.goal_greeting);
        String greeting = "What would you like to learn about investment?";
        textView.setText(greeting);
        String []s = new String[] {"Where can I invest?", "How much should I invest?", "Who can I talk to about investing?", "Ways to save money", "Stocks"};
        listView = (ListView) findViewById(R.id.goal_list);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, s));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setItemChecked(0, true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ListView listView = (ListView) arg0;
                TextView tv = (TextView) listView.getChildAt(arg2);
                String s = tv.getText().toString();
            }
        });

        Button button = (Button) findViewById(R.id.continue_button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if (listView.getCheckedItemCount() > 0) {
                    SharedPreferences sp = getSharedPreferences("Interests", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Goals", userPrefs);
                    editor.apply();
                    startActivity(new Intent(SignUp6.this, SignUp7.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please select one or more items", Toast.LENGTH_SHORT).show();
                }
                System.out.println(userPrefs);

            }
        });
    }
}