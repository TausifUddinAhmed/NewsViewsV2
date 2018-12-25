package com.example.tausif.newsviews.controller;

import android.util.Log;

import com.example.tausif.newsviews.model.Data;
import com.example.tausif.newsviews.apiinterface.Api;
import com.example.tausif.newsviews.callbackinterface.NewsApiListener;
import com.example.tausif.newsviews.model.NewsApiResponse;
import com.example.tausif.newsviews.network.ServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import utils.AppConfig;


import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
public class NewsApiController {


    List<Observable<?>> requests = new ArrayList<>();


    private NewsApiListener mNewsHeadlineListener = null;


    public NewsApiController(NewsApiListener newsHeadlineListener) {

        mNewsHeadlineListener = newsHeadlineListener;


    }


    public void start_rxjava() {

//        getObservable().subscribeWith(getObserver());


//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConfig.BASE_SERVER_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build();
//
//
//        Api newsApi = retrofit.create(Api.class);

//
//        requests.add(newsApi.getNews(AppConfig.SOURCES, AppConfig.API_KEY));
//        requests.add(newsApi.getNews(AppConfig.SOURCES, AppConfig.API_KEY));
//        requests.add(newsApi.getNews(AppConfig.SOURCES, AppConfig.API_KEY));
//
//
//        // Zip all requests with the Function, which will receive the results.
//        Observable.zip(
//                requests,
//                new Function<Object[], Object>() {
//                    @Override
//                    public Object apply(Object[] objects) throws Exception {
//                        // Objects[] is an array of combined results of completed requests
//
//
//
//                        // do something with those results and emit new event
//                        return new Object();
//                    }
//                })
//
//                // After all requests had been performed the next observer will receive the Object, returned from Function
//                .subscribe(
//                        // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
//                        new Consumer<Object>() {
//                            @Override
//                            public void accept(Object o) throws Exception {
//                                //Do something on successful completion of all requests
//                            }
//                        },
//
//                        // Will be triggered if any error during requests will happen
//                        new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable e) throws Exception {
//                                //Do something on error completion of requests
//                            }
//                        }
//
//
//                );



          }



//    public Observable<NewsApiResponse> getObservable(){
//
//        return NetworkClient.getRetrofit().create(Api.class)
//                .getNews(AppConfig.SOURCES,AppConfig.API_KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public DisposableObserver<NewsApiResponse> getObserver(){
//        return new DisposableObserver<NewsApiResponse>() {
//
//            @Override
//            public void onNext(@NonNull NewsApiResponse movieResponse) {
//                Log.e(TAG,"OnNext "+movieResponse.getStatus());
//            //    mvi.displayMovies(movieResponse);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d(TAG,"Error"+e);
//                e.printStackTrace();
//              //  mvi.displayError("Error fetching Movie Data");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG,"Completed");
//                //mvi.hideProgressBar();
//            }
//        };
//    }

//    public  void  last_try(){
//
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConfig.BASE_SERVER_URL)
//                .client(client)
//                .build();
//
//        Observable<ResponseType1> observable1 = retrofit.;
//        Observable<ResponseType2> observable2 = retrofit.getApi_b();
//        Observable<ResponseType3> observable3 = retrofit.getApi_c();
//
//
//    }


    public void start(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        Api service = ServiceFactory.createRetrofitService(Api.class, AppConfig.BASE_SERVER_URL);

//        Api newsApi = retrofit.create(Api.class);

        for(String newPortal : Data.newsPortalList) {
            service.getNews(newPortal, AppConfig.API_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NewsApiResponse>() {

                        @Override
                        public final void onCompleted() {
                            // do nothing
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("NewsPortalResponse", e.getMessage());
                        }


                        @Override
                        public final void onNext(NewsApiResponse response) {
                           // mCardAdapter.addData(response);

                            Log.e("NewsPortalResponse :  ", response.getArticles().toString());



                        }
                    });
        }


     }

}




