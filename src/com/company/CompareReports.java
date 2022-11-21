package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompareReports {
    private HashMap<Integer, Integer> generalIncomeMonth = new HashMap<>();
    private HashMap<Integer, Integer> generalConsumptionMonth = new HashMap<>();
    private HashMap<Integer, ArrayList<MonthData>> monthReport;
    private ArrayList<YearlyData> yearReport;

    public CompareReports(HashMap<Integer, ArrayList<MonthData>> monthReport, ArrayList<YearlyData> yearReport) {
        this.monthReport = monthReport;
        this.yearReport = yearReport;
        this.calculateMonthIncomeAndConsumption(this.monthReport);
        this.compareSums(this.yearReport, this.generalConsumptionMonth, this.generalIncomeMonth);
    }

    private void calculateMonthIncomeAndConsumption(HashMap<Integer, ArrayList<MonthData>> monthReport) {
        for (Map.Entry<Integer, ArrayList<MonthData>> entry : monthReport.entrySet()) {
            for (MonthData data : entry.getValue()) {
                if (!data.isExpense) {
                    if (generalIncomeMonth.containsKey(entry.getKey())) {
                        generalIncomeMonth.put(entry.getKey(), generalIncomeMonth.get(entry.getKey()) + data.sumOfOne * data.quantity);
                    } else {
                        generalIncomeMonth.put(entry.getKey(), data.sumOfOne * data.quantity);
                    }
                } else {
                    if (generalConsumptionMonth.containsKey(entry.getKey())) {
                        generalConsumptionMonth.put(entry.getKey(), generalConsumptionMonth.get(entry.getKey()) + data.sumOfOne * data.quantity);
                    } else {
                        generalConsumptionMonth.put(entry.getKey(), data.sumOfOne * data.quantity);
                    }
                }
            }
        }
    }

    private void compareSums(ArrayList<YearlyData> yearReport, HashMap<Integer, Integer> generalConsumptionMonth, HashMap<Integer, Integer> generalIncomeMonth) {
        for (YearlyData data : yearReport) {
            if (!data.isExpense) {
                if (data.amount != generalIncomeMonth.get(data.month)) {
                    System.out.printf("В месяце %d не совпадение годового и мячного отчёта по прибыли \n", data.month);
                }
            } else
                if (data.amount != generalConsumptionMonth.get(data.month)) {
                    System.out.printf("В месяце %d не совпадение годового и мячного отчёта по убыткам \n", data.month);
                }
        }
        System.out.println("Сверка закончена. \n");
    }
}
