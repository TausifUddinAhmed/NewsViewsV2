package com.example.tausif.newsviews.ui;


import android.util.Log;
import com.example.tausif.newsviews.network.Api;
import com.example.tausif.newsviews.model.Data;
import com.example.tausif.newsviews.model.NewsApiResponse;
import com.example.tausif.newsviews.network.ServiceFactory;
import com.example.tausif.newsviews.utils.AppConfig;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    MainViewInterface mvi;
    private String TAG = "MainPresenter";

    public MainPresenter(MainViewInterface mvi) {

        this.mvi = mvi;
    }


    @Override
    public void getNews() {

        mvi.showProgressBar();
        Api service = ServiceFactory.createRetrofitService(Api.class, AppConfig.BASE_SERVER_URL);


        for(String newPortal : Data.newsPortalList) {
            service.getNews(newPortal, AppConfig.API_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NewsApiResponse>() {

                        @Override
                        public final void onCompleted() {
                            // do nothing

                            mvi.hideProgressBar();

                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("NewsPortalResponse", e.getMessage());
                        }


                        @Override
                        public final void onNext(NewsApiResponse response) {
                            // mCardAdapter.addData(response);

                            mvi.displayNews(response)   ;

                            Log.e("NewsPortalResponse :  ", response.getArticles().toString());



                        }
                    });


        }




    }

}

