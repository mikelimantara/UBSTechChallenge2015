package com.prototype.ubs.techchallenge.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.MainActivity;
import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.CashDeposits;
import com.prototype.ubs.techchallenge.model.Funds;
import com.prototype.ubs.techchallenge.model.Insurance;
import com.prototype.ubs.techchallenge.model.Investment;
import com.prototype.ubs.techchallenge.model.OtherAsset;
import com.prototype.ubs.techchallenge.model.Portfolio;
import com.prototype.ubs.techchallenge.utils.Constants;

import org.joda.time.DateTime;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 12/6/2015.
 */
public class PortfolioOverviewFragment extends Fragment implements View.OnClickListener {
    private View v = null;
    private TextView txtLatestUpdateTime;
    private TextView txtTotalAssetValue;
    private TextView txtInvestmentMarketValue;
    private TextView txtInvestmentBookCost;
    private TextView txtInvestmentUnrealizedGain;
    private TextView txtCashMarketValue;
    private TextView txtInsurancePolicyValue;
    private TextView txtInsurancePremiumPaid;
    private TextView txtInsuranceOthers;
    private TextView txtInsuranceSumInsured;
    private TextView txtOtherAssetsMarketValue;
    private TextView txtOtherAssetsBookCost;
    private TextView txtOtherAssetsUnrealizedGain;
    private Spinner spinnerCurrency;
    private ArrayAdapter currencyAdapter;
    private LinearLayout investmentLayout;
    private LinearLayout cashLayout;
    private LinearLayout insuranceLayout;
    private LinearLayout otherAssetsLayout;

