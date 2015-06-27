package com.prototype.ubs.techchallenge.model;

/**
 * Created by Michael on 24/6/2015.
 */
public class Insurance {
    private String name;
    private Double policyValue;
    private Double premiumPaidToDate;
    private Double others;
    private Double sumInsured;

    public Double getPolicyValue() {
        return policyValue;
    }

    public void setPolicyValue(Double policyValue) {
        this.policyValue = policyValue;
    }

    public Double getPremiumPaidToDate() {
        return premiumPaidToDate;
    }

    public void setPremiumPaidToDate(Double premiumPaidToDate) {
        this.premiumPaidToDate = premiumPaidToDate;
    }

    public Double getOthers() {
        return others;
    }

    public void setOthers(Double others) {
        this.others = others;
    }

    public Double getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(Double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
