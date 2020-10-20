package com.example.android.investnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class HelpFeedback extends AppCompatActivity {
    ListView listView;
    TextView textView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_feedback);
        textView = (TextView)findViewById(R.id.help_textview);
        searchView = (SearchView)findViewById(R.id.help_searchview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                searchView.performClick();
//                searchView.setPressed(true);
//                searchView.onActionViewExpanded();
                textView.setVisibility(View.GONE);
                textView.setFocusable(false);
                textView.setClickable(false);
                textView.setFocusableInTouchMode(false);
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.onActionViewCollapsed();
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                textView.setVisibility(View.VISIBLE);
                searchView.onActionViewCollapsed();
                textView.setFocusableInTouchMode(true);
                textView.setFocusable(true);
                textView.setClickable(true);
                return false;
            }
        });

        String[] articles = {"Sign in/out", "Adding Articles to Library", "Deleting your account"};
        listView = (ListView)findViewById(R.id.help_listview);
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.help_list_layout, R.id.k, articles));
    }
}
