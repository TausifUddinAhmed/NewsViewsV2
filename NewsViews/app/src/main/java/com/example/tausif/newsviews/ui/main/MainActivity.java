package com.example.tausif.newsviews.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.news.Article;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainViewInterface,SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {


    MainPresenter mainPresenter;
    private  MainAdapter mainAdapter;


    private DrawerLayout drawerLayout;


    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.searchbar_toolbar)
    Toolbar toolBar;


    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);


        configureNavigationDrawer();
        configureToolbar();
        setupMVP();
        setupSwipeRefreshLayout();




        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

            //  getNewsList();
             }
        });



    }

    private void configureToolbar() {
        setSupportActionBar(toolBar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    private  void  setupSwipeRefreshLayout(){

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);

        this.menu = menu;


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return  true;
            case  R.id.search:
                return  true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

       //close search option when go to search result activity
        MenuItem searchItm = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItm.getActionView();

        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    private void configureNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

//                Fragment f = null;
//                int itemId = menuItem.getItemId();
//
//                if (itemId == R.id.refresh) {
//                    f = new RefreshFragment();
//                } else if (itemId == R.id.stop) {
//                    f = new StopFragment();
//                }
//
//                if (f != null) {
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame, f);
//                    transaction.commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }

                return false;
            }
        });
    }

    private void setupMVP() {

        mainPresenter = new MainPresenter(MainActivity.this, this);
    }

    private void getNewsList() {

       mainPresenter.getNews();

    }


    @Override
    public void showToast(String s) {

    }

    @Override
    public void showProgressBar() {

        mSwipeRefreshLayout.setRefreshing(true);


    }

    @Override
    public void hideProgressBar() {

        mSwipeRefreshLayout.setRefreshing(false);
        // progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayNews(List<Article> articleList) {

        mainAdapter = new MainAdapter(this,articleList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainAdapter);


    }


    // for search
    @Override
    public void displayError(String s) {

        Toast.makeText(this, "Error "  + s, Toast.LENGTH_SHORT).show();
    }




    @Override
    public boolean onQueryTextSubmit(String s) {

       // Toast.makeText(this, "Query Inserted  "+s, Toast.LENGTH_SHORT).show();
        mainPresenter.goSearchResultActivity(s);
        return true;

    }

    @Override
    public boolean onQueryTextChange(String s) {

        return true;
    }

    @Override
    public void onRefresh() {
        //getNewsList();
    }
}
