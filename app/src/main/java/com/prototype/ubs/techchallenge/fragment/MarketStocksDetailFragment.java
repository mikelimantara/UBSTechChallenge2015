package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 28/6/2015.
 */
public class MarketStocksDetailFragment extends Fragment implements View.OnClickListener {
    private View v;
    private Button btnBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.market_stocks_detail, container, false);

        btnBack = (Button) v.findViewById(R.id.btn_back_market_indices);
        btnBack.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }
    }
}
