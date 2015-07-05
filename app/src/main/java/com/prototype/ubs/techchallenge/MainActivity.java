package com.prototype.ubs.techchallenge;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
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

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.prototype.ubs.techchallenge.fragment.AssetAllocationFragment;
import com.prototype.ubs.techchallenge.fragment.EStatementFragment;
import com.prototype.ubs.techchallenge.fragment.MarketNewsMainFragment;
import com.prototype.ubs.techchallenge.fragment.MeetingReportFragment;
import com.prototype.ubs.techchallenge.fragment.MeetingReportUnverifiedFragment;
import com.prototype.ubs.techchallenge.fragment.PortfolioOverview2;
import com.prototype.ubs.techchallenge.fragment.PortfolioOverviewFragment;
import com.prototype.ubs.techchallenge.fragment.TransactionHistoryFilterFragment;
import com.prototype.ubs.techchallenge.fragment.TransactionHistoryFragment;
import com.prototype.ubs.techchallenge.fragment.WatchlistListFragment;

/**
 * Created by Michael on 3/7/2015.
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    public enum MenuBarState {
        DEFAULT, FILTER;
    }

    private ListView navList;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationDrawerAdapter drawerAdapter;

    private MenuBarState menuBarState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initViews();
        setUpToolbar();
        setupDrawer();
        setNavigationDrawerItems();

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);

        // Initial fragment to show
        MarketNewsMainFragment marketNewsFragment = new MarketNewsMainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_container, marketNewsFragment);
        transaction.commit();
        toolbar.setTitle("Market News");
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

    public void setNavigationDrawerItems() {
        String[] drawerItems = {"Portfolio Overview", "Asset Allocation", "Transaction History",
                "E-Statement", "Meeting Reports", "Watchlist", "Market News"};

        String[] drawerIcons = {"portfolio", "asset_allocation", "transaction_history",
                "download", "meeting_report", "watchlist", "market_news"};

        drawerAdapter = new NavigationDrawerAdapter(drawerItems, drawerIcons);
        navList.setAdapter(drawerAdapter);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navList = (ListView) findViewById(R.id.nav_list);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navList.setOnItemClickListener(this);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setToolbarBasedOnContent(String title, MenuBarState menuBarState) {
        toolbar.setTitle(title);
        setMenuBarState(menuBarState);
        getSupportActionBar().invalidateOptionsMenu();
    }

    public void setMenuBarState(MenuBarState state) {
        this.menuBarState = state;
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

    private void setupDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            PortfolioOverview2 portfolioOverviewFragment = new PortfolioOverview2();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, portfolioOverviewFragment)
                    .commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 1) {
            AssetAllocationFragment assetAllocationFragment = new AssetAllocationFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, assetAllocationFragment)
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
            MeetingReportFragment meetingReportFragment = new MeetingReportFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, meetingReportFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 5) {
            WatchlistListFragment watchlistListFragment = new WatchlistListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, watchlistListFragment);
            transaction.commit();
            drawerLayout.closeDrawer(navList);
        } else if (position == 6) {
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
            inflater = LayoutInflater.from(MainActivity.this);
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
