package com.prototype.ubs.techchallenge.model;

import org.joda.time.DateTime;

/**
 * Created by Michael on 24/6/2015.
 */
public class Funds extends Investment {
    private DateTime launchDate;
    private Double perUnitValue;
    private Double sixMonthChangeAmt;
    private Double sixMonthChangePercentage;
    private Double yesterdayPerformancePercentage;
    private Double oneYearPerformancePercentage;
    private Double threeYearsPerformancePercentage;
    private Double dividendYield;
    private Integer riskLevel;

    public DateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(DateTime launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPerUnitValue() {
        return perUnitValue;
    }

    public void setPerUnitValue(Double perUnitValue) {
        this.perUnitValue = perUnitValue;
    }

    public Double getSixMonthChangeAmt() {
        return sixMonthChangeAmt;
    }

    public void setSixMonthChangeAmt(Double sixMonthChangeAmt) {
        this.sixMonthChangeAmt = sixMonthChangeAmt;
    }

    public Double getSixMonthChangePercentage() {
        return sixMonthChangePercentage;
    }

    public void setSixMonthChangePercentage(Double sixMonthChangePercentage) {
        this.sixMonthChangePercentage = sixMonthChangePercentage;
    }

    public Double getYesterdayPerformancePercentage() {
        return yesterdayPerformancePercentage;
    }

    public void setYesterdayPerformancePercentage(Double yesterdayPerformancePercentage) {
        this.yesterdayPerformancePercentage = yesterdayPerformancePercentage;
    }

    public Double getOneYearPerformancePercentage() {
        return oneYearPerformancePercentage;
    }

    public void setOneYearPerformancePercentage(Double oneYearPerformancePercentage) {
        this.oneYearPerformancePercentage = oneYearPerformancePercentage;
    }

    public Double getThreeYearsPerformancePercentage() {
        return threeYearsPerformancePercentage;
    }

    public void setThreeYearsPerformancePercentage(Double threeYearsPerformancePercentage) {
        this.threeYearsPerformancePercentage = threeYearsPerformancePercentage;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }
}
