package com.example.tausif.newsviews.ui.main;

import com.example.tausif.newsviews.model.news.Article;

import java.util.List;


public interface MainViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayNews(List<Article> articleList);
    void displayError(String s);


}
