package com.prototype.ubs.techchallenge.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * Created by Michael on 19/6/2015.
 */
public class Transaction implements Comparable<Transaction> {
    private String transactionRef;
    private DateTime transactionDate;
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

    public DateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(DateTime transactionDate) {
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

    @Override
    public int compareTo(Transaction other) {
        DateTime otherDate = other.getTransactionDate();

        if (transactionDate.getYear() > otherDate.getYear()) {
            return -1;
        } else if (transactionDate.getYear() < otherDate.getYear()) {
            return 1;
        }

        if (transactionDate.getMonthOfYear() > otherDate.getMonthOfYear()) {
            return -1;
        } else if (transactionDate.getMonthOfYear() < otherDate.getMonthOfYear()) {
            return 1;
        }

        if (transactionDate.getDayOfMonth() > otherDate.getDayOfMonth()) {
            return -1;
        } else if (transactionDate.getDayOfMonth() < otherDate.getDayOfMonth()) {
            return 1;
        }

        if (transactionDate.getHourOfDay() > otherDate.getHourOfDay()) {
            return -1;
        } else if (transactionDate.getHourOfDay() < otherDate.getHourOfDay()) {
            return 1;
        }

        if (transactionDate.getMinuteOfDay() > otherDate.getMinuteOfDay()) {
            return -1;
        } else if (transactionDate.getMinuteOfDay() < otherDate.getMinuteOfDay()) {
            return 1;
        }

        if (transactionDate.getSecondOfDay() > otherDate.getSecondOfDay()) {
            return -1;
        } else if (transactionDate.getSecondOfDay() < otherDate.getSecondOfDay()) {
            return 1;
        }

        return 0;
    }

    public String getStringDate() {
        DateTimeFormatter format = new DateTimeFormatterBuilder().appendDayOfMonth(2)
                .appendLiteral(' ')
                .appendMonthOfYearShortText()
                .appendLiteral(' ')
                .appendYear(4,4)
                .appendLiteral(' ')
                .appendClockhourOfDay(2)
                .appendLiteral(':')
                .appendMinuteOfHour(2)
                .appendLiteral(' ')
                .appendHalfdayOfDayText()
                .toFormatter();

        return transactionDate.toString(format);
    }
}
