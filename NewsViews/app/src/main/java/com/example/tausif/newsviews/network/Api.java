package com.example.tausif.newsviews.network;


import com.example.tausif.newsviews.model.news.NewsApiResponse;
import com.example.tausif.newsviews.model.number.DateTriviaResponse;
import com.example.tausif.newsviews.model.number.NumberTriviaResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface Api {



    @GET("/everything")
    Observable<NewsApiResponse> getNews(@Query("q") String q, @Query("apikey") String apiKey);


    @GET("/{number}")
    Observable<NumberTriviaResponse> getNumberTrivia(@Path(value = "number") String number, @Query("json") String json);


    @GET("/{month}/{day}")
    Observable<DateTriviaResponse> getDateTrivia(@Path(value = "month") String month,
                                                 @Path(value = "day") String day,
                                                 @Query("json") String json);


}
