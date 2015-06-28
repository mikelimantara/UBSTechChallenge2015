package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
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
 * Created by Michael on 18/6/2015.
 */
public class TransactionHistoryFragment extends Fragment {
    private View v = null;
    private ViewPager transactionHistoryViewPager = null;
    private ActionBar actionBar = null;
    private PagerAdapter pagerAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_history_main, container, false);
        initViews();

        actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        showTabsOnActionBar();

        pagerAdapter = new TransactionHistoryPagerAdapter(getChildFragmentManager());

        transactionHistoryViewPager.setAdapter(pagerAdapter);
        transactionHistoryViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleOnActionBar();
    }

    private void initViews() {
        transactionHistoryViewPager = (ViewPager) v.findViewById(R.id.transaction_history_viewpager);
    }

    private void setTitleOnActionBar() {
        actionBar.setTitle("TRANSACTION HISTORY");
    }

    private void showTabsOnActionBar() {
        actionBar.removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                transactionHistoryViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        actionBar.addTab(actionBar.newTab().setText("Order Status").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Transaction History").setTabListener(tabListener));
    }

    private class TransactionHistoryPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> transactionHistoryFragments = new ArrayList<Fragment>();

        public TransactionHistoryPagerAdapter(FragmentManager fm) {
            super(fm);
            transactionHistoryFragments.add(new OrderStatusFragment());
            transactionHistoryFragments.add(new TransactionHistoryListFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return transactionHistoryFragments.get(position);
        }

        @Override
        public int getCount() {
            return transactionHistoryFragments.size();
        }
    }
}
