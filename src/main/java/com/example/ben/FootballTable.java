package com.example.ben;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class FootballTable {

    public static void main(String[] args) {

        if (args.length > 1) out.println("Too many arguments provided!");
        else if (args.length == 1 && !args[0].isEmpty()) handleFile(args[0]);
        else handleCommandLine();
    }

    private static void handleCommandLine() {
        printInstructions();

        FootballTableGenerator generator = new FootballTableGenerator();
        List<String> scoreList = new ArrayList<>();
        String userInput;

        Scanner input = new Scanner(System.in);

        userInput = input.nextLine().replaceAll("  ", " ");

        while (!"q".equals(userInput) && !"t".equals(userInput)) {

            if (!generator.isValidLine(userInput)) out.println("Invalid score: " + userInput);
            else scoreList.add(userInput);

            if (input.hasNextLine())
                userInput = input.nextLine().replaceAll("  ", " ");
            else userInput = "t";
        }

        if (scoreList.isEmpty()) out.println("No results provided!");
        else if ("t".equals(userInput)) generator.createTableAndPrint(scoreList);

        out.println("Done");
    }

    private static void printInstructions() {
        out.println("Please add results for the league. Type 'q' to exit.");
        out.println("Type 'q' to exit.");
        out.println("Type 't' to see table.");
    }

    private static void handleFile(String path) {
        FootballTableGenerator generator = new FootballTableGenerator();

        try {
            List<String> strings = readFile(path);

            if (!generator.isValidFile(strings)) out.println("Invalid file!");
            else generator.createTableAndPrint(strings);
        } catch (IOException e) {
            out.println("File not found!");
        }
    }

    private static List<String> readFile(String path) throws IOException {
        List<String> strings = Files.readAllLines(Paths.get(path));
        return strings.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}