    private Portfolio portfolio;
    private NumberFormat currencyFormatter;
    private DecimalFormat percentageFormatter;
    private ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.portfolio_overview, container, false);
        initViews();

        actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        hideTabsOnActionBar();
        ((MainActivity)getActivity()).setMenuBarState(MainActivity.MenuBarState.DEFAULT);
        getActivity().invalidateOptionsMenu();


        currencyFormatter = NumberFormat.getCurrencyInstance();
        percentageFormatter = new DecimalFormat("###.## '%'");
        percentageFormatter.setMaximumFractionDigits(2);

        if (portfolio == null) {
            portfolio = generatePortfolio();
            ((MainActivity)(getActivity())).setPortfolio(portfolio);
        }

        setInitialData();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitleOnActionBar();
    }

    private void initViews() {
        txtLatestUpdateTime = (TextView) v.findViewById(R.id.latest_update_time);
        txtTotalAssetValue = (TextView) v.findViewById(R.id.overview_total_asset_value);
        txtInvestmentMarketValue = (TextView) v.findViewById(R.id.portfolio_investment_market_price);
        txtInvestmentBookCost = (TextView) v.findViewById(R.id.portfolio_investment_book_cost);
        txtInvestmentUnrealizedGain = (TextView) v.findViewById(R.id.portfolio_overview_unrealised_gain_loss);
        txtCashMarketValue = (TextView) v.findViewById(R.id.cash_deposit_market_value);
        txtInsurancePolicyValue = (TextView) v.findViewById(R.id.insurance_policy_value);
        txtInsurancePremiumPaid = (TextView) v.findViewById(R.id.insurance_premium_paid_to_date);
        txtInsuranceOthers = (TextView) v.findViewById(R.id.insurance_others);
        txtInsuranceSumInsured = (TextView) v.findViewById(R.id.insurance_sum_insured);
        txtOtherAssetsMarketValue = (TextView) v.findViewById(R.id.other_asset_market_price);
        txtOtherAssetsBookCost = (TextView) v.findViewById(R.id.other_asset_book_cost);
        txtOtherAssetsUnrealizedGain = (TextView) v.findViewById(R.id.other_asset_unrealised_gain_loss);
        spinnerCurrency = (Spinner) v.findViewById(R.id.portfolio_currency_spinner);
        currencyAdapter = new ArrayAdapter<String>(getActivity(), R.layout.currency_spinner_item, Constants.CURRENCIES);
        spinnerCurrency.setAdapter(currencyAdapter);

        investmentLayout = (LinearLayout) v.findViewById(R.id.overview_investment);
        cashLayout = (LinearLayout) v.findViewById(R.id.overview_cash);
        insuranceLayout = (LinearLayout) v.findViewById(R.id.overview_insurance);
        otherAssetsLayout = (LinearLayout) v.findViewById(R.id.overview_other_assets);
        investmentLayout.setOnClickListener(this);
        cashLayout.setOnClickListener(this);
        insuranceLayout.setOnClickListener(this);
        otherAssetsLayout.setOnClickListener(this);
    }

    private void hideTabsOnActionBar() {
        actionBar.removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    private void setTitleOnActionBar() {
        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("My Portfolio");
    }

    private void setInitialData() {
        txtLatestUpdateTime.setText("Updated as of 10:06");
        setUpInvestmentData();
        setUpCashDepositsData();
        setUpInsuranceData();
        setUpOtherAssetsData();
        txtTotalAssetValue.setText(currencyFormatter.format(portfolio.getTotalAssetValue()));
    }

    private void setUpInvestmentData() {
        Double investmentMarketValue = portfolio.getInvestmentMarketValue();
        Double investmentBookCost = portfolio.getInvestmentBookCost();
        Double investmentUnrealizedGainAmt = investmentMarketValue - investmentBookCost;
        Double investmentUnrealizedGainPercentage = investmentUnrealizedGainAmt / investmentBookCost * 100;
        txtInvestmentMarketValue.setText(currencyFormatter.format(investmentMarketValue));
        txtInvestmentBookCost.setText(currencyFormatter.format(investmentBookCost));
        txtInvestmentUnrealizedGain.setText(percentageFormatter.format(investmentUnrealizedGainPercentage)
                + " | " + currencyFormatter.format(investmentUnrealizedGainAmt));
        if (investmentUnrealizedGainAmt < 0) {
            txtInvestmentUnrealizedGain.setText("-" + txtInvestmentUnrealizedGain.getText());
            txtInvestmentUnrealizedGain.setTextColor(Color.RED);
        } else {
            txtInvestmentUnrealizedGain.setText("+" + txtInvestmentUnrealizedGain.getText());
            txtOtherAssetsUnrealizedGain.setTextColor(getResources().getColor(R.color.green));
        }
    }

    private void setUpCashDepositsData() {
        txtCashMarketValue.setText(currencyFormatter.format(portfolio.getCashDepositsMarketValue()));
    }

    private void setUpInsuranceData() {
        Double insurancePolicyValue = portfolio.getInsurancePolicyValue();
        Double insurancePremiumPaidToDate = portfolio.getPremiumPaidToDate();
        Double insuranceOthers = portfolio.getInsuranceOthers();
        Double insuranceSumInsured = portfolio.getInsuranceSumInsured();

        txtInsurancePolicyValue.setText(currencyFormatter.format(insurancePolicyValue));
        txtInsurancePremiumPaid.setText(currencyFormatter.format(insurancePremiumPaidToDate));
        txtInsuranceOthers.setText(currencyFormatter.format(insuranceOthers));
        txtInsuranceSumInsured.setText(currencyFormatter.format(insuranceSumInsured));
    }

    private void setUpOtherAssetsData() {
        Double otherAssetsMarketValue = portfolio.getOtherAssetsMarketValue();
        Double otherAssetsBookCost = portfolio.getOtherAssetsBookCost();

        Double otherAssetsUnrealizedGainAmt = otherAssetsMarketValue - otherAssetsBookCost;
        Double otherAssetsUnrealizedGainPercentage = otherAssetsUnrealizedGainAmt / otherAssetsBookCost * 100;

        txtOtherAssetsMarketValue.setText(currencyFormatter.format(otherAssetsMarketValue));
        txtOtherAssetsBookCost.setText(currencyFormatter.format(otherAssetsBookCost));
        txtOtherAssetsUnrealizedGain.setText(percentageFormatter.format(otherAssetsUnrealizedGainPercentage) +
                " | " + currencyFormatter.format(otherAssetsUnrealizedGainAmt));

        if (otherAssetsMarketValue < otherAssetsBookCost) {
            txtOtherAssetsUnrealizedGain.setText("-" + txtOtherAssetsUnrealizedGain.getText());
            txtOtherAssetsUnrealizedGain.setTextColor(Color.RED);
        } else {
            txtOtherAssetsUnrealizedGain.setText("+" + txtOtherAssetsUnrealizedGain.getText());
            txtOtherAssetsUnrealizedGain.setTextColor(getResources().getColor(R.color.green));
        }
    }

    private Portfolio generatePortfolio() {
        Portfolio portfolio = new Portfolio();

        addFunds(portfolio);
        addOtherInvestments(portfolio);
        addCashDeposits(portfolio);
        addInsurances(portfolio);
        addOtherAssets(portfolio);

        return portfolio;
    }

    private void addFunds(Portfolio portfolio) {
        for (int i = 1; i <= 4; i++) {
            Funds funds = new Funds();

            //Setting common attributes
            funds.setProductType("funds");
            funds.setName("Funds " + i);
            funds.setMarketValue(10300453.37);
            funds.setBookCost(10100453.37);
            funds.setUnrealizedGainAmt(funds.getMarketValue() - funds.getBookCost());
            funds.setUnrealizedGainPercentage(funds.getUnrealizedGainAmt()/funds.getBookCost() * 100);

            //Setting funds's attributes
            funds.setLaunchDate(new DateTime(2010,
                    (int) ((Math.random() * 12) + 1),
                    (int) ((Math.random() * 28) + 1),
                    0, 0, 0));
            funds.setPerUnitValue(Math.random() * 1000);
            funds.setSixMonthChangeAmt(33450.0);
            funds.setSixMonthChangePercentage(20.5);
            funds.setYesterdayPerformancePercentage(3.54);
            funds.setOneYearPerformancePercentage(17.3);
            funds.setThreeYearsPerformancePercentage(23.1);
            funds.setDividendYield(10.0);
            funds.setRiskLevel(5);
            portfolio.getInvestments().add(funds);
        }
    }

    private void addOtherInvestments(Portfolio portfolio) {

        for (int i = 1; i <= 4; i++) {
            Investment investment = new Investment();
            investment.setProductType("bonds");
            investment.setName("Bonds " + i);
            investment.setMarketValue(10300453.37);
            investment.setBookCost(10100453.37);
            investment.setUnrealizedGainAmt(investment.getMarketValue() - investment.getBookCost());
            investment.setUnrealizedGainPercentage(investment.getUnrealizedGainAmt()/investment.getBookCost()*100);
            portfolio.getInvestments().add(investment);
        }

        for (int i = 1; i <= 4; i++) {
            Investment investment = new Investment();
            investment.setProductType("equity");
            investment.setName("Equity " + i);
            investment.setMarketValue(10300453.37);
            investment.setBookCost(10100453.37);
            investment.setUnrealizedGainAmt(investment.getMarketValue() - investment.getBookCost());
            investment.setUnrealizedGainPercentage(investment.getUnrealizedGainAmt()/investment.getBookCost()*100);
            portfolio.getInvestments().add(investment);
        }
    }

    private void addCashDeposits(Portfolio portfolio) {
        CashDeposits cashDepositsLocal = new CashDeposits();
        cashDepositsLocal.setType("Local Currency");
        cashDepositsLocal.setMarketValue(100450.10);

        CashDeposits cashDepositsForeign = new CashDeposits();
        cashDepositsForeign.setType("Foreign Currency");
        cashDepositsForeign.setMarketValue(30230.50);

        List<CashDeposits> cashDeposits = new ArrayList<>();
        cashDeposits.add(cashDepositsLocal);
        cashDeposits.add(cashDepositsForeign);

        portfolio.setCashDeposits(cashDeposits);
    }

    private void addInsurances(Portfolio portfolio) {
        Insurance insurance = new Insurance();
        insurance.setName("Insurance 1");
        insurance.setPolicyValue(0.00);
        insurance.setPremiumPaidToDate(0.00);
        insurance.setOthers(0.00);
        insurance.setSumInsured(0.00);

        portfolio.getInsurances().add(insurance);
    }

    private void addOtherAssets(Portfolio portfolio) {
        OtherAsset otherAsset = new OtherAsset();
        otherAsset.setName("Other Asset 1");
        otherAsset.setMarketValue(10000.00);
        otherAsset.setBookCost(8000.00);
        otherAsset.setUnrealizedGainAmt(otherAsset.getMarketValue() - otherAsset.getBookCost());
        otherAsset.setUnrealizedGainPercentage(otherAsset.getUnrealizedGainAmt() /
                otherAsset.getBookCost() * 100);

        portfolio.getOtherAssets().add(otherAsset);
    }

    @Override
    public void onClick(View v) {
        if (v == investmentLayout) {
            InvestmentListFragment investmentFragment = new InvestmentListFragment();
            investmentFragment.setPortfolio(portfolio);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container, investmentFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (v == cashLayout) {

        } else if (v == insuranceLayout) {

        } else if (v == otherAssetsLayout) {

        }
    }
}
