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
import com.prototype.ubs.techchallenge.utils.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 18/6/2015.
 */
public class TransactionHistoryFragment extends Fragment {
    private View v = null;
    private ViewPager transactionHistoryViewPager = null;
    private PagerAdapter pagerAdapter = null;
    private SlidingTabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_history_main, container, false);
        setUpToolbar();
        initViews();

        return v;
    }

    private void setUpToolbar() {
        ((MainActivity)(getActivity())).setToolbarBasedOnContent("Transaction History",
                MainActivity.MenuBarState.FILTER);
    }

    private void initViews() {
        transactionHistoryViewPager = (ViewPager) v.findViewById(R.id.transaction_history_viewpager);
        tabLayout = (SlidingTabLayout) v.findViewById(R.id.transaction_history_sliding_tab);
        pagerAdapter = new TransactionHistoryPagerAdapter(getChildFragmentManager());
        transactionHistoryViewPager.setAdapter(pagerAdapter);
        tabLayout.setViewPager(transactionHistoryViewPager);
    }

    private class TransactionHistoryPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> transactionHistoryFragments = new ArrayList<Fragment>();
        private String[] tabTitles = {"Order Status", "Transaction History"};

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

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
