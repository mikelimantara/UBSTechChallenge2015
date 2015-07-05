package com.prototype.ubs.techchallenge.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shelviadwihotama on 4/7/15.
 */
public class PortfolioOverview2 extends Fragment implements View.OnClickListener {
    private View v;
    private LinearLayout investmentLayout;
    private Portfolio portfolio;
    private Spinner spinnerCurrency;
    private ArrayAdapter<String> currencyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.portfolio_investment_3, container, false);
        setUpToolbar();
        initViews();

        if (portfolio == null) {
            portfolio = generatePortfolio();
        }

        return v;
    }

    private void setUpToolbar() {
        ((MainActivity) (getActivity())).setToolbarBasedOnContent("Portfolio Overview",
                MainActivity.MenuBarState.DEFAULT);
    }

    private void initViews() {
        investmentLayout = (LinearLayout) v.findViewById(R.id.portfolio_overview_investment);
        investmentLayout.setOnClickListener(this);

        spinnerCurrency = (Spinner) v.findViewById(R.id.spinner_overview_currency);
        currencyAdapter = new ArrayAdapter<String>(getActivity(), R.layout.currency_spinner_item, Constants.CURRENCIES);
        spinnerCurrency.setAdapter(currencyAdapter);
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
}
