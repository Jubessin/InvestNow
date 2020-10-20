package com.example.android.investnow;

import android.content.Intent;
import android.net.Network;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import static com.example.android.investnow.SignUp.user_name;

public class UserProfile extends AppCompatActivity {
    LinearLayout linearLayout;
    private FirebaseAuth mAuth;
    TextView nameView, emailView;
    ListView listView;
    Button home, news, recommended, library, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        UserProfileChangeRequest builder = new UserProfileChangeRequest.Builder()
                .build();
        linearLayout = (LinearLayout) findViewById(R.id.button_panel);
        linearLayout.bringToFront();
        nameView = (TextView) findViewById(R.id.user_p_name);
        mAuth = FirebaseAuth.getInstance();
        System.out.println(mAuth.getCurrentUser().getDisplayName());
        nameView.setText(mAuth.getCurrentUser().getDisplayName());
        emailView = (TextView) findViewById(R.id.user_p_email);
        emailView.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());



        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });

        news = (Button) findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, News.class));
            }
        });

        recommended = (Button) findViewById(R.id.recommended);
        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, Recommended.class));
            }
        });

        library = (Button) findViewById(R.id.library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, Library.class));
            }
        });

        profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, UserProfile.class));
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.profile_list);
        String[]strings = {"Account Information", "Notifications", "Network usage", "Settings", "Help & Feedback", "Share!"};
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings) );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println();
                if(position == 0){
                    startActivity(new Intent(UserProfile.this, AccountInfo.class));
                }

                if(position == 1){
                    startActivity(new Intent(UserProfile.this, Notifications.class));
                }

                if(position == 2){
                    startActivity(new Intent(UserProfile.this, NetworkUsage.class));
                }

                if(position == 3){
                    startActivity(new Intent(UserProfile.this, AppSettings.class));
                }

                if(position == 4){
                    startActivity(new Intent(UserProfile.this, HelpFeedback.class));
                }

                if(position == 5){
                    startActivity(new Intent(UserProfile.this, ShareApp.class));
                }
            }
        });
    }
}