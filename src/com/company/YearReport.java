package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YearReport {

    private ArrayList<YearlyData> yearReport = new ArrayList<>();
    private HashMap<Integer, Integer> monthlyProfit = new HashMap<>();
    private double avgIncome;
    private double avgConsumption;

    public void setYearReport(ArrayList<YearlyData> yearReport) {
        this.yearReport = yearReport;
    }

    public boolean isYearReportEmpty() {
        return yearReport.isEmpty();
    }

    public void printYearReport() {
        calculateMonthlyProfit(yearReport);
        calculateAvgConsumption(yearReport);
        calculateAvgIncome(yearReport);

        for (Map.Entry entry : monthlyProfit.entrySet()) {
            System.out.println("В месяце " + entry.getKey() +
                    " прибыль составила " + entry.getValue());
        }

        System.out.println("Средний расход за год " + avgConsumption +
                "\nСредний доход за год " + avgIncome);
    }

    private void calculateMonthlyProfit(ArrayList<YearlyData> yearReport) {
        for (int i = 0; i < yearReport.size(); i += 2) {
            if (yearReport.get(i).isExpense == false) {
                monthlyProfit.put(yearReport.get(i).month, (yearReport.get(i).amount - yearReport.get(i + 1).amount));
            } else {
                monthlyProfit.put(yearReport.get(i).month, (yearReport.get(i + 1).amount - yearReport.get(i).amount));
            }
        }
    }

    private void calculateAvgIncome(ArrayList<YearlyData> yearReport) {
        int sumIncome = 0;
        for (int i = 0; i < yearReport.size(); i++) {
            if(yearReport.get(i).isExpense == false) {
                sumIncome += yearReport.get(i).amount;
            }
        }
        avgIncome = sumIncome / (yearReport.size() / 2);
    }

    private void calculateAvgConsumption(ArrayList<YearlyData> yearReport) {
        int sumConsumption = 0;
        for (int i = 0; i < yearReport.size(); i++) {
            if(yearReport.get(i).isExpense == true) {
                sumConsumption += yearReport.get(i).amount;
            }
        }
        avgConsumption = sumConsumption / (yearReport.size() / 2);
    }
}
