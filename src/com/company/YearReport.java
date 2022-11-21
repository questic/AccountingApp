package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YearReport {

    private ArrayList<YearlyData> yearReport = new ArrayList<>();
    private final HashMap<Integer, Integer> monthlyProfit = new HashMap<>();
    private int avgIncome;
    private int avgConsumption;

    public void setYearReport(ArrayList<YearlyData> yearReport) {

        this.yearReport = yearReport;
    }

    public ArrayList<YearlyData> getYearReport( ) {
        return this.yearReport;
    }

    public boolean isYearReportEmpty() {
        return yearReport.isEmpty();
    }

    public void printYearReport() {
        calculateMonthlyProfit(yearReport);
        calculateAvgConsumption(yearReport);
        calculateAvgIncome(yearReport);

        for (Map.Entry<Integer, Integer> entry : monthlyProfit.entrySet()) {
            System.out.println("В месяце " + entry.getKey() +
                    " прибыль составила " + entry.getValue());
        }

        System.out.println("Средний расход за год " + avgConsumption +
                "\nСредний доход за год " + avgIncome);
    }

    private void calculateMonthlyProfit(ArrayList<YearlyData> yearReport) {
        for (int i = 0; i < yearReport.size(); i += 2) {
            if (!yearReport.get(i).isExpense) {
                monthlyProfit.put(yearReport.get(i).month, (yearReport.get(i).amount - yearReport.get(i + 1).amount));
            } else {
                monthlyProfit.put(yearReport.get(i).month, (yearReport.get(i + 1).amount - yearReport.get(i).amount));
            }
        }
    }

    private void calculateAvgIncome(ArrayList<YearlyData> yearReport) {
        int sumIncome = 0;
        for (YearlyData yearlyData : yearReport) {
            if (!yearlyData.isExpense) {
                sumIncome += yearlyData.amount;
            }
        }
        avgIncome = sumIncome / (yearReport.size() / 2);
    }

    private void calculateAvgConsumption(ArrayList<YearlyData> yearReport) {
        int sumConsumption = 0;
        for (YearlyData yearlyData : yearReport) {
            if (yearlyData.isExpense) {
                sumConsumption += yearlyData.amount;
            }
        }
        avgConsumption = sumConsumption / (yearReport.size() / 2);
    }
}
