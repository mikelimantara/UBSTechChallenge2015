package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.Transaction;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 19/6/2015.
 */
public class TransactionHistoryListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View v;
    private ListView transactionHistoryLv;
    private TransactionHistoryAdapter transactionHistoryAdapter;
    private List<Transaction> transactionList;
    private LinearLayout transactionDetailContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.transaction_history_list, container, false);
        initViews();

        prepareData();
        transactionHistoryAdapter = new TransactionHistoryAdapter(transactionList);
        transactionHistoryLv.setAdapter(transactionHistoryAdapter);
        transactionHistoryLv.setOnItemClickListener(this);

        return v;
    }

    private void initViews() {
        transactionHistoryLv = (ListView) v.findViewById(R.id.transaction_history_listview);
        transactionDetailContainer = (LinearLayout) v.findViewById(R.id.transaction_history_detail_container);
    }

    private void prepareData() {
        transactionList = new ArrayList<Transaction>();
        Transaction a = new Transaction();
        a.setAccountName("Aberdeen");
        a.setAccountNo("111-1111111");
        a.setTransactionDate(new DateTime(2015, 5, 20, 12, 31, 0));
        a.setTransactionRef("123-456-7890");
        a.setDescription("Buy 10 units @1003.85");
        a.setSettledAmount(10030.85);
        a.setOrderAmount(10030.85);
        a.setRealizedGainAmt(1300.23);
        a.setRealizedGainPercentage(5.67);

        Transaction b = new Transaction();
        b.setAccountName("Aberdeen 2");
        b.setAccountNo("111-1111111");
        b.setTransactionDate(new DateTime(2015, 5, 22, 12, 31, 0));
        b.setTransactionRef("123-456-7891");
        b.setDescription("Buy 10 units @1003.85");
        b.setSettledAmount(10030.85);
        b.setOrderAmount(10030.85);
        b.setRealizedGainAmt(500.23);
        b.setRealizedGainPercentage(3.50);

        transactionList.add(a);
        transactionList.add(b);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Transaction transaction = (Transaction) parent.getAdapter().getItem(position);
        TransactionHistoryDetailFragment transactionDetailFragment = new TransactionHistoryDetailFragment();
        transactionDetailFragment.setData(transaction);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_container, transactionDetailFragment);
        ft.addToBackStack(null);
        ft.commit();
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

            txtTransactionDate.setText(transaction.getStringDate());
            txtAccount.setText(transaction.getAccountName());
            txtDescription.setText(transaction.getDescription());
            txtSettledAmount.setText(transaction.getSettledAmount().toString());

            return v;
        }
    }
}