package com.prototype.ubs.techchallenge.model;

/**
 * Created by Michael on 24/6/2015.
 */
public class Investment {
    private String productType;
    private String name;
    private Double marketValue;
    private Double bookCost;
    private Double unrealizedGainAmt;
    private Double unrealizedGainPercentage;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getUnrealizedGainAmt() {
        return unrealizedGainAmt;
    }

    public void setUnrealizedGainAmt(Double unrealizedGainAmt) {
        this.unrealizedGainAmt = unrealizedGainAmt;
    }

    public Double getUnrealizedGainPercentage() {
        return unrealizedGainPercentage;
    }

    public void setUnrealizedGainPercentage(Double unrealizedGainPercentage) {
        this.unrealizedGainPercentage = unrealizedGainPercentage;
    }

    public Double getBookCost() {
        return bookCost;
    }

    public void setBookCost(Double bookCost) {
        this.bookCost = bookCost;
    }
}
