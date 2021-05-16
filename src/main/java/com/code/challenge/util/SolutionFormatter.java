package com.code.challenge.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class SolutionFormatter {
    public String formatSolution(Collection<char[]> solutions, int length) {
        String result;
        if (solutions.isEmpty()) {
            result = "No valid solutions found.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (var solution : solutions) {
                int index = 0;
                while (index + length <= solution.length) {
                    char[] subArray = Arrays.copyOfRange(solution, index, index + length);
                    builder.append(String.valueOf(subArray)).append(System.lineSeparator());
                    index += length;
                }
                builder.append(System.lineSeparator());
            }
            result = builder.toString();
        }
        return result;
    }
}
