package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototype.ubs.techchallenge.R;

/**
 * Created by shelviadwihotama on 4/7/15.
 */
public class PortfolioOverview2 extends Fragment {
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.portfolio_investment_3, container, false);
        return v;
    }
}
