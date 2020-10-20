package com.example.android.investnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.android.investnow.Adapter.LibraryAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.android.investnow.Adapter.LibraryAdapter.libcontent;
import static com.example.android.investnow.Adapter.LibraryAdapter.libdate;
import static com.example.android.investnow.Adapter.LibraryAdapter.libimage;
import static com.example.android.investnow.Adapter.LibraryAdapter.libtitle;

public class Library extends AppCompatActivity {
    LinearLayout linearLayout;
    Button home, news, recommended, library, profile;
    RecyclerView recyclerView;
    public String[] titles = {""};
    public String[] contents = {""};
    public String[] dates = {""};
    public String[] images = {""};
    private LayoutInflater inflater;
    public int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        linearLayout = (LinearLayout) findViewById(R.id.button_panel);
        linearLayout.bringToFront();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);



        try {
            FileInputStream tfis = openFileInput(libtitle);
            InputStreamReader tisr = new InputStreamReader(tfis);

            BufferedReader tbr = new BufferedReader(tisr);
            StringBuffer tsb = new StringBuffer();

            String tlines;
            while ((tlines = tbr.readLine()) != null){
                tsb.append(tlines);

            }

            FileInputStream dfis = openFileInput(libdate);
            InputStreamReader disr = new InputStreamReader(dfis);

            BufferedReader dbr = new BufferedReader(disr);
            StringBuffer dsb = new StringBuffer();

            String dlines;
            while ((dlines = dbr.readLine()) != null){
                dsb.append(dlines);

            }

            FileInputStream cfis = openFileInput(libcontent);
            InputStreamReader cisr = new InputStreamReader(cfis);

            BufferedReader cbr = new BufferedReader(cisr);
            StringBuffer csb = new StringBuffer();

            String clines;
            while ((clines = cbr.readLine()) != null){
                csb.append(clines);

            }

            FileInputStream ifis = openFileInput(libimage);
            InputStreamReader iisr = new InputStreamReader(ifis);

            BufferedReader ibr = new BufferedReader(iisr);
            StringBuffer isb = new StringBuffer();

            String ilines;
            while ((ilines = ibr.readLine()) != null){
                isb.append(ilines);

            }
//                for (int ib = 0; ib < images.length; ib+= 1) {
//                    titles[ib] = tsb.toString();
//                    dates[ib] = dsb.toString();
//                    contents[ib] = csb.toString();
//
//                }
            images[0] = isb.toString();
            String[] placeholder;
                String[] string = new String[]{"","","","",""};

                int i = 0;

                for (String s = "jpg"; images[0].contains("jpg"); images[0] = images[0].replaceFirst("jpg", "")){

                    i += 1;
                    System.out.println(i);

                }
            images[0] = images[0].replaceAll(".https", "jpg\nhttps");
//            System.out.println(images[0]);

            for (i = i; i > 0; i -= 1 ){
                placeholder = images[0].split("jpg");
                string[i] = placeholder[0];
                System.out.println(string[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(new LibraryAdapter(getApplicationContext(), titles, contents, dates, images));


        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Library.this, MainActivity.class));
            }
        });

        news = (Button) findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Library.this, News.class));
            }
        });

        recommended = (Button) findViewById(R.id.recommended);
        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Library.this, Recommended.class));
            }
        });

        library = (Button) findViewById(R.id.library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Library.this, Library.class));
                finish();
            }
        });

        profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Library.this, UserProfile.class));
            }
        });
    }
}