package com.code.challenge.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SolutionFormatterTest {
    private static final String EMPTY_SOLUTION_MESSAGE = "No valid solutions found.";
    private static final int TEST_LENGTH = 2;
    private static final String TEST_SOLUTION_1_ROW_1 = "ab";
    private static final String TEST_SOLUTION_1_ROW_2 = "cd";
    private static final String TEST_SOLUTION_2_ROW_1 = "da";
    private static final String TEST_SOLUTION_2_ROW_2 = "bc";
    private Collection<char[]> testSolutions;

    private SolutionFormatter testFormatter;

    @Before
    public void setUp() {
        Collection<String> testSolutionsStrings = Arrays.asList(
                TEST_SOLUTION_1_ROW_1.concat(TEST_SOLUTION_1_ROW_2),
                TEST_SOLUTION_2_ROW_1.concat(TEST_SOLUTION_2_ROW_2));
        testSolutions = new ArrayList<>();
        testSolutionsStrings.forEach(string -> testSolutions.add(string.toCharArray()));
        testFormatter = new SolutionFormatter();
    }

    @Test
    public void formatSolution_method_outputs_string_in_expected_format() {
        String expected = TEST_SOLUTION_1_ROW_1 + System.lineSeparator()
                + TEST_SOLUTION_1_ROW_2 + System.lineSeparator() + System.lineSeparator()
                + TEST_SOLUTION_2_ROW_1 + System.lineSeparator()
                + TEST_SOLUTION_2_ROW_2 + System.lineSeparator() + System.lineSeparator();

        String result = testFormatter.formatSolution(testSolutions, TEST_LENGTH);

        assertEquals(expected, result);
    }

    @Test
    public void formatSolution_method_outputs_expected_string_if_solutions_are_empty() {
        String result = testFormatter.formatSolution(Collections.emptyList(), TEST_LENGTH);

        assertEquals(EMPTY_SOLUTION_MESSAGE, result);
    }
}