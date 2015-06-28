package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 28/6/2015.
 */
public class MarketIndicesFragment extends Fragment implements View.OnClickListener {
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.market_indices, container, false);

        TextView txt = (TextView) v.findViewById(R.id.market_indices_mu);
        txt.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, new MarketIndicesDetailFragment())
                .addToBackStack(null)
                .commit();
    }
}
