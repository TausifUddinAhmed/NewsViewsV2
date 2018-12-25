package com.example.tausif.newsviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tausif.newsviews.callbackinterface.NewsApiListener;
import com.example.tausif.newsviews.controller.NewsApiController;
import com.example.tausif.newsviews.model.Article;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NewsApiListener {


     NewsApiController newsApiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





//        Observable
//                .merge(getMaleObservable(), getFemaleObservable())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new Observer<User>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(User user) {
//                        Log.e("TAG", user.getName() + ", " + user.getGender());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


        GetNew();


    }

    private void GetNew() {


//        //Progress dialog initialization so that users doesn't get bored :P
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Loading..."); // Setting Message
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
//        progressDialog.show(); // Display Progress Dialog
//        progressDialog.setCancelable(false);

        //controller class initialization and called the method of class
        newsApiController = new NewsApiController(this);
        newsApiController.start();


    }

    @Override
    public void GetNewsHeadLinesResponseSuccessful(List<Article> articleList) {

    }

    @Override
    public void GetHeadNewsLinesResponseUnsuccessful(String message) {

    }


}
