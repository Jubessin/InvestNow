package com.example.android.investnow.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.investnow.Interface.ItemClickListener;
import com.example.android.investnow.R;
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

class LibraryAdapterHolder extends ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    private Context context;
    private String[] titletxt;
    private String[] contenttxt;
    private String[] datetxt;
    private String[] imagetxt;
    public TextView title, content, date;
    public ImageView imageView;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public LibraryAdapterHolder(@NonNull View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.lib_title);
        content = (TextView) view.findViewById(R.id.lib_content);
        date = (TextView)view.findViewById(R.id.lib_date);
        imageView = (ImageView)view.findViewById(R.id.lib_image);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }
}

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapterHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    public static final String libtitle = "LibTitle";
    public static final String libdate = "LibDate";
    public static final String libcontent = "LibContent";
    public static final String libimage = "LibImage";
    public String[] titles = {""};
    public String[] contents = {""};
    public String[] dates = {""};
    public String[] images = {"", ""};
    public int i = 0;
    public LibraryAdapter(Context mContext, String[] title, String[] content, String[] date, String[] image){
        this.mContext = mContext;
        this.titles = title;
        image = this.images;
        this.contents = content;
        this.dates = date;
        inflater = LayoutInflater.from(mContext);

        try {
            FileInputStream tfis = mContext.openFileInput(libtitle);
            InputStreamReader tisr = new InputStreamReader(tfis);

            BufferedReader tbr = new BufferedReader(tisr);
            StringBuffer tsb = new StringBuffer();

            String tlines;
            while ((tlines = tbr.readLine()) != null){
                tsb.append(tlines);

            }

            FileInputStream dfis = mContext.openFileInput(libdate);
            InputStreamReader disr = new InputStreamReader(dfis);

            BufferedReader dbr = new BufferedReader(disr);
            StringBuffer dsb = new StringBuffer();

            String dlines;
            while ((dlines = dbr.readLine()) != null){
                dsb.append(dlines);

            }

            FileInputStream cfis = mContext.openFileInput(libcontent);
            InputStreamReader cisr = new InputStreamReader(cfis);

            BufferedReader cbr = new BufferedReader(cisr);
            StringBuffer csb = new StringBuffer();

            String clines;
            while ((clines = cbr.readLine()) != null){
                csb.append(clines);

            }

            FileInputStream ifis = mContext.openFileInput(libimage);
            InputStreamReader iisr = new InputStreamReader(ifis);

            BufferedReader ibr = new BufferedReader(iisr);
            StringBuffer isb = new StringBuffer();

            String ilines;
            while ((ilines = ibr.readLine()) != null){
                isb.append(ilines);

            }
            if ((!tsb.toString().isEmpty() && (!cbr.toString().isEmpty()))) {
                image[0] = isb.toString();
                title[0] = tsb.toString();
                date[0] = dsb.toString();
                content[0] = csb.toString();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @NonNull
        @Override
        public LibraryAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
            View itemView = inflater.inflate(R.layout.row, parent, false);
            return new LibraryAdapterHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LibraryAdapterHolder libraryAdapterHolder, int position) {

//            holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
//            holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
//            holder.txtContent.setText(rssObject.getItems().get(position).getContent());
//            holder.setItemClickListener(new ItemClickListener() {
//                @Override
//                public void onClick(View view, int position, boolean isLongClick) {
//                    if (!isLongClick){
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
//                        String image = rssObject.getItems().get(position).enclosure.toString().replace("{link=", "").replace("}", "");
//                        String title = rssObject.getItems().get(position).title;
//                        String date = rssObject.getItems().get(position).pubDate;
//                        String content = rssObject.getItems().get(position).content;
//                        saveLast(title, date, content, image);
//                        view.getContext().startActivity(browserIntent);
//                    }
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

//
//    @Override
//    public int getCount() {
//        return titletxt.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        view = layoutInflater.inflate(R.layout.row, null);
//        TextView title = (TextView) view.findViewById(R.id.lib_title);
//        TextView content = (TextView) view.findViewById(R.id.lib_content);
//        TextView date = (TextView)view.findViewById(R.id.lib_date);
//        ImageView imageView = (ImageView)view.findViewById(R.id.lib_image);
//        Picasso.get().load(imagetxt[position]).fit().noFade().into(imageView);
//        title.setText(titletxt[position]);
//        content.setText(contenttxt[position]);
//        date.setText(datetxt[position]);
//        return view;
//    }

