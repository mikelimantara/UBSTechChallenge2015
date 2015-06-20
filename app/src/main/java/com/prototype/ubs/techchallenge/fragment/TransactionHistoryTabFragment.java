package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 19/6/2015.
 */
public class TransactionHistoryTabFragment extends Fragment {
    private View v;
    private ListView transactionHistoryLv;
    private TransactionHistoryAdapter transactionHistoryAdapter;
    private List<Transaction> transactionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_history, container, false);
        initViews();

        prepareData();
        transactionHistoryAdapter = new TransactionHistoryAdapter(transactionList);
        transactionHistoryLv.setAdapter(transactionHistoryAdapter);

        return v;
    }

    private void initViews() {
        transactionHistoryLv = (ListView) v.findViewById(R.id.transaction_history_listview);
    }

    private void prepareData() {
        transactionList = new ArrayList<Transaction>();
        Transaction a = new Transaction();
        a.setAccountName("Aberdeen");
        a.setTransactionDate("21 May 2015");
        a.setTransactionRef("12345");
        a.setDescription("Buy 10 units @1003.85");
        a.setSettledAmount(10030.85);
        a.setOrderAmount(10030.85);
        a.setRealizedGainAmt(1300.23);
        a.setRealizedGainPercentage(5.67);

        Transaction b = new Transaction();
        b.setAccountName("Aberdeen 2");
        b.setTransactionDate("21 May 2015");
        b.setTransactionRef("12345");
        b.setDescription("Buy 10 units @1003.85");
        b.setSettledAmount(10030.85);
        b.setOrderAmount(10030.85);
        b.setRealizedGainAmt(1300.23);
        b.setRealizedGainPercentage(5.67);

        transactionList.add(a);
        transactionList.add(b);
    }

    private class TransactionHistoryAdapter extends BaseAdapter {
        private List<Transaction> transactionHistoryList = new ArrayList<Transaction>();
        private LayoutInflater inflater = null;

        public TransactionHistoryAdapter(List<Transaction> transactionHistoryList) {
            this.transactionHistoryList = transactionHistoryList;
            inflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getCount() {
            return transactionHistoryList.size();
        }

        @Override
        public Object getItem(int position) {
            return transactionHistoryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.transaction_history_item, null);
            }

            Transaction transaction = (Transaction) getItem(position);

            TextView txtTransactionDate = (TextView) v.findViewById(R.id.transaction_history_date);
            TextView txtAccount = (TextView) v.findViewById(R.id.transaction_history_account);
            TextView txtDescription = (TextView) v.findViewById(R.id.transaction_history_desc);
            TextView txtSettledAmount = (TextView) v.findViewById(R.id.transaction_history_amount);

            txtTransactionDate.setText(transaction.getTransactionDate());
            txtAccount.setText(transaction.getAccountName());
            txtDescription.setText(transaction.getDescription());
            txtSettledAmount.setText(transaction.getSettledAmount().toString());

            return v;
        }
    }
}
