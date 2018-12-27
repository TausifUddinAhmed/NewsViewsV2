package com.example.tausif.newsviews.ui.main;


import android.util.Log;

import com.example.tausif.newsviews.model.news.Article;
import com.example.tausif.newsviews.network.Api;
import com.example.tausif.newsviews.model.news.Data;
import com.example.tausif.newsviews.model.news.NewsApiResponse;
import com.example.tausif.newsviews.network.ServiceFactory;
import com.example.tausif.newsviews.utils.AppConfig;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    MainViewInterface mainViewInterface;
    private String TAG = "MainPresenter";

    List<Article> articles;

    public MainPresenter(MainViewInterface mainViewInterface) {

        this.mainViewInterface = mainViewInterface;
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
                        }


                        @Override
                        public final void onNext(NewsApiResponse response) {



                            mainViewInterface.displayNews(response.getArticles())   ;

                            Log.e("NewsPortalResponse :  ", response.getArticles().toString());



                        }
                    });


        }

    }

}

