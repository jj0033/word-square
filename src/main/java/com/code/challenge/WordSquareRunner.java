package com.code.challenge;

import com.code.challenge.obj.WordSquareInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class WordSquareRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordSquareRunner.class);

    private final ArgumentParser argumentParser;
    private final ArgumentValidator argumentValidator;
    @Value("${com.code.challenge.file}")
    private String g;

    public WordSquareRunner(ArgumentParser argumentParser, ArgumentValidator argumentValidator) {
        this.argumentParser = argumentParser;
        this.argumentValidator = argumentValidator;
    }

    @Override
    public void run(String... args) throws Exception {
        WordSquareInput input;
        try {
            input = argumentParser.parseCommandLineArgs(args);
            argumentValidator.validate(input);
        } catch (IllegalArgumentException e) {
            String message = "Invalid input. Correct usage: 2 arguments of an integer (size of square) and a list of " +
                    "letters. The number of letters should equal the square of the size given in the first argument." + g;
            LOGGER.error(message, e);
            throw  e;
        }
    }
}
