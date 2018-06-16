package com.example.ben;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class FootballTable {

    public static void main(String[] args) {

        FootballTableGenerator generator = new FootballTableGenerator();

        if (args.length > 1) out.println("Too many arguments provided!");
        else if (args.length == 1 && !args[0].isEmpty()) handleFile(args[0], generator);
        else handleCommandLine(generator);
    }

    private static void handleCommandLine(FootballTableGenerator generator) {
        out.println("Please add results for the league. Type 'q' to exit.");
        out.println("Type 'q' to exit.");
        out.println("Type 't' to see table.");

        String userInput;
        List<String> scoreList = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        userInput = input.nextLine().replaceAll("  ", " ");

        while (!"q".equals(userInput) && !"t".equals(userInput)) {

            if (!generator.isValidLine(userInput)) out.println("invalid score: " + userInput);
            else scoreList.add(userInput);

            if (input.hasNextLine())
                userInput = input.nextLine().replaceAll("  ", " ");
            else userInput = "q";
        }

        if ("t".equals(userInput) && !scoreList.isEmpty()) generator.createTableAndPrint(scoreList);

        out.println("Done");
    }

    private static void handleFile(String path, FootballTableGenerator generator) {
        // try to read from file
        out.println("Path to file: " + path);

        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            boolean b = generator.validateLines(strings);

            if (!b) out.println("Invalid line in file!");

            generator.createTableAndPrint(strings);
        } catch (IOException e) {
            out.println("File not found!");
        }
    }
}