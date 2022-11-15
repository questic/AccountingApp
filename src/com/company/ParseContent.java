package com.company;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

public class ParseContent {
    public static ArrayList<MonthData> parseContentForMonth(String fileContent) {
        if (!fileContent.isEmpty()) {
            ArrayList<MonthData> monthDataList = new ArrayList<>();
            String[] fileLines = fileContent.split("\n");

            for (int i = 1; i < fileLines.length; i++) {
                String[] lineContent = fileLines[i].split(",");
                monthDataList.add(new MonthData(lineContent[0],
                        Boolean.parseBoolean(lineContent[1]),
                        Integer.parseInt(lineContent[2]),
                        Integer.parseInt(lineContent[3])));
            }

            return monthDataList;
        }
        else {
            return null;
        }
    }

    public static ArrayList<YearlyData> parseContentForYear(String fileContent) {
        if (!fileContent.isEmpty()) {
            ArrayList<YearlyData> yearlyDataList = new ArrayList<>();
            String[] fileLines = fileContent.split("\n");

            for (int i = 1; i < fileLines.length; i++){
                String [] lineContent = fileLines[i].split(",");
                yearlyDataList.add(new YearlyData(Integer.parseInt(lineContent[0]),
                        Integer.parseInt(lineContent[1]),
                        Boolean.parseBoolean(lineContent[2])));
            }

            return yearlyDataList;
        } else {
            return null;
        }
    }
}
