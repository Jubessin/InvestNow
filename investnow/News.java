package com.example.android.investnow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.android.investnow.Adapter.FeedAdapter;
import com.example.android.investnow.Common.HTTPDataHandler;
import com.example.android.investnow.Model.RSSObject;
import com.google.gson.Gson;

import static com.example.android.investnow.NewsFeed.RSS_link;

public class News extends AppCompatActivity {
    public boolean new_feed = false;
    public int rssInt = 1;
    LinearLayout linearLayout;
    Button home, news, recommended, library, profile;

    RecyclerView recyclerView;
    RSSObject rssObject;
    ListView listView;
//    private String[] RSS_link = {"https://rss.nytimes.com/services/xml/rss/nyt/YourMoney.xml", "https://www.whitecoatinvestor.com/feed/", "http://rss.reddit.com/r/investing/.rss"};
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";
    public String[] sites = {"The College Investor", "Millennial Money", "Bay Street Blog", "The Penny Hoarder", ""};
    public String[] siteImgs =
            {"https://cdn.thecollegeinvestor.com/wp-content/uploads/2017/11/TCIaudioshow.jpg", "https://millennialmoney.com/wp-content/uploads/2015/10/cropped-mm1-32x32.jpg", "http://www.baystreetblog.com/wp-content/uploads/2016/11/baystreet-profile-picture.jpg", "https://www.thepennyhoarder.com", ""};
    public String[] links = {"https://thecollegeinvestor.com/feed/","https://millennialmoney.com/feed/"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView = (ListView)findViewById(R.id.news_list_view);
        listView.setAdapter(new CustomAdapter(getApplicationContext(), sites, siteImgs));
//        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        loadRSS(rssInt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] s = {""};
                if (siteImgs[position].contains("/wp")) {
                    s = siteImgs[position].split("/wp");
                }
                else{
                    s[0] = siteImgs[position];
                }
                if (s[0].contains(".com")) {
                    s[0] = s[0].replace(".com", ".com/feed/");
                }
                if (s[0].contains(".ca")){
                    s[0] = s[0].replace(".ca", ".ca/feed/");
                }
                if (s[0].contains(".uk")){
                    s[0] = s[0].replace(".uk", ".uk/feed/");
                }
                RSS_link = s[0];
                System.out.println(s[0]);
                startActivity(new Intent(News.this, NewsFeed.class));
            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.button_panel);
        linearLayout.bringToFront();

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(News.this, MainActivity.class));
            }
        });

        news = (Button) findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(News.this, News.class));
                finish();
            }
        });

        recommended = (Button) findViewById(R.id.recommended);
        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(News.this, Recommended.class));
            }
        });

        library = (Button) findViewById(R.id.library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(News.this, Library.class));
            }
        });

        profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(News.this, UserProfile.class));
            }
        });
    }

//    private void loadRSS(int i) {
//        i = rssInt;
//        AsyncTask<String, String, String> loadRSSASync = new AsyncTask<String, String, String>() {
//            ProgressDialog mDialog = new ProgressDialog(News.this);
//
//            @Override
//            protected void onPreExecute() {
//                mDialog.setMessage("Please wait...");
//                mDialog.show();
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                String result;
//                HTTPDataHandler http = new HTTPDataHandler();
//                result = http.getHTTPData(params[0]);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                mDialog.dismiss();
//                rssObject = new Gson().fromJson(s, RSSObject.class);
//                FeedAdapter adapter = new FeedAdapter(rssObject, getBaseContext());
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            }
//        };
//        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
//        url_get_data.append(RSS_link[0]);
//        loadRSSASync.execute(url_get_data.toString());
//        System.out.println(RSS_link.length);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
////    public void loadNewRSSFeed(){
////        loadRSS();
////    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.menu_refresh){
//            loadRSS(rssInt);
//        }
//        return true;
//    }

}
