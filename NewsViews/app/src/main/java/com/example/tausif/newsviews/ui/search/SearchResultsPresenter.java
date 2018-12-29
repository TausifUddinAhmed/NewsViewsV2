package com.example.tausif.newsviews.ui.search;

import android.util.Log;
import android.widget.Toast;

import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.number.DateTriviaResponse;
import com.example.tausif.newsviews.model.number.NumberTriviaResponse;
import com.example.tausif.newsviews.network.Api;
import com.example.tausif.newsviews.network.ServiceFactory;
import com.example.tausif.newsviews.utils.AppConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchResultsPresenter implements  SearchResultsPresenterInterface {




    SearchResultViewInterface searchResultViewInterface ;

    SearchResultsPresenter(SearchResultViewInterface searchResultViewInterface){

        this.searchResultViewInterface = searchResultViewInterface;


    }

    @Override
    public void getNumberOrDateResults(String numberOrDate) {

        searchResultViewInterface.showProgressBar();

        String regex = "[0-9]+";

        if(numberOrDate.matches(regex)) {


            Api service = ServiceFactory.createRetrofitService(Api.class, AppConfig.BASE_NUMBER_URL);

            service.getNumberTrivia(numberOrDate, AppConfig.QUERY_STRING_JSON)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NumberTriviaResponse>() {

                        @Override
                        public final void onCompleted() {

                            searchResultViewInterface.hideProgressBar();


                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("Number Response", e.getMessage());
                            searchResultViewInterface.showToast("Invaild Number/Date");
                            searchResultViewInterface.hideProgressBar();

                        }


                        @Override
                        public final void onNext(NumberTriviaResponse response) {


                            searchResultViewInterface.displayNumberOrDateTrivia(response.getText());
                            Log.e("Number Response  ", response.getText());


                        }
                    });


        }


        else  if(numberOrDate.matches("^(?i)(\\d{2}/\\d{2})$")){

            System.out.println("write format");

            String[] parts = numberOrDate.split("/");
            String partOne = parts[0];
            String partTwo = parts[1];


            searchResultViewInterface.showProgressBar();
        Api service = ServiceFactory.createRetrofitService(Api.class, AppConfig.BASE_NUMBER_URL);


        service.getDateTrivia( partOne,partTwo,AppConfig.QUERY_STRING_JSON)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DateTriviaResponse>() {

                    @Override
                    public final void onCompleted() {

                        searchResultViewInterface.hideProgressBar();


                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Date Response", e.getMessage());
                        searchResultViewInterface.showToast("Invaild Number/Date");
                        searchResultViewInterface.hideProgressBar();

                    }


                    @Override
                    public final void onNext(DateTriviaResponse response) {




                        searchResultViewInterface.displayNumberOrDateTrivia(response.getText());
                        Log.e("Date Response  ", response.getText());



                    }
                });


        }

        else {



            searchResultViewInterface.hideProgressBar();
            searchResultViewInterface.displayError();

        }




   }






}
