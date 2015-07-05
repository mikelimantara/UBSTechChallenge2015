package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.MainActivity22;
import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 28/6/2015.
 */
public class TransactionHistoryFilterFragment extends Fragment implements View.OnClickListener {

    private View v;
    private ImageView imgCalendarFrom;
    private ImageView imgCalendarTo;
    private EditText txtCalendarFrom;
    private EditText txtCalendarTo;
    private ToggleButton btnAccountAll;
    private ToggleButton btnAccount1;
    private ToggleButton btnAccount2;
    private ToggleButton btnHoldingsAll;
    private ToggleButton btnHoldingsEquity;
    private ToggleButton btnHoldingsFunds;
    private ToggleButton btnHoldingsBonds;
    private ToggleButton btnHoldingsOtherAssets;
    private ToggleButton btnTypeAll;
    private ToggleButton btnTypeBuy;
    private ToggleButton btnTypeSell;
    private ToggleButton btnTypeSwitch;
    private ToggleButton btnTypeDividend;
    private ToggleButton btnTypeSplit;
    private Button btnApply;
    private Button btnReset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_history_filter, container, false);
        setUpToolbar();
        initViews();

        return v;
    }

    private void setUpToolbar() {
        ((MainActivity)(getActivity())).setToolbarBasedOnContent("Transaction History Filter",
                MainActivity.MenuBarState.DEFAULT);
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
        } else if (v == btnReset) {
            btnAccountAll.setChecked(true);
            btnHoldingsAll.setChecked(true);
            btnTypeAll.setChecked(true);
            btnAccount1.setChecked(false);
            btnAccount2.setChecked(false);
            btnHoldingsEquity.setChecked(false);
            btnHoldingsFunds.setChecked(false);
            btnHoldingsBonds.setChecked(false);
            btnHoldingsOtherAssets.setChecked(false);
            btnTypeBuy.setChecked(false);
            btnTypeSell.setChecked(false);
            btnTypeSwitch.setChecked(false);
            btnTypeDividend.setChecked(false);
            btnTypeSplit.setChecked(false);
        } else if (v == btnApply) {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        } else if (v == btnAccountAll) {
            btnAccount1.setChecked(false);
            btnAccount2.setChecked(false);
        } else if (v == btnHoldingsAll) {
            btnHoldingsEquity.setChecked(false);
            btnHoldingsFunds.setChecked(false);
            btnHoldingsBonds.setChecked(false);
            btnHoldingsOtherAssets.setChecked(false);
        } else if (v == btnTypeAll) {
            btnTypeBuy.setChecked(false);
            btnTypeSell.setChecked(false);
            btnTypeSwitch.setChecked(false);
            btnTypeDividend.setChecked(false);
            btnTypeSplit.setChecked(false);
        } else if (v == btnAccount1 || v == btnAccount2) {
            btnAccountAll.setChecked(false);
        } else if (v == btnHoldingsFunds || v == btnHoldingsEquity ||
                v == btnHoldingsBonds || v == btnHoldingsOtherAssets) {
            btnHoldingsAll.setChecked(false);
        } else if (v == btnTypeBuy || v == btnTypeSell || v == btnTypeSwitch ||
                v == btnTypeSplit || v == btnTypeDividend) {
            btnTypeAll.setChecked(false);
        }
    }

    private void initViews() {
        imgCalendarFrom = (ImageView) v.findViewById(R.id.transaction_history_filter_calender_from);
        txtCalendarFrom = (EditText) v.findViewById(R.id.transaction_history_filter_date_textfield_from);
        imgCalendarTo = (ImageView) v.findViewById(R.id.transaction_history_filter_calender_to);
        txtCalendarTo = (EditText) v.findViewById(R.id.transaction_history_filter_date_textfield_to);

        btnAccountAll = (ToggleButton) v.findViewById(R.id.order_status_filter_button_account_all);
        btnAccount1 = (ToggleButton) v.findViewById(R.id.order_status_filter_button_account1);
        btnAccount2 = (ToggleButton) v.findViewById(R.id.order_status_filter_button_account2);
        btnHoldingsAll = (ToggleButton) v.findViewById(R.id.order_status_filter_button_holdings_all);
        btnHoldingsEquity = (ToggleButton) v.findViewById(R.id.order_status_filter_button_holdings_equity);
        btnHoldingsFunds = (ToggleButton) v.findViewById(R.id.order_status_filter_button_holdings_funds);
        btnHoldingsBonds = (ToggleButton) v.findViewById(R.id.order_status_filter_button_holdings_bonds);
        btnHoldingsOtherAssets = (ToggleButton) v.findViewById(R.id.order_status_filter_button_holdings_other_assets);
        btnTypeAll = (ToggleButton) v.findViewById(R.id.order_status_filter_button_type_all);
        btnTypeBuy = (ToggleButton) v.findViewById(R.id.order_status_filter_button_type_buy);
        btnTypeSell = (ToggleButton) v.findViewById(R.id.order_status_filter_button_type_sell);
        btnTypeSwitch = (ToggleButton) v.findViewById(R.id.order_status_filter_button_type_switch);
        btnTypeDividend = (ToggleButton) v.findViewById(R.id.order_status_filter_button_type_dividend);
        btnTypeSplit = (ToggleButton) v.findViewById(R.id.order_status_filter_button_type_split);
        btnApply = (Button) v.findViewById(R.id.filter_button_apply);
        btnReset = (Button) v.findViewById(R.id.filter_button_reset);

        imgCalendarFrom.setOnClickListener(this);
        imgCalendarTo.setOnClickListener(this);

        btnAccountAll.setOnClickListener(this);
        btnAccount1.setOnClickListener(this);
        btnAccount2.setOnClickListener(this);
        btnHoldingsAll.setOnClickListener(this);
        btnHoldingsEquity.setOnClickListener(this);
        btnHoldingsFunds.setOnClickListener(this);
        btnHoldingsBonds.setOnClickListener(this);
        btnHoldingsOtherAssets.setOnClickListener(this);
        btnTypeAll.setOnClickListener(this);
        btnTypeBuy.setOnClickListener(this);
        btnTypeSell.setOnClickListener(this);
        btnTypeSplit.setOnClickListener(this);
        btnTypeSwitch.setOnClickListener(this);
        btnTypeDividend.setOnClickListener(this);

        btnApply.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }


}
