package com.example.tausif.newsviews.ui.search;


import com.example.tausif.newsviews.model.number.NumberOrDateTriviaResponse;

public interface SearchResultViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayNumberOrDateTrivia(NumberOrDateTriviaResponse numberOrDateTriviaResponse);
    void displayError(String s);
}
