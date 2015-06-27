package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.Funds;
import com.prototype.ubs.techchallenge.model.Investment;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Michael on 26/6/2015.
 */
public class InvestmentDetailFragment extends Fragment {
    private View v;
    private TextView txtProductName;
    private TextView txtLaunchDate;
    private TextView txtValuePerUnit;
    private TextView txtSixMonthChange;
    private TextView txtYesterdayPerf;
    private TextView txtOneYearPerf;
    private TextView txtThreeYearPerf;
    private TextView txtDividendYield;
    private TextView txtRiskLevel;
    private Funds funds;
    private DecimalFormat percentageFormatter;
    private NumberFormat currencyFormatter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.investment_item_detail, container, false);
        initViews();

        currencyFormatter = NumberFormat.getCurrencyInstance();
        percentageFormatter = new DecimalFormat("###.## '%'");
        percentageFormatter.setMaximumFractionDigits(2);

        setInitialData();

        return v;
    }

    public void setFunds(Funds funds) {
        this.funds = funds;
    }

    private void setInitialData() {
        txtProductName.setText(funds.getName());
        DateTimeFormatter dateFormat = new DateTimeFormatterBuilder()
                .appendDayOfMonth(1)
                .appendLiteral(" ")
                .appendMonthOfYearText()
                .appendLiteral(" ")
                .appendYear(4,4)
                .toFormatter();

        txtLaunchDate.setText(funds.getLaunchDate().toString(dateFormat));
        txtValuePerUnit.setText(currencyFormatter.format(funds.getPerUnitValue()));

        if (funds.getSixMonthChangeAmt() < 0) {
            txtSixMonthChange.setText("-" + percentageFormatter.format(funds.getSixMonthChangePercentage())
                    + " | " + "-" + currencyFormatter.format(funds.getSixMonthChangeAmt()));
        } else {
            txtSixMonthChange.setText("+" + percentageFormatter.format(funds.getSixMonthChangePercentage())
                    + " | " + "+" + currencyFormatter.format(funds.getSixMonthChangeAmt()));
        }

        if (funds.getYesterdayPerformancePercentage() < 0) {
            txtYesterdayPerf.setText("-" + percentageFormatter.format(funds.getYesterdayPerformancePercentage()));
        } else {
            txtYesterdayPerf.setText("+" + percentageFormatter.format(funds.getYesterdayPerformancePercentage()));
        }

        if (funds.getOneYearPerformancePercentage() < 0) {
            txtOneYearPerf.setText("-" + percentageFormatter.format(funds.getOneYearPerformancePercentage()));
        } else {
            txtOneYearPerf.setText("+" + percentageFormatter.format(funds.getOneYearPerformancePercentage()));
        }

        if (funds.getThreeYearsPerformancePercentage() < 0) {
            txtThreeYearPerf.setText("-" + percentageFormatter.format(funds.getThreeYearsPerformancePercentage()));
        } else {
            txtThreeYearPerf.setText("+" + percentageFormatter.format(funds.getThreeYearsPerformancePercentage()));
        }

        if (funds.getDividendYield() < 0) {
            txtDividendYield.setText("-" + percentageFormatter.format(funds.getDividendYield()));
        } else {
            txtDividendYield.setText("+" + percentageFormatter.format(funds.getDividendYield()));
        }

        txtRiskLevel.setText(Integer.toString(funds.getRiskLevel()));

    }

    private void initViews() {
        txtProductName = (TextView) v.findViewById(R.id.investment_market_name);
        txtLaunchDate = (TextView) v.findViewById(R.id.investment_launch_date);
        txtValuePerUnit = (TextView) v.findViewById(R.id.investment_current_value);
        txtSixMonthChange = (TextView) v.findViewById(R.id.investment_six_month_change);
        txtYesterdayPerf = (TextView) v.findViewById(R.id.investment_yesterday_performance_percentage);
        txtOneYearPerf = (TextView) v.findViewById(R.id.investment_one_year_performance_percentage);
        txtThreeYearPerf = (TextView) v.findViewById(R.id.investment_three_year_performance_percentage);
        txtDividendYield = (TextView) v.findViewById(R.id.investment_dividend_yield);
        txtRiskLevel = (TextView) v.findViewById(R.id.investment_risk_level);
    }
}
