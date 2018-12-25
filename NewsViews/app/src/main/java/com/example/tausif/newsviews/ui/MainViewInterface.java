package com.example.tausif.newsviews.ui;

import com.example.tausif.newsviews.model.NewsApiResponse;





public interface MainViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayNews(NewsApiResponse newsApiResponse);
    void displayError(String s);
}
