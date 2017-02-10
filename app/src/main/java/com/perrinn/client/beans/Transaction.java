package com.perrinn.client.beans;

import java.util.Date;

/**
 * Created by alessand.silacci on 30.12.2016.
 */

public class Transaction {
    private String iban;
    private String value;
    private double amount;
    private Date transactionDate;

    public Transaction(String iban, String value, double amount, Date transactionDate) {
        this.iban = iban;
        this.value = value;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Transaction() {
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
