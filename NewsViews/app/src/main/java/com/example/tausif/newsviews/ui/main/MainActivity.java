package com.example.tausif.newsviews.ui.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tausif.newsviews.R;
import com.example.tausif.newsviews.model.news.Article;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

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


    TextView textViewUsername;
    TextView textViewUserMail;


    private Menu menu;


    private static final String TAG = "GOOGLE SIGN IN";

    private GoogleSignInClient googleSignInClient;

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
                getNewsList();
             }
        });


        mainAdapter = new MainAdapter(this);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainAdapter);

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
    protected void onResume() {
        super.onResume();



    }

    private void configureNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                if (itemId == R.id.home) {

                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    finish();

                }else if (itemId == R.id.google_sign_in) {

                    mainPresenter.signInGoogle();


                }else if (itemId == R.id.about) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("NewsViews");
                    builder.setMessage("App Version 1.0 \nTo google sign in, you need to use gmail which are you already logged in play store \nAs It uses developer " +
                            "plan, Limited api call is supported " );
                    builder.setPositiveButton("0K",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {




                                }
                            });


                    builder.setCancelable(false);
                    builder.show();
                }

                else if (itemId == R.id.exit) {

                    finish();
                }


                return false;
            }
        });

        View header=navView.getHeaderView(0);

        textViewUsername = (TextView)header.findViewById(R.id.text_view_username);
        textViewUserMail= (TextView)header.findViewById(R.id.text_view_mail);
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
    }

    @Override
    public void displayNews(List<Article> articleList) {

//        Log.e("TAG", articleList.toString());
//        mainAdapter = new MainAdapter(this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(mainAdapter);

        mainAdapter.addData(articleList);



    }


    // for search
    @Override
    public void displayError(String s) {

        Toast.makeText(this, "Error "  + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void googleSignInResult() {


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);

                        String name = account.getDisplayName();
                        String email  =  account.getEmail();
                        Log.e(TAG, email);
                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {


        textViewUsername.setText(googleSignInAccount.getDisplayName());
        textViewUserMail.setText(googleSignInAccount.getEmail());


    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Log.d(TAG, "ALready logged in");
            onLoggedIn(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Not logged in");
        }
    }



    @Override
    public boolean onQueryTextSubmit(String s) {

        MenuItem searchItm = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItm.getActionView();

        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }


        mainPresenter.goSearchResultActivity(s);


        return true;

    }

    @Override
    public boolean onQueryTextChange(String s) {

        return true;
    }

    @Override
    public void onRefresh() {
        getNewsList();
    }
}
