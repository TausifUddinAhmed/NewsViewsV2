package com.example.tausif.newsviews.network;


import com.example.tausif.newsviews.model.news.NewsApiResponse;
import com.example.tausif.newsviews.model.number.NumberOrDateTriviaResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface Api {



    @GET("/everything")
    Observable<NewsApiResponse> getNews(@Query("q") String q, @Query("apikey") String apiKey);


    @GET("/{value}")
    Observable<NumberOrDateTriviaResponse> getNumberOrDate(@Path(value = "value") String value, @Query("json") String json);



}
