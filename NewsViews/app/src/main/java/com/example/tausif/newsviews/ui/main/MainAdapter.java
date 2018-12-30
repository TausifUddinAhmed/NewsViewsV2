package com.example.tausif.newsviews.ui.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.news.Article;
import com.example.tausif.newsviews.ui.webview.WebViewActivity;

import java.util.List;

import static android.content.Intent.CATEGORY_BROWSABLE;

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



        Glide.with(context).load(articles.get(i).getUrlToImage())
                .thumbnail(0.5f)
                .into(newsViewHolder.imageViewNews);





        String author = context.getString(R.string.author) + " : " +articles.get(i).getAuthor();
        String  description = context.getString(R.string.description) + " : " +articles.get(i).getDescription();
        String  published_Date = context.getString(R.string.published_date) + " :" +articles.get(i).getPublishedAt();


        newsViewHolder.textViewTitle.setText(articles.get(i).getTitle());
        newsViewHolder.textViewAuthor.setText(author);
        newsViewHolder.textViewDescription.setText(description);
        newsViewHolder.textViewPublishedDate.setText(published_Date);
       // newsViewHolder.textViewUrl.setText(articles.get(i).getUrl());

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
        //TextView textViewUrl;
        TextView textViewPublishedDate;



        public NewsViewHolder(@NonNull View itemView) {

             super(itemView);

             imageViewNews = itemView.findViewById(R.id.image_view_news);
             textViewTitle = itemView.findViewById(R.id.text_view_title);
             textViewAuthor = itemView.findViewById(R.id.text_view_author);
             textViewPublishedDate = itemView.findViewById(R.id.text_view_published_date);
             textViewDescription = itemView.findViewById(R.id.text_view_description);
         //    textViewUrl = itemView.findViewById(R.id.text_view_url);


             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int position = getAdapterPosition();

                     //Toast.makeText(context, articles.get(position).getAuthor(), Toast.LENGTH_SHORT).show();

                     final String url = articles.get(position).getUrl();

                    // OpenAlertDilog();

                    // Resources.getSystem().getString(R.string.load_content);
                     AlertDialog.Builder builder = new AlertDialog.Builder(context);
                     builder.setTitle("NewsViews");
                     builder.setMessage("Load the content in your mobile browser ?");
                     builder.setPositiveButton("YES",
                             new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int which) {


                                    Toast.makeText(context, "OK was clicked", Toast.LENGTH_SHORT).show();
                                     Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                     browserIntent.addCategory(CATEGORY_BROWSABLE);
                                     context.startActivity(browserIntent);


                                 }
                             });
                     builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {

                             Intent intent = new Intent(context, WebViewActivity.class);
                             intent.putExtra("URL", url);
                             context.startActivity(intent);

                             //Toast.makeText(context, android.R.string.no, Toast.LENGTH_SHORT).show();
                         }
                     });


                     builder.setCancelable(false);
                     builder.show();
                 }
             });


        }



        }
}
