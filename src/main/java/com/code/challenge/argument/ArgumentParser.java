package com.code.challenge.argument;

import com.code.challenge.obj.WordSquareInput;
import org.springframework.stereotype.Component;

@Component
public class ArgumentParser {
    public WordSquareInput parseCommandLineArgs(String... args) {
        int size;
        char[] chars;
        if (args.length != 2) {
            throw new IllegalArgumentException("Input should contain exactly two arguments");
        }
        try {
            size = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Argument one should be an integer.", e);
        }
        chars = args[1].toCharArray();

        return new WordSquareInput(size, chars);
    }
}
