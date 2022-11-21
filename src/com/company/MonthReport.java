package com.company;

import java.util.*;

public class MonthReport {
    private static class Item {
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

    public HashMap<Integer, ArrayList<MonthData>> getMonthReport() {
        return this.monthDataMap;
    }

    public void printReport() {
        Item mostProfitableItem;
        Item biggestWasteItem;

        for (Map.Entry<Integer, ArrayList<MonthData>> entry : monthDataMap.entrySet()) {
            mostProfitableItem = getMostProfitableItem(entry.getValue());
            biggestWasteItem = getBiggestWasteItem(entry.getValue());
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
            if (!data.isExpense) {
                monthDataExpense.add(data);
            }
        }

        ArrayList<Integer> profit = new ArrayList<>();
        for (MonthData data : monthDataExpense) {
            profit.add(data.sumOfOne * data.quantity);
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
            if (data.isExpense) {
                monthDataExpense.add(data);
            }
        }

        ArrayList<Integer> waste = new ArrayList<>();
        for (MonthData data : monthDataExpense) {
            waste.add(data.sumOfOne * data.quantity);
        }

        int maxWaste = Collections.max(waste, null);
        int indexOfBiggestWasteItem = waste.indexOf(maxWaste);
        Item item = new Item();
        item.name = monthDataExpense.get(indexOfBiggestWasteItem).itemName;
        item.sum = monthDataExpense.get(indexOfBiggestWasteItem).sumOfOne;

        return item;
    }
}
