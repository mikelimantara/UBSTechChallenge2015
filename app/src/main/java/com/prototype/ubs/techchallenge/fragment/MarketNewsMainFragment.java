package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 28/6/2015.
 */
public class MarketNewsMainFragment extends Fragment {
    private View v;
    private ViewPager viewPager;
    private ActionBar actionBar;
    private MarketNewsPagerAdapter marketNewsPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.market_news_main, container, false);
        initViews();

        actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        showTabsOnActionBar();
        ((MainActivity)getActivity()).setMenuBarState(MainActivity.MenuBarState.DEFAULT);
        getActivity().invalidateOptionsMenu();

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        return v;
    }

    private void initViews() {
        viewPager = (ViewPager) v.findViewById(R.id.market_news_viewpager);
        marketNewsPagerAdapter = new MarketNewsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(marketNewsPagerAdapter);
    }

    private void showTabsOnActionBar() {
        actionBar.removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        actionBar.addTab(actionBar.newTab().setText("Market News").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Market Indices").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Currencies").setTabListener(tabListener));
    }

    private class MarketNewsPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> marketNewsFragment = new ArrayList<Fragment>();

        public MarketNewsPagerAdapter(FragmentManager fm) {
            super(fm);
            marketNewsFragment.add(new MarketNewsFragment());
            marketNewsFragment.add(new MarketIndicesFragment());
            marketNewsFragment.add(new MarketForexFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return marketNewsFragment.get(position);
        }

        @Override
        public int getCount() {
            return marketNewsFragment.size();
        }
    }
}
