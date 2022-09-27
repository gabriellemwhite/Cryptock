package com.gabriellewhite.cryptock.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.gabriellewhite.cryptock.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private static ArrayList<NewsModel> articles = new ArrayList<>();
    private final Context context;
    static Integer articlesPosition;
    ItemListener itemListener;

    public NewsAdapter(ArrayList<NewsModel> articles, Context context) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // final String category = articles.get(position).getCategory();
        Integer datetime = articles.get(position).getDatetime();
        String headline = articles.get(position).getHeadline();
        String source = articles.get(position).getSource();
        String summary = articles.get(position).getSummary();

        articlesPosition = viewHolder.getLayoutPosition();

        //newsUrl1 = newsUrl;
        // viewHolder.newsUrl.setText(newsUrl);
        viewHolder.datetime.setText(convertTimeStampToDay(datetime));
        viewHolder.headline.setText(headline);
        viewHolder.source.setText(source);
        viewHolder.summary.setText(summary);
        viewHolder.hours.setText(convertTimeStampToHours(datetime));


        Glide.with(context)
                .load(articles.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.ic_newspaper)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.imageView);


        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onItemClick(null, view, viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface ItemListener {
        void onItemClick(AdapterView<?> adapterView, View view, int position);
    }

    public void setOnItemClickListener(ItemListener itemListener){
        this.itemListener = itemListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView datetime, headline, source, summary, hours;
        ImageView imageView;
        ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            datetime = view.findViewById(R.id.datetime);
            headline = view.findViewById(R.id.headline);
            source = view.findViewById(R.id.source);
            summary = view.findViewById(R.id.summary);
            hours = view.findViewById(R.id.simpletime);
            progressBar = view.findViewById(R.id.progress_bar);

        }

    }

    private static String convertTimeStampToDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance(); // create Calendar
        calendar.setTimeInMillis(timeStamp * 1000); // set time

        // SimpleDateFormat that returns the day's name
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        return dateFormatter.format(calendar.getTime());
    }

    private static String convertTimeStampToHours(long timeStamp) {
        Calendar calendar = Calendar.getInstance(); // create Calendar
        calendar.setTimeInMillis(timeStamp * 1000); // set time

        // SimpleDateFormat that returns the day's name
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
        return dateFormatter.format(calendar.getTime());
    }

}