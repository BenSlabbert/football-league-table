package com.example.ben;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class FootballTableTest {


    private FootballTable footballTable;

    @Before
    public void setUp() {
        footballTable = new FootballTable();

    }

    @Test
    public void testMainRuns() {
        ByteArrayInputStream in = new ByteArrayInputStream(("Lions 3, Snakes 3\n" +
                "Tarantulas 1, FC Awesome 0\n" +
                "Lions 1, FC Awesome 1\n" +
                "Tarantulas 3, Snakes 1\n" +
                "Lions 4, Grouches 0\n").getBytes());
        System.setIn(in);

        footballTable.main(new String[]{});
    }

    @Test
    public void testMain_1() {
        footballTable.main(new String[]{"src/test/resources/test-1.csv"});
    }

    @Test
    public void testMain_2() {
        footballTable.main(new String[]{"src/test/resources/test-2.csv"});
    }

    @Test
    public void readFileTest_1() {

        String path = "src/test/resources/test-1.csv";
        List<String> fileLines = ReflectionTestUtils.invokeMethod(footballTable, "readFile", path);

        assertNotNull(fileLines);
        assertFalse(fileLines.isEmpty());
        assertEquals(5, fileLines.size());
    }

    @Test
    public void readFileTest_2() {

        String path = "src/test/resources/test-2.csv";
        List<String> fileLines = ReflectionTestUtils.invokeMethod(footballTable, "readFile", path);

        assertNotNull(fileLines);
        assertFalse(fileLines.isEmpty());
        assertEquals(7, fileLines.size());
    }

    @Test
    public void handleFileTest_fileNotFound(){

        new MockUp<PrintStream>(){
          @Mock
          public void println(String x) {
              assertEquals("File not found!", x);
          }
        };

        ReflectionTestUtils.invokeMethod(footballTable, "handleFile", "");
    }

    @Test
    public void handleFileTest_invalidFile(){

        new MockUp<PrintStream>(){
          @Mock
          public void println(String x) {
              assertEquals("Invalid file!", x);
          }
        };

        ReflectionTestUtils.invokeMethod(footballTable, "handleFile", "src/test/resources/invalid-file.csv");
    }
}
