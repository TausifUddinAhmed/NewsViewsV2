package com.example.tausif.newsviews.network;


import com.example.tausif.newsviews.model.NewsApiResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface Api {


    // Ex https://newsapi.org/v2/top-headlines?sources=bbc-news&apikey=69bd6f42f32f43e3ba76ec9cd250743b
//    @GET("top-headlines")
//    Call<NewsHeadLineResponses> getHeadLines(@Query("sources") String sources, @Query("apikey") String apiKey);
//
//    @GET("everything")
//    Call<NewsApiResponse> getNews(@Query("q") String q, @Query("apikey") String apiKey);

    @GET("/everything")
    Observable<NewsApiResponse> getNews(@Query("q") String q, @Query("apikey") String apiKey);


}
