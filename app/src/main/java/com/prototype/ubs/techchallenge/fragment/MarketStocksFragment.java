package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 28/6/2015.
 */
public class MarketStocksFragment extends Fragment implements View.OnClickListener {
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.market_stocks, container, false);

        LinearLayout txt = (LinearLayout) v.findViewById(R.id.market_stocks_mu);
        txt.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, new MarketStocksDetailFragment())
                .addToBackStack(null)
                .commit();
    }
}
