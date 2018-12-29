package com.example.tausif.newsviews.ui.search;


public interface SearchResultViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayNumberOrDateTrivia(String result);
    void displayError();
}
