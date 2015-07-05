package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 5/7/2015.
 */
public class WatchlistListFragment extends Fragment implements View.OnClickListener {

    private View v;
    private ListView watchlistList;
    private WatchListAdapter watchListAdapter;
    private ImageView imgAddWatchlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.watchlist, container, false);
        setUpToolbar();
        initViews();

        return v;
    }

    private void setUpToolbar() {
        ((MainActivity)(getActivity())).setToolbarBasedOnContent("Watchlist",
                MainActivity.MenuBarState.FILTER);
    }

    private void initViews() {
        watchlistList = (ListView) v.findViewById(R.id.watchlist_listview);
        watchListAdapter = new WatchListAdapter();
        watchlistList.setAdapter(watchListAdapter);
        imgAddWatchlist = (ImageView) v.findViewById(R.id.img_add_watchlist);
        imgAddWatchlist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == imgAddWatchlist) {
            WatchlistDetailFragment watchlistDetailFragment = new WatchlistDetailFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, watchlistDetailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private class WatchListAdapter extends BaseAdapter {


        private LayoutInflater inflater;

        public WatchListAdapter() {
            inflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.watchlist_item, parent, false);
            }

            return v;

        }
    }


}
