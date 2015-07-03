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
import com.prototype.ubs.techchallenge.model.MeetingReport;
import com.prototype.ubs.techchallenge.utils.SlidingTabLayout;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 3/7/2015.
 */
public class MeetingReportFragment extends Fragment {
    private View v;
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private MeetingReportPagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.meeting_report_main, container, false);
        setUpToolbar();
        initViews();

        return v;
    }

    private void setUpToolbar() {
        ((MainActivity)(getActivity())).setToolbarBasedOnContent("Meeting Report",
                MainActivity.MenuBarState.DEFAULT);
    }

    private void initViews() {
        tabLayout = (SlidingTabLayout) v.findViewById(R.id.meeting_report_sliding_tab);
        viewPager = (ViewPager) v.findViewById(R.id.meeting_report_viewpager);
        pagerAdapter = new MeetingReportPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setViewPager(viewPager);
    }

    private class MeetingReportPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> meetingReportFragments = new ArrayList<Fragment>();
        private String[] tabTitles = {"Unverified Reports", "Verified Reports"};

        public MeetingReportPagerAdapter(FragmentManager fm) {
            super(fm);
            meetingReportFragments.add(new MeetingReportUnverifiedFragment());
            meetingReportFragments.add(new MeetingReportVerifiedFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return meetingReportFragments.get(position);
        }

        @Override
        public int getCount() {
            return meetingReportFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
