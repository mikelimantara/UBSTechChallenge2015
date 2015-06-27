package com.prototype.ubs.techchallenge.model;

/**
 * Created by Michael on 26/6/2015.
 */
public class AssetAllocation {
    private String productName;
    private Double percentage;

    public AssetAllocation(String productName, Double percentage) {
        this.productName = productName;
        this.percentage = percentage;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPercentage() {
        return percentage;
    }
}
