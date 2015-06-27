package com.prototype.ubs.techchallenge.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael on 24/6/2015.
 */
public class Portfolio {

    // Holdings
    private List<Investment> investments;
    private List<CashDeposits> cashDeposits;
    private List<Insurance> insurances;
    private List<OtherAsset> otherAssets;

    public Portfolio() {
        investments = new ArrayList<>();
        cashDeposits = new ArrayList<>();
        insurances = new ArrayList<>();
        otherAssets = new ArrayList<>();
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<CashDeposits> getCashDeposits() {
        return cashDeposits;
    }

    public void setCashDeposits(List<CashDeposits> cashDeposits) {
        this.cashDeposits = cashDeposits;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<OtherAsset> getOtherAssets() {
        return otherAssets;
    }

    public void setOtherAssets(List<OtherAsset> otherAssets) {
        this.otherAssets = otherAssets;
    }

    public Double getInvestmentMarketValue() {
        Double totalInvMarketValue = 0.00;
        for (Investment investment: investments) {
            totalInvMarketValue += investment.getMarketValue();
        }

        return totalInvMarketValue;
    }

    public Double getInvestmentBookCost() {
        Double totalInvBookCost = 0.00;
        for (Investment investment: investments) {
            totalInvBookCost += investment.getBookCost();
        }

        return totalInvBookCost;
    }

    public Double getCashDepositsMarketValue() {
        Double totalCashDepositsMarketValue = 0.00;
        for (CashDeposits cash: cashDeposits) {
            totalCashDepositsMarketValue += cash.getMarketValue();
        }

        return totalCashDepositsMarketValue;
    }

    public Double getInsurancePolicyValue() {
        Double totalPolicyValue = 0.00;
        for (Insurance insurance: insurances) {
            totalPolicyValue += insurance.getPolicyValue();
        }

        return totalPolicyValue;
    }

    public Double getPremiumPaidToDate() {
        Double totalPremiumPaidToDate = 0.00;
        for (Insurance insurance: insurances) {
            totalPremiumPaidToDate += insurance.getPremiumPaidToDate();
        }

        return totalPremiumPaidToDate;
    }

    public Double getInsuranceOthers() {
        Double totalOthers = 0.00;
        for (Insurance insurance: insurances) {
            totalOthers += insurance.getOthers();
        }

        return totalOthers;
    }

    public Double getInsuranceSumInsured() {
        Double totalSumInsured = 0.00;
        for (Insurance insurance: insurances) {
            totalSumInsured += insurance.getSumInsured();
        }

        return totalSumInsured;
    }

    public Double getOtherAssetsMarketValue() {
        Double totalMarketValue = 0.00;
        for (OtherAsset other: otherAssets) {
            totalMarketValue += other.getMarketValue();
        }

        return totalMarketValue;
    }

    public Double getOtherAssetsBookCost() {
        Double totalBookCost = 0.00;
        for (OtherAsset other: otherAssets) {
            totalBookCost += other.getBookCost();
        }

        return totalBookCost;
    }

    public Double getTotalAssetValue() {
        Double totalAssetValue = 0.00;
        totalAssetValue += getInvestmentMarketValue();
        totalAssetValue += getCashDepositsMarketValue();
        totalAssetValue += getInsurancePolicyValue();
        totalAssetValue += getOtherAssetsMarketValue();
        return totalAssetValue;
    }

    public HashMap<String, List<AssetAllocation>> constructAssetAllocationBasedOnProductType() {
        Double totalAssetValue = getTotalAssetValue();

        HashMap<String, List<AssetAllocation>> assetAllocation = new HashMap<>();
        assetAllocation.put("Equity", new ArrayList<AssetAllocation>());
        assetAllocation.put("Funds", new ArrayList<AssetAllocation>());
        assetAllocation.put("Bonds", new ArrayList<AssetAllocation>());
        assetAllocation.put("Cash/Deposits", new ArrayList<AssetAllocation>());
        assetAllocation.put("Insurances", new ArrayList<AssetAllocation>());
        assetAllocation.put("Other Assets", new ArrayList<AssetAllocation>());

        for (Investment investment: investments) {
            if (investment.getProductType().equals("equity")) {
                assetAllocation.get("Equity").add(new AssetAllocation(investment.getName(),
                        investment.getMarketValue()/totalAssetValue*100));
            } else if (investment.getProductType().equals("funds")) {
                assetAllocation.get("Funds").add(new AssetAllocation(investment.getName(),
                        investment.getMarketValue()/totalAssetValue*100));
            } else if (investment.getProductType().equals("bonds")) {
                assetAllocation.get("Bonds").add(new AssetAllocation(investment.getName(),
                        investment.getMarketValue()/totalAssetValue*100));
            }
        }

        for (CashDeposits cash: cashDeposits) {
            assetAllocation.get("Cash/Deposits").add(new AssetAllocation(cash.getType(),
                    cash.getMarketValue()/totalAssetValue*100));
        }

        for (Insurance insurance: insurances) {
            assetAllocation.get("Insurances").add(new AssetAllocation(insurance.getName(),
                    insurance.getSumInsured()/totalAssetValue*100));
        }

        for (OtherAsset otherAsset: otherAssets) {
            assetAllocation.get("Other Assets").add(new AssetAllocation(otherAsset.getName(),
                    otherAsset.getMarketValue()/totalAssetValue*100));
        }

        return assetAllocation;
    }
}
