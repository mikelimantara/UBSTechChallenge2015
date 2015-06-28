package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 28/6/2015.
 */
public class TransactionHistoryFilterFragment extends Fragment implements View.OnClickListener {

    private View v;
    private ActionBar actionBar;
    private ImageView imgCalendarFrom;
    private ImageView imgCalendarTo;
    private EditText txtCalendarFrom;
    private EditText txtCalendarTo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_history_filter, container, false);
        initViews();

        actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        hideTabsOnActionBar();
        ((MainActivity)getActivity()).setMenuBarState(MainActivity.MenuBarState.DEFAULT);
        getActivity().invalidateOptionsMenu();

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == imgCalendarFrom) {
            DateTimePickerFragment datePickerFragment = new DateTimePickerFragment();
            datePickerFragment.setTxtDate(txtCalendarFrom);
            datePickerFragment.show(getActivity().getSupportFragmentManager(), null);
        } else if (v == imgCalendarTo) {
            DateTimePickerFragment datePickerFragment = new DateTimePickerFragment();
            datePickerFragment.setTxtDate(txtCalendarTo);
            datePickerFragment.show(getActivity().getSupportFragmentManager(), null);
        }
    }

    private void initViews() {
        imgCalendarFrom = (ImageView) v.findViewById(R.id.transaction_history_filter_calender_from);
        txtCalendarFrom = (EditText) v.findViewById(R.id.transaction_history_filter_date_textfield_from);
        imgCalendarTo = (ImageView) v.findViewById(R.id.transaction_history_filter_calender_to);
        txtCalendarTo = (EditText) v.findViewById(R.id.transaction_history_filter_date_textfield_to);

        imgCalendarFrom.setOnClickListener(this);
        imgCalendarTo.setOnClickListener(this);
    }


    private void hideTabsOnActionBar() {
        actionBar.removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }
}
