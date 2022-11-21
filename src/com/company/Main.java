package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean isRunning = true;
        String baseFilePathForMonth = "src/data/m.20220";
        String baseFilePathForYear = "src/data/y.2022.csv";
        Scanner scanner = new Scanner(System.in);
        MonthReport monthReport = new MonthReport();
        YearReport yearReport = new YearReport();

        System.out.println("/////////////////////////////////////////////////// \n" +
                           "/                  Доступные команды              / \n" +
                           "/ 1. Считать все месячные отчёты.                 / \n" +
                           "/ 2. Считать годовой отчёт.                       / \n" +
                           "/ 3. Сверить отчёты.                              / \n" +
                           "/ 4. Вывести информацию о всех месячных отчётах.  / \n" +
                           "/ 5. Вывести информацию о годовом отчёте.         / \n" +
                           "/ 0. Выход из программы.                          / \n" +
                           "/////////////////////////////////////////////////// \n" );

        while (isRunning) {
            if (scanner.hasNextInt()) {
                int userInput = scanner.nextInt();

                switch (userInput) {
                    case 1 -> {
                        for (int i = 1; i <= 3; i++) {
                            String fileContent = ReadFile.readFileContentOrNull(baseFilePathForMonth + i + ".csv");
                            if (fileContent != null) {
                                ArrayList<MonthData> monthData = ParseContent.parseContentForMonth(fileContent);
                                monthReport.addMonth(i, monthData);
                            } else {
                                System.out.println("Не удалось прочитать файл, повторите попытку и убедитесь что файл существует.");
                            }
                        }
                        System.out.println("Нажмите 6 что бы показать меню.");
                    }
                    case 2 -> {
                        String fileContent = ReadFile.readFileContentOrNull(baseFilePathForYear);
                        if (fileContent != null) {
                            yearReport.setYearReport(ParseContent.parseContentForYear(fileContent));
                        } else {
                            System.out.println("Не удалось прочитать файл, повторите попытку и убедитесь что файл существует.");
                        }
                        System.out.println("Нажмите 6 что бы показать меню.");
                    }
                    case 3 -> {
                        new CompareReports(monthReport.getMonthReport(), yearReport.getYearReport());
                        System.out.println("Нажмите 6 что бы показать меню.");
                    }

                    case 4 -> {
                        if (!monthReport.isMonthReportEmpty()) {
                            monthReport.printReport();
                        } else {
                            System.out.println("Сначала считайте месячные отчёты.");
                        }
                        System.out.println("Нажмите 6 что бы показать меню.");
                    }
                    case 5 -> {
                        if (!yearReport.isYearReportEmpty()) {
                            yearReport.printYearReport();
                        } else {
                            System.out.println("Сначала считайте годовой отчёт.");
                        }
                        System.out.println("Нажмите 6 что бы показать меню.");
                    }
                    case 6 -> System.out.println("/////////////////////////////////////////////////// \n" +
                            "/                  Доступные команды              / \n" +
                            "/ 1. Считать все месячные отчёты.                 / \n" +
                            "/ 2. Считать годовой отчёт.                       / \n" +
                            "/ 3. Сверить отчёты.                              / \n" +
                            "/ 4. Вывести информацию о всех месячных отчётах.  / \n" +
                            "/ 5. Вывести информацию о годовом отчёте.         / \n" +
                            "/ 0. Выход из программы.                          / \n" +
                            "/////////////////////////////////////////////////// \n");
                    case 0 -> {
                        isRunning = false;
                        System.out.println("Завершение работы!");
                    }
                    default -> System.out.println("Этой команды нет.");
                }
            } else {
                System.out.println("Введите допустимую команду.");
                scanner.next();
            }
        }
    }
}
