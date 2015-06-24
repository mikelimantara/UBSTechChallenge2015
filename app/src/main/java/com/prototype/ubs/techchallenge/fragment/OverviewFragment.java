package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.utils.Constants;

/**
 * Created by Michael on 12/6/2015.
 */
public class OverviewFragment extends Fragment {
    private View v = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.overview, container, false);
        initViews();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitleOnActionBar();
    }

    private void initViews() {

    }

    private void setTitleOnActionBar() {
        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("MY PORTFOLIO - OVERVIEW");
    }
}
