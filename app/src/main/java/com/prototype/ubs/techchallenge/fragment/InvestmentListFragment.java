package com.prototype.ubs.techchallenge.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.Funds;
import com.prototype.ubs.techchallenge.model.Investment;
import com.prototype.ubs.techchallenge.model.Portfolio;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael on 26/6/2015.
 */
public class InvestmentListFragment extends Fragment implements ExpandableListView.OnChildClickListener {
    private View v;
    private TextView txtTotalAssetValue;
    private TextView txtMarketValue;
    private TextView txtBookCost;
    private TextView txtUnrealizedGain;
    private Portfolio portfolio;
    private ExpandableListView investmentList;
    private InvestmentListAdapter investmentListAdapter;
    private NumberFormat currencyFormatter;
    private DecimalFormat percentageFormatter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.investment, container, false);
        initViews();

        currencyFormatter = NumberFormat.getCurrencyInstance();
        percentageFormatter = new DecimalFormat("###.## '%'");
        percentageFormatter.setMaximumFractionDigits(2);

        setInitialData();

        return v;
    }

    private void initViews() {
        txtTotalAssetValue = (TextView) v.findViewById(R.id.total_asset_value);
        txtBookCost = (TextView) v.findViewById(R.id.portfolio_investment_total_book_cost);
        txtMarketValue = (TextView) v.findViewById(R.id.portfolio_investment_totalmarket_price);
        txtUnrealizedGain = (TextView) v.findViewById(R.id.portfolio_investment_total_unrealised_capital);
        investmentList = (ExpandableListView) v.findViewById(R.id.investment_list);
        investmentListAdapter = new InvestmentListAdapter(portfolio);
        investmentList.setAdapter(investmentListAdapter);
        investmentList.setOnChildClickListener(this);
        setListViewHeight(investmentList, 0);
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    private void setInitialData() {
        txtTotalAssetValue.setText(currencyFormatter.format(portfolio.getTotalAssetValue()));
        txtMarketValue.setText(currencyFormatter.format(portfolio.getInvestmentMarketValue()));
        txtBookCost.setText(currencyFormatter.format(portfolio.getInvestmentBookCost()));

        Double unrealizedGainAmt = portfolio.getInvestmentMarketValue() - portfolio.getInvestmentBookCost();
        Double unrealizedGainPercentage = unrealizedGainAmt / portfolio.getInvestmentBookCost() * 100;

        if (unrealizedGainAmt < 0) {
            txtUnrealizedGain.setTextColor(Color.RED);
            txtUnrealizedGain.setText("-" + percentageFormatter.format(unrealizedGainPercentage) + " | "
                    + "-" + currencyFormatter.format(unrealizedGainAmt));
        } else {
            txtUnrealizedGain.setTextColor(Color.GREEN);
            txtUnrealizedGain.setText("+" + percentageFormatter.format(unrealizedGainPercentage) + " | "
                    + "+" + currencyFormatter.format(unrealizedGainAmt));
        }

    }

    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
                MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if (groupPosition == 1) {
            InvestmentDetailFragment investmentDetailFragment = new InvestmentDetailFragment();
            Funds selectedFunds = (Funds) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
            investmentDetailFragment.setFunds(selectedFunds);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, investmentDetailFragment)
                    .addToBackStack(null)
                    .commit();
        }

        return true;
    }

    private class InvestmentListAdapter extends BaseExpandableListAdapter {
        private LayoutInflater inflater;
        private Portfolio portfolio;
        private String[] investmentTypeHeader = {"Equity", "Funds", "Bonds"};
        private HashMap<String, List<Investment>> investmentTypes;
        private NumberFormat currencyFormatter;
        private DecimalFormat percentageFormatter;

        public InvestmentListAdapter(Portfolio portfolio) {
            inflater = LayoutInflater.from(getActivity());
            this.portfolio = portfolio;
            currencyFormatter = NumberFormat.getCurrencyInstance();
            percentageFormatter = new DecimalFormat("###.## '%'");
            percentageFormatter.setMaximumFractionDigits(2);
            investmentTypes = new HashMap<>();
            for (int i = 0; i < investmentTypeHeader.length; i++) {
                investmentTypes.put(investmentTypeHeader[i], new ArrayList<Investment>());
            }
            groupInvestments(portfolio.getInvestments());
        }

        @Override
        public int getGroupCount() {
            return investmentTypeHeader.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return investmentTypes.get(investmentTypeHeader[groupPosition]).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return investmentTypeHeader[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return investmentTypes.get(investmentTypeHeader[groupPosition]).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = inflater.inflate(R.layout.investment_list_item, parent, false);
            }

            TextView txtInvestmentType = (TextView) v.findViewById(R.id.investment_type);
            TextView txtInvestmentTypeValue = (TextView) v.findViewById(R.id.investment_type_value);
            TextView txtInvestmentUnrealizedGain = (TextView) v.findViewById(R.id.investment_type_unrealised_capital_gain);

            List<Investment> investments = investmentTypes.get(getGroup(groupPosition));
            Double totalInvestmentMarketValue = 0.00;
            Double totalInvestmentBookCost = 0.00;
            for (Investment i: investments) {
                totalInvestmentMarketValue += i.getMarketValue();
                totalInvestmentBookCost += i.getBookCost();
            }
            Double unrealizedGainAmt = totalInvestmentMarketValue - totalInvestmentBookCost;
            Double unrealizedGainPercentage = unrealizedGainAmt / totalInvestmentBookCost * 100;

            txtInvestmentType.setText((String)getGroup(groupPosition));
            txtInvestmentTypeValue.setText(currencyFormatter.format(totalInvestmentMarketValue));

            if (totalInvestmentMarketValue < totalInvestmentBookCost) {
                txtInvestmentUnrealizedGain.setTextColor(Color.RED);
                txtInvestmentUnrealizedGain.setText("-" + percentageFormatter.format(unrealizedGainPercentage) + " | " +
                    "-" + currencyFormatter.format(unrealizedGainAmt));
            } else {
                txtInvestmentUnrealizedGain.setTextColor(Color.GREEN);
                txtInvestmentUnrealizedGain.setText("+" + percentageFormatter.format(unrealizedGainPercentage) + " | " +
                        "+" + currencyFormatter.format(unrealizedGainAmt));
            }

            return v;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.investment_list_sub_item, parent, false);
            }

            TextView txtInvestmentType = (TextView) v.findViewById(R.id.investment_type);
            TextView txtInvestmentTypeValue = (TextView) v.findViewById(R.id.investment_type_value);
            TextView txtInvestmentUnrealizedGain = (TextView) v.findViewById(R.id.investment_type_unrealised_capital_gain);

            Investment investment = (Investment) getChild(groupPosition, childPosition);
            txtInvestmentType.setText(investment.getName());
            txtInvestmentTypeValue.setText(currencyFormatter.format(investment.getMarketValue()));

            if (investment.getMarketValue() < investment.getBookCost()) {
                txtInvestmentUnrealizedGain.setTextColor(Color.RED);
                txtInvestmentUnrealizedGain.setText("-" + percentageFormatter.format(investment.getUnrealizedGainPercentage()) + " | " +
                        "-" + currencyFormatter.format(investment.getUnrealizedGainAmt()));
            } else {
                txtInvestmentUnrealizedGain.setTextColor(Color.GREEN);
                txtInvestmentUnrealizedGain.setText("+" + percentageFormatter.format(investment.getUnrealizedGainPercentage()) + " | " +
                        "+" + currencyFormatter.format(investment.getUnrealizedGainAmt()));
            }

            return v;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private void groupInvestments(List<Investment> investmentList) {
            for (Investment investment: investmentList) {
                if (investment.getProductType().equals("equity")) {
                    investmentTypes.get(investmentTypeHeader[0]).add(investment);
                } else if (investment.getProductType().equals("funds")) {
                    investmentTypes.get(investmentTypeHeader[1]).add(investment);
                } else if (investment.getProductType().equals("bonds")) {
                    investmentTypes.get(investmentTypeHeader[2]).add(investment);
                }
            }
        }
    }
}
