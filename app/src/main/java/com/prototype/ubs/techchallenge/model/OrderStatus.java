package com.prototype.ubs.techchallenge.model;

import org.joda.time.DateTime;

/**
 * Created by Michael on 28/6/2015.
 */
public class OrderStatus {

    public enum Status {
        PENDING, EXECUTED, CANCELLED
    }

    public enum TransactionType {
        BUY, SELL, SWITCH, DIVIDEND
    }

    private DateTime orderDate;
    private String accountName;
    private TransactionType transactionType;
    private Double orderAmount;
    private Status status;

    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
