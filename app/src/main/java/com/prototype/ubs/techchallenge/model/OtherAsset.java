package com.prototype.ubs.techchallenge.model;

/**
 * Created by Michael on 26/6/2015.
 */
public class OtherAsset {
    private String name;
    private Double marketValue;
    private Double bookCost;
    private Double unrealizedGainAmt;
    private Double unrealizedGainPercentage;

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getBookCost() {
        return bookCost;
    }

    public void setBookCost(Double bookCost) {
        this.bookCost = bookCost;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
