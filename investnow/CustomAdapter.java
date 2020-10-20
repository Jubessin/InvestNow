package com.example.android.investnow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomAdapter extends BaseAdapter {
    Context context;
    public String[]titletxt;
    public String[]imagetxt;
    public LayoutInflater layoutInflater;
    public CustomAdapter(Context appContext, String[] title, String[] image){
        this.context = appContext;
        this.titletxt = title;
        this.imagetxt = image;
        layoutInflater = (LayoutInflater.from(appContext));
    }
    @Override
    public int getCount() {
        return titletxt.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.list_view1, null);
        TextView title = (TextView)   view.findViewById(R.id.n_title);
        ImageView image = (ImageView)   view.findViewById(R.id.image);
        title.setText(titletxt[position]);
        Picasso.get().load(imagetxt[position]).fit().into(image);
        return view;
    }
}
