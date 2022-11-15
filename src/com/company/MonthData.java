package com.company;

public class MonthData {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public MonthData(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
