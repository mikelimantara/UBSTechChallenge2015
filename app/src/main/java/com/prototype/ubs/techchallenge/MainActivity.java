package com.prototype.ubs.techchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.prototype.ubs.techchallenge.fragment.LoginFragment;
import com.prototype.ubs.techchallenge.fragment.MarketNewsFragment;
import com.prototype.ubs.techchallenge.fragment.OverviewFragment;
import com.prototype.ubs.techchallenge.fragment.TransactionHistoryFragment;
import com.prototype.ubs.techchallenge.utils.Constants;

/**
 * Created by Michael on 10/6/2015.
 */
public class MainActivity extends ActionBarActivity implements LoginFragment.OnLoginListener, AdapterView.OnItemClickListener {

    private ActionBar actionBar = null;
    private ListView navList = null;
    private ArrayAdapter<String> drawerAdapter = null;
    private ActionBarDrawerToggle drawerToggle = null;
    private DrawerLayout drawerLayout = null;
    private SharedPreferences sharedPrefs = null;
    private boolean hasLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initViews();

        sharedPrefs = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        if (findViewById(R.id.content_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            checkHasLogin();

            if (hasLogin) {
                String[] drawerItems = {"Transaction History", "Market News"};
                setNavigationDrawerItems(drawerItems);
                setupDrawer();
                OverviewFragment overviewFragment = new OverviewFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.content_container, overviewFragment)
                        .commit();
            } else {
                String[] drawerItems = {"Settings", "About Us", "Contact Us"};
                setNavigationDrawerItems(drawerItems);
                setupDrawer();
                LoginFragment loginFragment = new LoginFragment();
                getSupportFragmentManager()
                        .beginTransaction().add(R.id.content_container, loginFragment)
                        .commit();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Callback method from loginFragment once login is successful
    @Override
    public void onLogin() {
        OverviewFragment overviewFragment = new OverviewFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, overviewFragment);
        transaction.commit();
    }

    public void setNavigationDrawerItems(String[] items) {
        drawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        navList.setAdapter(drawerAdapter);
    }

    private void initViews() {
        navList = (ListView) findViewById(R.id.nav_list);
        actionBar = getSupportActionBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navList.setOnItemClickListener(this);
    }

    private void setupDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                actionBar.setTitle("Navigation");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                actionBar.setTitle("Elite Bank");
                invalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    //TODO: Communicate with server
    private void checkHasLogin() {
        int userId = sharedPrefs.getInt(Constants.SHARED_PREFS_UID, -1);
        hasLogin  = (userId != -1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            TransactionHistoryFragment transactionHistoryFragment = new TransactionHistoryFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, transactionHistoryFragment);
            transaction.commit();
        }
        else if (position == 1) {
            MarketNewsFragment marketNewsFragment = new MarketNewsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, marketNewsFragment);
            transaction.commit();
        }
    }
}
