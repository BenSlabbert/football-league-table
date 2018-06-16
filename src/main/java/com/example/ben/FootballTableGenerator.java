package com.example.ben;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FootballTableGenerator {

    private List<TeamResult> getSortedTeamResults(Map<String, Integer> table) {
        List<TeamResult> teamResults = new ArrayList<>();
        for (Map.Entry<String, Integer> key : table.entrySet())
            teamResults.add(new TeamResult(key.getKey(), key.getValue() == null ? 0 : key.getValue()));

        sortResults(teamResults);

        return teamResults;
    }

    private TeamResult getTeam(String string) {
        string = string.trim();
        int beginIndex = string.lastIndexOf(" ");
        String teamName = string.substring(0, beginIndex);
        String score = string.substring(beginIndex + 1);

        Integer homeTeamScore = Integer.valueOf(score);

        return new TeamResult(teamName, homeTeamScore);
    }

    private void sortResults(List<TeamResult> teamResults) {
        teamResults.sort((o1, o2) -> {

            int c;
            c = o2.getScore().compareTo(o1.getScore());

            if (c == 0) c = o1.getName().compareTo(o2.getName());

            return c;
        });
    }

    private void printTable(List<TeamResult> teamResults) {
        int pos = 1;
        for (TeamResult tr : teamResults) {
            System.out.println(pos++ + ". " + tr.getName() + " " + tr.getScore() + " pts");
        }
    }

    void createTableAndPrint(List<String> scoreList) {

        Map<String, Integer> table = new HashMap<>();

        for (String scoreRecord : scoreList) {
            TeamResult homeTeamResult = getTeam(scoreRecord.split(",")[0].trim());
            TeamResult awayTeamResult = getTeam(scoreRecord.split(",")[1].trim());

            if (!table.containsKey(homeTeamResult.getName())) table.put(homeTeamResult.getName(), 0);
            if (!table.containsKey(awayTeamResult.getName())) table.put(awayTeamResult.getName(), 0);

            updatePoints(table, homeTeamResult, awayTeamResult);
        }

        List<TeamResult> teamResults = getSortedTeamResults(table);

        printTable(teamResults);
    }

    private void updatePoints(Map<String, Integer> table, TeamResult homeTeamResult, TeamResult awayTeamResult) {
        if (homeTeamResult.getScore() > awayTeamResult.getScore()) {
            Integer score = table.get(homeTeamResult.getName()) + 3;
            table.put(homeTeamResult.getName(), score);
        } else if (awayTeamResult.getScore() > homeTeamResult.getScore()) {
            Integer score = table.get(awayTeamResult.getName()) + 3;
            table.put(awayTeamResult.getName(), score);
        } else {
            Integer score = table.get(homeTeamResult.getName());
            table.put(homeTeamResult.getName(), ++score);

            score = table.get(awayTeamResult.getName());
            table.put(awayTeamResult.getName(), ++score);
        }
    }

    boolean validateLines(List<String> lines) {

        for (String line : lines) {
            if (!isValidLine(line)) {
                return false;
            }
        }
        return true;
    }

    boolean isValidLine(String line) {

        String[] split = line.trim().split(",");

        if (split.length == 2) return validateTeamScores(split);

        return false;
    }

    private boolean validateTeamScores(String... split) {

        for (String teamScore : split) {
            teamScore = teamScore.trim();
            int beginIndex = teamScore.lastIndexOf(" ");
            String teamName = teamScore.substring(0, beginIndex);
            String score = teamScore.substring(beginIndex + 1);

            if (teamName.length() < 1 || isNotInteger(score)) return false;
        }

        String homeTeam = split[0].trim().split(" ")[0];
        String awayTeam = split[1].trim().split(" ")[0];

        return !homeTeam.equalsIgnoreCase(awayTeam);
    }

    private boolean isNotInteger(String string) {

        try {
            int i = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return true;
        }

        return false;
    }
}
