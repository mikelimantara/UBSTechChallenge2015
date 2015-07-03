package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.MainActivity22;
import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.Transaction;

/**
 * Created by Michael on 22/6/2015.
 */
public class TransactionHistoryDetailFragment extends Fragment implements View.OnClickListener {
    private View v;
    private TextView txtAccountName;
    private TextView txtDate;
    private TextView txtDescription;
    private TextView txtAccountNo;
    private TextView txtTransactionRefNo;
    private TextView txtOrderAmount;
    private TextView txtSettledAmount;
    private TextView txtRealisedGainAmount;
    private TextView txtRealisedGainPercentage;
    private Button btnBack;
    private Transaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_history_item_detail, container, false);
        setUpToolbar();

//        hideTabsInActionBar();
        initViews();
        populateData();

        return v;
    }

    public void setData(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }
    }

    private void setUpToolbar() {
        ((MainActivity)(getActivity())).setToolbarBasedOnContent("Transaction History",
                MainActivity.MenuBarState.DEFAULT);
    }

    private void initViews() {
        txtAccountName = (TextView) v.findViewById(R.id.transaction_history_detail_account_name);
        txtTransactionRefNo = (TextView) v.findViewById(R.id.transaction_history_detail_ref_no);
        txtAccountNo = (TextView) v.findViewById(R.id.transaction_history_detail_account);
        txtDate = (TextView) v.findViewById(R.id.transaction_history_detail_date);
        txtDescription = (TextView) v.findViewById(R.id.transaction_history_detail_description);
        txtOrderAmount = (TextView) v.findViewById(R.id.transaction_history_detail_order_amt);
        txtSettledAmount = (TextView) v.findViewById(R.id.transaction_history_detail_settled_amt);
        txtRealisedGainAmount = (TextView) v.findViewById(R.id.transaction_history_detail_realized_gain_amt);
        txtRealisedGainPercentage = (TextView) v.findViewById(R.id.transaction_history_detail_realized_gain_percentage);
        btnBack = (Button) v.findViewById(R.id.btn_back_transaction_history_detail);
        btnBack.setOnClickListener(this);
    }

    private void populateData() {
        if (transaction != null) {
            txtAccountName.setText(transaction.getAccountName());
            txtDate.setText(transaction.getStringDate());
            txtTransactionRefNo.setText(transaction.getTransactionRef());
            txtAccountNo.setText(transaction.getAccountNo());
            txtDescription.setText(transaction.getDescription());
            txtOrderAmount.setText(transaction.getOrderAmount().toString());
            txtSettledAmount.setText(transaction.getSettledAmount().toString());
            txtRealisedGainAmount.setText(transaction.getRealizedGainAmt().toString());
            txtRealisedGainPercentage.setText(transaction.getRealizedGainPercentage().toString() + " %");
        }
    }

    private void hideTabsInActionBar() {
        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }


}
