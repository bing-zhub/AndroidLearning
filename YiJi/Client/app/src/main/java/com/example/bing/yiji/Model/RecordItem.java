package com.example.bing.yiji.Model;

import com.payment.entity.Payment;

import java.util.Date;
import java.util.List;

public class RecordItem {
    private Date date;
    private int sum;
    private int income;
    private int outcome;
    private List<Payment> contents;

    public RecordItem() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public List<Payment> getContents() {
        return contents;
    }

    public void setContents(List<Payment> contents) {
        this.contents = contents;
    }
}
