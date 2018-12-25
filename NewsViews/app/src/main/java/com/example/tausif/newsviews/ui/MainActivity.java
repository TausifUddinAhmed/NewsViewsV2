package com.example.tausif.newsviews.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.NewsApiResponse;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainViewInterface {


    MainPresenter mainPresenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupMVP();
        getNewsList();
    }


    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void getNewsList() {

        mainPresenter.getNews();

    }

    private void GetNew() {


//        //Progress dialog initialization so that users doesn't get bored :P
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Loading..."); // Setting Message
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
//        progressDialog.show(); // Display Progress Dialog
//        progressDialog.setCancelable(false);

        //controller class initialization and called the method of class


    }


    @Override
    public void showToast(String s) {

    }

    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayNews(NewsApiResponse newsApiResponse) {

    }

    @Override
    public void displayError(String s) {

    }
}
