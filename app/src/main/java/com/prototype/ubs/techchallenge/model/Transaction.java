package com.prototype.ubs.techchallenge.model;

/**
 * Created by Michael on 19/6/2015.
 */
public class Transaction {
    private String transactionRef;
    private String transactionDate;
    private String accountName;
    private String accountNo;
    private String description;
    private Double orderAmount;
    private Double settledAmount;
    private Double realizedGainAmt;
    private Double realizedGainPercentage;

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getSettledAmount() {
        return settledAmount;
    }

    public void setSettledAmount(Double settledAmount) {
        this.settledAmount = settledAmount;
    }

    public Double getRealizedGainAmt() {
        return realizedGainAmt;
    }

    public void setRealizedGainAmt(Double realizedGainAmt) {
        this.realizedGainAmt = realizedGainAmt;
    }

    public Double getRealizedGainPercentage() {
        return realizedGainPercentage;
    }

    public void setRealizedGainPercentage(Double realizedGainPercentage) {
        this.realizedGainPercentage = realizedGainPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
