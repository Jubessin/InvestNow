package com.example.android.investnow.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.investnow.Interface.ItemClickListener;
import com.example.android.investnow.Model.RSSObject;
import com.example.android.investnow.R;
import com.example.android.investnow.SaveInfo;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
{

    public TextView txtTitle, txtPubDate, txtContent;
    public ImageView imageView;
    public Button button;
    private ItemClickListener itemClickListener;

    public FeedViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
        txtPubDate = (TextView)itemView.findViewById(R.id.txtPubDate);
        txtContent = (TextView)itemView.findViewById(R.id.txtContent);
        imageView = (ImageView)itemView.findViewById(R.id.news_img);
        button = (Button)itemView.findViewById(R.id.add_to_lib);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
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
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    private RSSObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;
    public static final String ftitle = "Title";
    public static final String fdate = "Date";
    public static final String fcontent = "Content";
    public static final String fimage = "Image";

    public static String libtitle = "LibTitle";
    public static String libdate = "LibDate";
    public static String libcontent = "LibContent";
    public static String libimage = "LibImage";

    public FeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = inflater.inflate(R.layout.row, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedViewHolder holder, int position) {

        System.out.println(rssObject.getItems().get(position).getGuid());
        if ((rssObject.getItems().get(position).thumbnail.contains("h")) || rssObject.getItems().get(position).enclosure.toString().contains("htt")) {
            if (!rssObject.getItems().get(position).thumbnail.toString().isEmpty()) {
                Picasso.get().load(rssObject.getItems().get(position).thumbnail.toString().replace("{link=", "").replace("}", "")).noFade().fit().into(holder.imageView);
            } else {
                Picasso.get()
                        .load((rssObject.getItems().get(position).enclosure.toString().replace("{link=", "").replace("}", "")))
                        .noFade()
                        .fit()
                        .into(holder.imageView);
            }
        }
        else{
            if (rssObject.getFeed().getImage().contains("htt")){
            Picasso.get().load(rssObject.getFeed().getImage().toString()).fit().noFade().into(holder.imageView);
        }
            else{
                Picasso.get().load(rssObject.getItems().get(position).image).fit().noFade().into(holder.imageView);
            }
        }
        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
        String[] pholder = {""};
        pholder[0] = rssObject.getItems().get(position).getDescription().replaceAll("<p>", "").replace("</p>", "");
        pholder = pholder[0].split("<i>");
        holder.txtContent.setText(pholder[0]);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image = rssObject.getItems().get(holder.getAdapterPosition()).enclosure.toString().replace("{link=", "").replace("}", "");
                String title = rssObject.getItems().get(holder.getAdapterPosition()).title;
                String date = rssObject.getItems().get(holder.getAdapterPosition()).pubDate;
                String content = rssObject.getItems().get(holder.getAdapterPosition()).description.replaceAll("<p>", "").replace("</p>", "");
                addToLib(title, date, content, image);
            }
        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick){
                    String image;
                    if ((rssObject.getItems().get(position).thumbnail.contains("h")) || rssObject.getItems().get(position).enclosure.toString().contains("htt")) {
                        if (!rssObject.getItems().get(position).thumbnail.toString().isEmpty()) {
                            image = rssObject.getItems().get(position).thumbnail.toString().replace("{link=", "").replace("}", "");
                        } else {
                            image = rssObject.getItems().get(position).enclosure.toString().replace("{link=", "").replace("}", "");
                        }
                    }
                    else{
                        image = rssObject.getFeed().getImage().toString().replaceAll("g/", "g");
                    }
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    String title = rssObject.getItems().get(position).title;
                    String date = rssObject.getItems().get(position).pubDate;
                    String content = rssObject.getItems().get(position).description.replaceAll("<p>,", "").replace("</p", "");
                    saveLast(title, date, content, image);
                    view.getContext().startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rssObject.getItems().size();
    }

    public void saveLast(String title, String date, String content, String image){
        try {
            FileOutputStream tfos = mContext.openFileOutput(ftitle, Context.MODE_PRIVATE);
            tfos.write(title.getBytes());
            tfos.close();

            FileOutputStream dfos = mContext.openFileOutput(fdate, Context.MODE_PRIVATE);
            dfos.write(date.getBytes());
            dfos.close();

            FileOutputStream cfos = mContext.openFileOutput(fcontent, Context.MODE_PRIVATE);
            cfos.write(content.getBytes());
            cfos.close();

            FileOutputStream ifos = mContext.openFileOutput(fimage, Context.MODE_PRIVATE);
            ifos.write(image.getBytes());
            ifos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addToLib(String title, String date, String content, String image){
        try {
            FileOutputStream tfos = mContext.openFileOutput(libtitle, Context.MODE_APPEND);
            tfos.write(title.getBytes());
            tfos.close();

            FileOutputStream dfos = mContext.openFileOutput(libdate, Context.MODE_APPEND);
            dfos.write(date.getBytes());
            dfos.close();

            FileOutputStream cfos = mContext.openFileOutput(libcontent, Context.MODE_APPEND);
            cfos.write(content.getBytes());
            cfos.close();

            FileOutputStream ifos = mContext.openFileOutput(libimage, Context.MODE_APPEND);
            ifos.write(image.getBytes());
            ifos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(title);
        System.out.println(date);
        System.out.println(content);
        System.out.println(image);
    }
}
