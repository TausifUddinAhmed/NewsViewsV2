package com.example.tausif.newsviews.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.news.Article;
import java.util.List;

public class MainAdapter extends  RecyclerView.Adapter<MainAdapter.NewsViewHolder> {


     private  Context context;

     private List<Article> articles;


    public  MainAdapter(Context context, List<Article>articles){

             this.context = context;
             this.articles =articles;

    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.item_show_news_list, viewGroup, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {

        newsViewHolder.textViewTitle.setText(articles.get(i).getTitle());
        newsViewHolder.textViewAuthor.setText(articles.get(i).getAuthor());
        newsViewHolder.textViewDescription.setText(articles.get(i).getDescription());
        newsViewHolder.textViewPublishedDate.setText(articles.get(i).getPublishedAt());
        newsViewHolder.textViewUrl.setText(articles.get(i).getUrl());

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public  class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewNews;

        TextView textViewTitle;
        TextView textViewAuthor;
        TextView textViewDescription;
        TextView textViewUrl;
        TextView textViewPublishedDate;



        public NewsViewHolder(@NonNull View itemView) {

             super(itemView);

             textViewTitle = itemView.findViewById(R.id.text_view_title);
             textViewAuthor = itemView.findViewById(R.id.text_view_author);
             textViewPublishedDate = itemView.findViewById(R.id.text_view_published_date);
             textViewDescription = itemView.findViewById(R.id.text_view_description);
             textViewUrl = itemView.findViewById(R.id.text_view_url);


        }
    }
}
