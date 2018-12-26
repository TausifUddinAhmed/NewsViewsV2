package com.example.tausif.newsviews.ui.search;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.news.NewsApiResponse;
import com.example.tausif.newsviews.model.number.NumberOrDateTriviaResponse;

public class SearchResultsActivity extends AppCompatActivity implements SearchResultViewInterface {


    SearchResultsPresenter searchResultsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        getSupportActionBar().hide();
        setupMVP();
    }

    private void setupMVP() {
        searchResultsPresenter = new SearchResultsPresenter(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();

            searchResultsPresenter.getNumberOrDateResults("2");
            //use the query to search your data somehow
        }
    }



    @Override
    public void showToast(String s) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayNumberOrDateTrivia(NumberOrDateTriviaResponse numberOrDateTriviaResponse) {

    }


    @Override
    public void displayError(String s) {

    }
}
