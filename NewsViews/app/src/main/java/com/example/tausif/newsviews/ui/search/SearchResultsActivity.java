package com.example.tausif.newsviews.ui.search;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.news.NewsApiResponse;
import com.example.tausif.newsviews.model.number.NumberOrDateTriviaResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity implements SearchResultViewInterface {


    SearchResultsPresenter searchResultsPresenter;

    @BindView(R.id.text_view_number_result)
    TextView textViewNumberResult;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.search_result_toolbar)
    Toolbar toolBar;

    String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
//        getSupportActionBar().hide();
        ButterKnife.bind(this);

        setupMVP();

        setSupportActionBar(toolBar);
        ActionBar actionbar = getSupportActionBar();

        SearchResultsActivity.this.setTitle("Number Trivia");

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);

       // textViewNumberResult = findViewById(R.id.text_view_number_result);
        searchQuery = getIntent().getStringExtra("Searching_Value");

       // Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show();

        getNumberResult(searchQuery);

    }

    private void setupMVP() {
        searchResultsPresenter = new SearchResultsPresenter(this);
    }


    @Override
    public void showToast(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgressBar() {


        progressBar.setVisibility(View.VISIBLE);

    }

    public  void getNumberResult(String s){

        searchResultsPresenter.getNumberOrDateResults(s);
    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayNumberOrDateTrivia(String result) {

        textViewNumberResult.setText(result);

    }


    @Override
    public void displayError(String s) {

    }
}
