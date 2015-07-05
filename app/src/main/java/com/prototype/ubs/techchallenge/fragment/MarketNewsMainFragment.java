package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.utils.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 28/6/2015.
 */
public class MarketNewsMainFragment extends Fragment {
    private View v;
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;
    private MarketNewsPagerAdapter marketNewsPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.market_news_main, container, false);
        setUpToolbar();
        initViews();

        tabLayout = (SlidingTabLayout) v.findViewById(R.id.market_news_sliding_tab);
        tabLayout.setViewPager(viewPager);

        ((MainActivity)getActivity()).setMenuBarState(MainActivity.MenuBarState.DEFAULT);
        getActivity().invalidateOptionsMenu();

        return v;
    }

    private void setUpToolbar() {
        ((MainActivity)(getActivity())).setToolbarBasedOnContent("Market News",
                MainActivity.MenuBarState.DEFAULT);
    }

    private void initViews() {
        viewPager = (ViewPager) v.findViewById(R.id.market_news_viewpager);
        marketNewsPagerAdapter = new MarketNewsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(marketNewsPagerAdapter);
    }

    private class MarketNewsPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> marketNewsFragment = new ArrayList<Fragment>();
        private String[] tabTitles = {"Market News", "Market Indices", "Currencies"};

        public MarketNewsPagerAdapter(FragmentManager fm) {
            super(fm);
            marketNewsFragment.add(new MarketNewsFragment());
            marketNewsFragment.add(new MarketStocksFragment());
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

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
