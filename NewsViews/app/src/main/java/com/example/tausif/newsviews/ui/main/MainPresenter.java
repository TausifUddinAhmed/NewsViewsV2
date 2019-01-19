package com.example.tausif.newsviews.ui.main;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tausif.newsviews.model.news.Article;
import com.example.tausif.newsviews.network.Api;
import com.example.tausif.newsviews.model.news.Data;
import com.example.tausif.newsviews.model.news.NewsApiResponse;
import com.example.tausif.newsviews.network.ServiceFactory;
import com.example.tausif.newsviews.ui.search.SearchResultsActivity;
import com.example.tausif.newsviews.utils.AppConfig;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    MainViewInterface mainViewInterface;
   // private String TAG = "MainPresenter";

    private static final String TAG = "GOOGLE SIGN IN";

    private GoogleSignInClient googleSignInClient;



    Context context;

    List<Article> articles;

    public MainPresenter(Context context, MainViewInterface mainViewInterface) {

        this.context = context;
        this.mainViewInterface = mainViewInterface;
        articles = new ArrayList<>();
    }


    @Override
    public void getNews() {

        mainViewInterface.showProgressBar();
        Api service = ServiceFactory.createRetrofitService(Api.class, AppConfig.BASE_NEWS_URL);


        for(String newsPortal : Data.newsPortalList) {
            service.getNews(newsPortal, AppConfig.API_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NewsApiResponse>() {

                        @Override
                        public final void onCompleted() {

                            mainViewInterface.hideProgressBar();

                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("NewsPortalResponse", e.getMessage());
                            mainViewInterface.displayError(e.getMessage());
                        }


                        @Override
                        public final void onNext(NewsApiResponse response) {


                            articles.addAll(response.getArticles());

                            mainViewInterface.displayNews(articles);

                            Log.e("NewsPortalResponse :  ", response.getArticles().toString());



                        }
                    });


        }

    }

    @Override
    public void goSearchResultActivity(String s) {

        Intent intent  =  new Intent(context,SearchResultsActivity.class);
        intent.putExtra("Searching_Value", s);
        context.startActivity(intent);


    }

    @Override
    public void signInGoogle() {


        mainViewInterface.googleSignInResult();

    }


}

