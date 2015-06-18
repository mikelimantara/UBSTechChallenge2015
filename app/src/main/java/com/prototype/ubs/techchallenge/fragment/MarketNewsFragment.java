package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 18/6/2015.
 */
public class MarketNewsFragment extends Fragment{
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.market_news, container, false);
        return v;
    }
}


