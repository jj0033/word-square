package com.code.challenge.argument;

import com.code.challenge.obj.WordSquareInput;
import org.junit.Assert;
import org.junit.Test;

public class ArgumentParserTest {
    private static final int TEST_SIZE = 5;
    private static final String TEST_CHARS = "abc";

    private final ArgumentParser testParser = new ArgumentParser();

    @Test
    public void parseCommandLineArgs_returns_expected_result() {
        WordSquareInput result = testParser.parseCommandLineArgs(TEST_SIZE + "", TEST_CHARS);

        Assert.assertEquals(TEST_SIZE, result.getSize());
        Assert.assertArrayEquals(TEST_CHARS.toCharArray(), result.getChars());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseCommandLineArgs_throws_exception_if_first_argument_is_not_an_int() {
        testParser.parseCommandLineArgs("not an int", TEST_CHARS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseCommandLineArgs_throws_exception_if_incorrect_number_of_arguments_are_passed() {
        testParser.parseCommandLineArgs(TEST_SIZE + "");
    }
}