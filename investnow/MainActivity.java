package com.example.android.investnow;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.android.investnow.Adapter.FeedAdapter.fcontent;
import static com.example.android.investnow.Adapter.FeedAdapter.fdate;
import static com.example.android.investnow.Adapter.FeedAdapter.fimage;
import static com.example.android.investnow.Adapter.FeedAdapter.ftitle;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout, main_layout;
    ViewPager viewPager;
    public static boolean logged = false;
    ImageView imageView;
    TextView newsTitle, newsContext, newsPubDate;
    Button home, news, recommended, library, profile;
    CardView last_saw;
    ScrollView scrollView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        imageView = (ImageView)findViewById(R.id.news_img);
        newsTitle = (TextView)findViewById(R.id.txtTitle);
        newsContext = (TextView)findViewById(R.id.txtContent);
        newsPubDate = (TextView)findViewById(R.id.txtPubDate);
        last_saw = (CardView)findViewById(R.id.last_saw);
        scrollView = (ScrollView)findViewById(R.id.main_scrollv);
        main_layout = (LinearLayout)findViewById(R.id.main_llayoutv);

        try {
            FileInputStream tfis = openFileInput(ftitle);
            InputStreamReader tisr = new InputStreamReader(tfis);

            BufferedReader tbr = new BufferedReader(tisr);
            StringBuffer tsb = new StringBuffer();

            String tlines;
            while ((tlines = tbr.readLine()) != null){
                tsb.append(tlines);

            }

            FileInputStream dfis = openFileInput(fdate);
            InputStreamReader disr = new InputStreamReader(dfis);

            BufferedReader dbr = new BufferedReader(disr);
            StringBuffer dsb = new StringBuffer();

            String dlines;
            while ((dlines = dbr.readLine()) != null){
                dsb.append(dlines);

            }

            FileInputStream cfis = openFileInput(fcontent);
            InputStreamReader cisr = new InputStreamReader(cfis);

            BufferedReader cbr = new BufferedReader(cisr);
            StringBuffer csb = new StringBuffer();

            String clines;
            while ((clines = cbr.readLine()) != null){
                csb.append(clines);

            }

            FileInputStream ifis = openFileInput(fimage);
            InputStreamReader iisr = new InputStreamReader(ifis);

            BufferedReader ibr = new BufferedReader(iisr);
            StringBuffer isb = new StringBuffer();

            String ilines;
            while ((ilines = ibr.readLine()) != null){
                isb.append(ilines);

            }
            if ((!tsb.toString().isEmpty() && (!cbr.toString().isEmpty()))) {
                newsTitle.setText(tsb.toString());
                newsPubDate.setText(dsb.toString());
                newsContext.setText(csb.toString());
                Picasso.get().load(isb.toString()).fit().noFade().into(imageView);

            }
            else{
                main_layout.removeView(last_saw);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        linearLayout = (LinearLayout) findViewById(R.id.button_panel);
        linearLayout.bringToFront();
        viewPager = (ViewPager) findViewById(R.id.acc_view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.setImages(new Integer[]{R.drawable.slide3, R.drawable.slide2});
        viewPager.setClickable(true);
        viewPager.setAdapter(viewPagerAdapter);
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });

        news = (Button) findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, News.class));
                logged = false;

            }
        });

        recommended = (Button) findViewById(R.id.recommended);
        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Recommended.class));
                logged = false;
            }
        });

        library = (Button) findViewById(R.id.library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Library.class));
                logged = false;
            }
        });

        profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserProfile.class));
                logged = false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        startActivity(new Intent(MainActivity.this, MenuScreen.class));
    }

}
