package com.example.ben;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeamResultTest {

    private TeamResult result;

    @Before
    public void setUp() {
        result = new TeamResult();
    }

    @Test
    public void toStringTest() {

        result = new TeamResult("team 1", 23);

        String s = result.toString();

        assertEquals("TeamResult(name=team 1, score=23)", s);
    }

    @Test
    public void setterGetterTest() {
        result.setName("setting name");
        result.setScore(1);

        assertEquals("setting name", result.getName());
        assertEquals(1, result.getScore().intValue());
    }
}
