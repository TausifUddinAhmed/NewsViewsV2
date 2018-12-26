package com.example.tausif.newsviews.ui.search;

import android.util.Log;

import com.example.tausif.newsviews.model.news.NewsApiResponse;
import com.example.tausif.newsviews.model.number.NumberOrDateTriviaResponse;
import com.example.tausif.newsviews.network.Api;
import com.example.tausif.newsviews.network.ServiceFactory;
import com.example.tausif.newsviews.utils.AppConfig;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchResultsPresenter implements  SearchResultsPresenterInterface {

    SearchResultViewInterface searchResultViewInterface ;

    SearchResultsPresenter(SearchResultViewInterface searchResultViewInterface){

        this.searchResultViewInterface = searchResultViewInterface;


    }

    @Override
    public void getNumberOrDateResults( String numberOrDate) {

        searchResultViewInterface.showProgressBar();
        Api service = ServiceFactory.createRetrofitService(Api.class, AppConfig.BASE_NUMBER_URL);


            service.getNumberOrDate(numberOrDate,AppConfig.QUERY_STRING_JSON)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NumberOrDateTriviaResponse>() {

                        @Override
                        public final void onCompleted() {

                            searchResultViewInterface.hideProgressBar();


                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("Number Response", e.getMessage());
                        }


                        @Override
                        public final void onNext(NumberOrDateTriviaResponse response) {




                            searchResultViewInterface.displayNumberOrDateTrivia(response);
                            Log.e("Number Response  ", response.getText());



                        }
                    });




    }
}
