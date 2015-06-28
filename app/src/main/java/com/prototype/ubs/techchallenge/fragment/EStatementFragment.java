package com.prototype.ubs.techchallenge.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 28/6/2015.
 */
public class EStatementFragment extends Fragment implements View.OnClickListener {
    private View v;
    private Button year5Btn;
    private Button year4Btn;
    private Button year3Btn;
    private Button year2Btn;
    private Button year1Btn;
    private GridView eStatementMonthGridView;
    private EStatementMonthAdapter eStatementMonthAdapter;
    private ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.e_statement, container, false);
        initViews();

        actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        hideTabsOnActionBar();
        ((MainActivity)getActivity()).setMenuBarState(MainActivity.MenuBarState.DEFAULT);
        getActivity().invalidateOptionsMenu();

        return v;
    }

    private void hideTabsOnActionBar() {
        actionBar.removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    private void initViews() {
        year5Btn = (Button) v.findViewById(R.id.e_statement_2015);
        year4Btn = (Button) v.findViewById(R.id.e_statement_2014);
        year3Btn = (Button) v.findViewById(R.id.e_statement_2013);
        year2Btn = (Button) v.findViewById(R.id.e_statement_2012);
        year1Btn = (Button) v.findViewById(R.id.e_statement_2011);
        eStatementMonthGridView = (GridView) v.findViewById(R.id.e_statement_month_gridview);
        eStatementMonthAdapter = new EStatementMonthAdapter();
        eStatementMonthGridView.setAdapter(eStatementMonthAdapter);

        year5Btn.setBackgroundColor(getResources().getColor(R.color.blue));
        year5Btn.setTextColor(getResources().getColor(R.color.yellow));
        year5Btn.setOnClickListener(this);
        year4Btn.setOnClickListener(this);
        year3Btn.setOnClickListener(this);
        year2Btn.setOnClickListener(this);
        year1Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == year5Btn) {
            year5Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            year4Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year3Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year2Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year1Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));

            year5Btn.setTextColor(getActivity().getResources().getColor(R.color.yellow));
            year4Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year3Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year2Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year1Btn.setTextColor(getActivity().getResources().getColor(R.color.black));

        } else if (v == year4Btn) {
            year5Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year4Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            year3Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year2Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year1Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));

            year5Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year4Btn.setTextColor(getActivity().getResources().getColor(R.color.yellow));
            year3Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year2Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year1Btn.setTextColor(getActivity().getResources().getColor(R.color.black));

        } else if (v == year3Btn) {
            year5Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year4Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year3Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            year2Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year1Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));

            year5Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year4Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year3Btn.setTextColor(getActivity().getResources().getColor(R.color.yellow));
            year2Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year1Btn.setTextColor(getActivity().getResources().getColor(R.color.black));

        } else if (v == year2Btn) {
            year5Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year4Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year3Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year2Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            year1Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));

            year5Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year4Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year3Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year2Btn.setTextColor(getActivity().getResources().getColor(R.color.yellow));
            year1Btn.setTextColor(getActivity().getResources().getColor(R.color.black));

        } else if (v == year1Btn) {
            year5Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year4Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year3Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year2Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
            year1Btn.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));

            year5Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year4Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year3Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year2Btn.setTextColor(getActivity().getResources().getColor(R.color.black));
            year1Btn.setTextColor(getActivity().getResources().getColor(R.color.yellow));
        }
    }

    private class EStatementMonthAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Des"};
        private TextView previouslySelected;

        public EStatementMonthAdapter() {
            this.inflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getCount() {
            return months.length;
        }

        @Override
        public Object getItem(int position) {
            return months[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.e_statement_month, parent, false);
            }

            final TextView txtMonth = (TextView) v.findViewById(R.id.e_statement_month);
            txtMonth.setText((String) getItem(position));

            txtMonth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == txtMonth) {
                        txtMonth.setBackgroundColor(getResources().getColor(R.color.blue));
                        txtMonth.setTextColor(getResources().getColor(R.color.yellow));

                        if (previouslySelected == null) {
                            previouslySelected = txtMonth;
                        } else {
                            previouslySelected.setBackgroundColor(getResources().getColor(R.color.transparent));
                            previouslySelected.setTextColor(getResources().getColor(R.color.black));
                            previouslySelected = txtMonth;
                        }
                    }
                }
            });


            return v;
        }
    }
}
