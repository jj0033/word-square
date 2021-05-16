package com.code.challenge;

import com.code.challenge.argument.ArgumentParser;
import com.code.challenge.argument.ArgumentValidator;
import com.code.challenge.obj.WordSquareInput;
import com.code.challenge.solve.WordSquareSolver;
import com.code.challenge.util.EnglishDictionary;
import com.code.challenge.util.SolutionFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WordSquareRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordSquareRunner.class);

    private final ArgumentParser argumentParser;
    private final ArgumentValidator argumentValidator;
    private final EnglishDictionary dictionary;
    private final WordSquareSolver solver;
    private final SolutionFormatter solutionFormatter;

    public WordSquareRunner(ArgumentParser argumentParser,
                            ArgumentValidator argumentValidator,
                            EnglishDictionary dictionary,
                            WordSquareSolver solver,
                            SolutionFormatter solutionFormatter) {
        this.argumentParser = argumentParser;
        this.argumentValidator = argumentValidator;
        this.dictionary = dictionary;
        this.solver = solver;
        this.solutionFormatter = solutionFormatter;
    }

    @Override
    public void run(String... args) throws Exception {
        WordSquareInput input;
        try {
            input = argumentParser.parseCommandLineArgs(args);
            argumentValidator.validate(input);
        } catch (IllegalArgumentException e) {
            String message = "Invalid input. Correct usage: 2 arguments of an integer (size of square) and a list of " +
                    "letters. The number of letters should equal the square of the size given in the first argument.";
            LOGGER.error(message, e);
            throw e;
        }

        Collection<String> validWords = dictionary.possibleWords(input.getChars(), input.getSize());
        Collection<char[]> solutions = solver.solve(input, validWords);
        String formattedSolution = solutionFormatter.formatSolution(solutions, input.getSize());

        System.out.println(formattedSolution);
    }
}
