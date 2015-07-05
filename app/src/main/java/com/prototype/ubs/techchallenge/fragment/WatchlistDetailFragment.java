package com.prototype.ubs.techchallenge.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 5/7/2015.
 */
public class WatchlistDetailFragment extends Fragment implements View.OnClickListener {
    private View v;
    private LinearLayout addItemToWatchlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.watchlist_add, container, false);

        addItemToWatchlist = (LinearLayout) v.findViewById(R.id.btn_add_item_to_watchlist);
        addItemToWatchlist.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == addItemToWatchlist) {
            Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.watchlist_pop_up_search);
            dialog.setTitle("Add Item to Watchlist");
            dialog.show();
        }
    }
}
