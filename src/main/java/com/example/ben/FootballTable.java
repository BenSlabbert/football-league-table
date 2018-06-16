package com.example.ben;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FootballTable {

    public static void main(String[] args) {
        System.out.println("Please add results for the league. Type 'q' to exit.");
        System.out.println("Type 'q' to exit.");
        System.out.println("Type 't' to see table.");

        String userInput;
        List<String> scoreList = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        FootballTableGenerator ft = new FootballTableGenerator();

        userInput = input.nextLine().replaceAll("  ", " ");

        while (!"q".equals(userInput) && !"t".equals(userInput)) {

            if (!ft.isValidLine(userInput)) System.out.println("invalid score: " + userInput);
            else scoreList.add(userInput);

            userInput = input.nextLine().replaceAll("  ", " ");
        }

        if ("t".equals(userInput) && !scoreList.isEmpty()) ft.createTableAndPrint(scoreList);

        System.out.println("Done");
    }
}