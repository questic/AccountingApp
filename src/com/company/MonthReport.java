package com.company;

import java.util.*;

public class MonthReport {
    private class Item {
        String name;
        int sum;
    }

    private final HashMap<Integer, ArrayList<MonthData>> monthDataMap = new HashMap<>();

    public void addMonth(int month, ArrayList<MonthData> monthData) {
        monthDataMap.put(month, monthData);
    }

    public boolean isMonthReportEmpty() {
        return monthDataMap.isEmpty();
    }

    public void printReport() {
        Item mostProfitableItem = new Item();
        Item biggestWasteItem = new Item();

        for (Map.Entry entry : monthDataMap.entrySet()) {
            mostProfitableItem = getMostProfitableItem((ArrayList<MonthData>) entry.getValue());
            biggestWasteItem = getBiggestWasteItem((ArrayList<MonthData>) entry.getValue());
            System.out.println("В месяце " + entry.getKey() +
                    " самый прибыльный товар " + mostProfitableItem.name +
                    " с ценой " + mostProfitableItem.sum +
                    ". Самый затратный товар " + biggestWasteItem.name +
                    " с ценой " + biggestWasteItem.sum);
        }
    }

    private Item getMostProfitableItem(ArrayList<MonthData> monthData) {
        ArrayList<MonthData> monthDataExpense = new ArrayList<>();
        for (MonthData data : monthData) {
            if (data.isExpense == false) {
                monthDataExpense.add(data);
            }
        }

        ArrayList<Integer> profit = new ArrayList<>();
        for (int i = 0; i < monthDataExpense.size(); i++) {
            profit.add(monthDataExpense.get(i).sumOfOne * monthDataExpense.get(i).quantity);
        }

        int maxProfit = Collections.max(profit, null);
        int indexOfMostProfitableItem = profit.indexOf(maxProfit);
        Item item = new Item();
        item.name = monthDataExpense.get(indexOfMostProfitableItem).itemName;
        item.sum = monthDataExpense.get(indexOfMostProfitableItem).sumOfOne;

        return item;
    }

    private Item getBiggestWasteItem(ArrayList<MonthData> monthData) {
        ArrayList<MonthData> monthDataExpense = new ArrayList<>();
        for (MonthData data : monthData) {
            if (data.isExpense == true) {
                monthDataExpense.add(data);
            }
        }

        ArrayList<Integer> waste = new ArrayList<>();
        for (int i = 0; i < monthDataExpense.size(); i++) {
            waste.add(monthDataExpense.get(i).sumOfOne * monthDataExpense.get(i).quantity);
        }

        int maxWaste = Collections.max(waste, null);
        int indexOfBiggestWasteItem = waste.indexOf(maxWaste);
        Item item = new Item();
        item.name = monthDataExpense.get(indexOfBiggestWasteItem).itemName;
        item.sum = monthDataExpense.get(indexOfBiggestWasteItem).sumOfOne;

        return item;
    }
}
