package com.example.ben;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class FootballTableTest {


    private FootballTable footballTable;

    @Before
    public void setUp() {
        footballTable = new FootballTable();

    }

    @Test
    public void testMainRuns(){
        ByteArrayInputStream in = new ByteArrayInputStream("My string\nhello".getBytes());
        System.setIn(in);

        footballTable.main(new String[]{"arg1", "arg2"});
    }
}
