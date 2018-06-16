# Football Table Generator

This program the football results in the following format:

Lions 3, Snakes 3

Tarantulas 1, FC Awesome 0

Lions 1, FC Awesome 1

Tarantulas 3, Snakes 1

Lions 4, Grouches 0

as either input with the command line or you can pass in a path to a file with the records as a csv.

## To build

* Use the provided Maven wrapper:

`./mvnw clean package`

### To execute with the command line interface:

`java -jar target/footbal-table.jar`

### Or provide a file:

`java -jar target/football-table.jar src/test/resources/test-1.csv`

## To Test

`./mvnw clean test`

## Requirements

* Java 8 JKD to compile and run
