package com.example.ben;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FootballTableGeneratorTest {

    private FootballTableGenerator generator;

    @Before
    public void setUp() {
        generator = new FootballTableGenerator();
    }


    @Test
    public void isValidLineTest_emptyLine_false() {

        String line = "";

        boolean isValid = generator.isValidLine(line);

        assertFalse(isValid);
    }

    @Test
    public void isValidLineTest_valid() {

        String line = "Lions 3, Snakes 2";

        boolean isValid = generator.isValidLine(line);

        assertTrue(isValid);
    }

    @Test
    public void isValidLineTest_invalidScore_1() {

        String line = "Lions x, Snakes 2";

        boolean isValid = generator.isValidLine(line);

        assertFalse(isValid);
    }

    @Test
    public void isValidLineTest_invalidScore_2() {

        String line = "Lions 1.2, Snakes 2";

        boolean isValid = generator.isValidLine(line);

        assertFalse(isValid);
    }

    @Test
    public void isValidLineTest_moreThanOneComma() {

        String line = "Lions 1, , Snakes 2";

        boolean isValid = generator.isValidLine(line);

        assertFalse(isValid);
    }

    @Test
    public void isValidLineTest_sameTeamNames_1() {

        String line = "Lions 1, Lions 2";

        boolean isValid = generator.isValidLine(line);

        assertFalse(isValid);
    }

    @Test
    public void isValidLineTest_sameTeamNames_2() {

        String line = "Lions 1, lions 2";

        boolean isValid = generator.isValidLine(line);

        assertFalse(isValid);
    }

    @Test
    public void isNotIntegerTest_false() {

        String integer = "1";

        boolean isValid = ReflectionTestUtils.invokeMethod(generator, "isNotInteger", integer);

        assertFalse(isValid);
    }

    @Test
    public void isNotIntegerTest_true_1() {

        String integer = "x";

        boolean isValid = ReflectionTestUtils.invokeMethod(generator, "isNotInteger", integer);

        assertTrue(isValid);
    }

    @Test
    public void isNotIntegerTest_true_2() {

        String integer = "1.1";

        boolean isValid = ReflectionTestUtils.invokeMethod(generator, "isNotInteger", integer);

        assertTrue(isValid);
    }

}