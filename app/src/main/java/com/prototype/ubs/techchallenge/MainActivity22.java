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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.fragment.AssetAllocationProductTypeFragment;
import com.prototype.ubs.techchallenge.fragment.EStatementFragment;
import com.prototype.ubs.techchallenge.fragment.LoginFragment;
import com.prototype.ubs.techchallenge.fragment.MarketNewsMainFragment;
import com.prototype.ubs.techchallenge.fragment.MeetingReportUnverifiedFragment;
import com.prototype.ubs.techchallenge.fragment.PortfolioOverviewFragment;
import com.prototype.ubs.techchallenge.fragment.TransactionHistoryFilterFragment;
import com.prototype.ubs.techchallenge.fragment.TransactionHistoryFragment;
import com.prototype.ubs.techchallenge.model.Portfolio;
import com.prototype.ubs.techchallenge.utils.Constants;
import com.prototype.ubs.techchallenge.utils.ReplaceFont;

/**
 * Created by Michael on 10/6/2015.
 */
public class MainActivity22 extends ActionBarActivity implements LoginFragment.OnLoginListener,
        AdapterView.OnItemClickListener {

    public enum MenuBarState {
        DEFAULT, FILTER;
    }

    private ActionBar actionBar = null;
    private MenuBarState menuBarState = MenuBarState.DEFAULT;
    private ListView navList = null;
    private NavigationDrawerAdapter drawerAdapter = null;
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

            String[] drawerIcons = {"portfolio", "asset_allocation", "transaction_history",
                "download", "meeting_report", "market_news"};

            setNavigationDrawerItems(drawerItems, drawerIcons);
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
        } else if (item.getItemId() == R.id.menu_filter) {
            TransactionHistoryFilterFragment transactionHistoryFilterFragment = new TransactionHistoryFilterFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, transactionHistoryFilterFragment)
                    .addToBackStack(null)
                    .commit();
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

    public void setNavigationDrawerItems(String[] items, String[] icons) {
        drawerAdapter = new NavigationDrawerAdapter(items, icons);
        navList.setAdapter(drawerAdapter);

//        drawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//        navList.setAdapter(drawerAdapter);
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
            AssetAllocationProductTypeFragment assetAllocationProductTypeFragment = new AssetAllocationProductTypeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, assetAllocationProductTypeFragment)
                    .commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 2) {
            drawerLayout.closeDrawer(navList);

            final Dialog OTP = new Dialog(this);
            OTP.setContentView(R.layout.otp);
            OTP.setTitle("Enter a One-Time Password");

            final Button cancel = (Button) OTP.findViewById(R.id.btn_otp_cancel);
            final Button submit = (Button) OTP.findViewById(R.id.btn_otp_submit);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == cancel) {
                        OTP.dismiss();
                    }
                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == submit) {
                        OTP.dismiss();
                        TransactionHistoryFragment transactionHistoryFragment = new TransactionHistoryFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_container, transactionHistoryFragment);
                        transaction.commit();
                        actionBar.setTitle("Transaction History");
                    }
                }
            });

            OTP.show();

        } else if (position == 3) {
            EStatementFragment eStatementFragment = new EStatementFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, eStatementFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 4) {
            MeetingReportUnverifiedFragment meetingReportFragment = new MeetingReportUnverifiedFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, meetingReportFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 5) {
            MarketNewsMainFragment marketNewsFragment = new MarketNewsMainFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, marketNewsFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        }
    }

    private class NavigationDrawerAdapter extends BaseAdapter {

        String[] icons = {};
        String[] title = {};
        LayoutInflater inflater;

        public NavigationDrawerAdapter(String[] title, String[] icons) {
            inflater = LayoutInflater.from(MainActivity22.this);
            this.icons = icons;
            this.title = title;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.side_bar_item, parent, false);
            }

            ImageView imgIcon = (ImageView) v.findViewById(R.id.side_bar_icon);
            TextView txtTitle = (TextView) v.findViewById(R.id.side_bar_text);

            Context ctx = imgIcon.getContext();
            int resourceId = ctx.getResources().getIdentifier(icons[position], "drawable", ctx.getPackageName());
            imgIcon.setImageResource(resourceId);
            txtTitle.setText(title[position]);

            return v;
        }
    }

}
