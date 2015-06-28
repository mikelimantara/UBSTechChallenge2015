package com.prototype.ubs.techchallenge;

import android.app.Dialog;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.prototype.ubs.techchallenge.fragment.AssetAllocationFragment;
import com.prototype.ubs.techchallenge.fragment.EStatementFragment;
import com.prototype.ubs.techchallenge.fragment.LoginFragment;
import com.prototype.ubs.techchallenge.fragment.MarketNewsFragment;
import com.prototype.ubs.techchallenge.fragment.MeetingReportFragment;
import com.prototype.ubs.techchallenge.fragment.PortfolioOverviewFragment;
import com.prototype.ubs.techchallenge.fragment.TransactionHistoryFragment;
import com.prototype.ubs.techchallenge.model.MeetingReport;
import com.prototype.ubs.techchallenge.model.Portfolio;
import com.prototype.ubs.techchallenge.utils.Constants;
import com.prototype.ubs.techchallenge.utils.ReplaceFont;

/**
 * Created by Michael on 10/6/2015.
 */
public class MainActivity extends ActionBarActivity implements LoginFragment.OnLoginListener,
        AdapterView.OnItemClickListener {

    public enum MenuBarState {
        DEFAULT, FILTER;
    }

    private ActionBar actionBar = null;
    private MenuBarState menuBarState = MenuBarState.DEFAULT;
    private ListView navList = null;
    private ArrayAdapter<String> drawerAdapter = null;
    private ActionBarDrawerToggle drawerToggle = null;
    private DrawerLayout drawerLayout = null;
    private SharedPreferences sharedPrefs = null;
    private boolean hasLogin = false;
    private Portfolio portfolio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ReplaceFont.replaceDefaultFont(this, "MONOSPACE", "DroidSerif.ttf");

        initViews();

        sharedPrefs = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        if (findViewById(R.id.content_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            // checkHasLogin();

//            if (hasLogin) {
            String[] drawerItems = {"Portfolio Overview", "Asset Allocation", "Transaction History",
                    "E-Statement", "Meeting Reports", "Market News"};
            setNavigationDrawerItems(drawerItems);
            setupDrawer();
            PortfolioOverviewFragment portfolioOverviewFragment = new PortfolioOverviewFragment();
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.content_container, portfolioOverviewFragment)
                    .commit();
//            } else {
//                String[] drawerItems = {"Settings", "About Us", "Contact Us"};
//                setNavigationDrawerItems(drawerItems);
//                setupDrawer();
//                LoginFragment loginFragment = new LoginFragment();
//                getSupportFragmentManager()
//                        .beginTransaction().add(R.id.content_container, loginFragment)
//                        .commit();
//            }
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
        Log.d("action bar", "post create, sync!");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
        Log.d("action bar", "configuration");
    }

    public void setMenuBarState(MenuBarState state) {
        this.menuBarState = state;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);

        if (menuBarState == MenuBarState.DEFAULT) {
            MenuItem iconFilter = menu.findItem(R.id.menu_filter);
            iconFilter.setVisible(false);
        } else if (menuBarState == MenuBarState.FILTER) {
            MenuItem iconFilter = menu.findItem(R.id.menu_filter);
            iconFilter.setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.menu_contact_advisor) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.contact_us);
            dialog.setTitle("Need Any Help?");
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    // Callback method from loginFragment once login is successful
    @Override
    public void onLogin() {
        PortfolioOverviewFragment portfolioOverviewFragment = new PortfolioOverviewFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, portfolioOverviewFragment);
        transaction.commit();
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
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
            PortfolioOverviewFragment portfolioOverviewFragment = new PortfolioOverviewFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, portfolioOverviewFragment)
                    .commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 1) {
            AssetAllocationFragment assetAllocationFragment = new AssetAllocationFragment();
            assetAllocationFragment.setPortfolio(portfolio);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, assetAllocationFragment)
                    .commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 2) {
            TransactionHistoryFragment transactionHistoryFragment = new TransactionHistoryFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, transactionHistoryFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
            actionBar.setTitle("Transaction History");
        } else if (position == 3) {
            EStatementFragment eStatementFragment = new EStatementFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, eStatementFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 4) {
            MeetingReportFragment meetingReportFragment = new MeetingReportFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, meetingReportFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 5) {
            MarketNewsFragment marketNewsFragment = new MarketNewsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, marketNewsFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        }
    }


}
