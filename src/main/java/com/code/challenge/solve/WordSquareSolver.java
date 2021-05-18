package com.code.challenge.solve;

import com.code.challenge.obj.FilledInLetter;
import com.code.challenge.obj.WordCoords;
import com.code.challenge.obj.WordSquareInput;
import com.code.challenge.obj.exception.NoPossibleWordsException;
import com.code.challenge.util.EnglishDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class WordSquareSolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordSquareSolver.class);
    private final EnglishDictionary dictionary;
    private final GridHelper gridHelper;

    public WordSquareSolver(EnglishDictionary dictionary, GridHelper gridHelper) {
        this.dictionary = dictionary;
        this.gridHelper = gridHelper;
    }

    public Collection<char[]> solve(WordSquareInput input) {
        int wordSize = input.getSize();
        int solutionArraySize = wordSize * wordSize;
        List<WordCoords> wordCoords = gridHelper.getWordCoords(wordSize);
        char[] grid = new char[solutionArraySize];
        Collection<char[]> solutions = new ArrayList<>();
        try {
            fill(grid, input.getChars(), input.getChars(), wordCoords, 0, input.getSize(), solutions);
        } catch (NoPossibleWordsException g) {
            LOGGER.info("No possible words found for first row/column.");
        }
        return solutions;
    }

    private void fill(char[] grid, char[] originalLetters, char[] letters, List<WordCoords> coordsList, int level, int wordSize, Collection<char[]> solutions)
            throws NoPossibleWordsException {
        WordCoords coords = coordsList.get(level);
        // Get information on places in this word's position on the grid that are already filled from previous
        // rows/columns.
        Collection<FilledInLetter> filledIn = gridHelper.getFilledInLetters(grid, coords);
        // All possible words that can be placed in this row/column using the remaining letters, accounting for letters
        // already filled in.
        Collection<String> possibleWords = dictionary.possibleWords(letters, wordSize, coords.getJoinIndex(), filledIn);
        if (possibleWords.isEmpty()) {
            String message = String.format("No valid words can be formed to fill in row/column %d with the remaining " +
                            "letters.",
                    level + 1);
            // If no possible words available throw exception If this happens for row/column 1 no words are possible
            // from the provided letters so end immediately. For subsequent rows/columns, jump back up to the previous
            // row/column and try another one of that row/column's possible words.
            throw new NoPossibleWordsException(message);
        }
        for (var word : possibleWords) {
            char[] newGrid = Arrays.copyOf(grid, grid.length);
            // Fill the word into the grid.
            fillInWord(newGrid, coords, word);
            if (level == wordSize - 1) {
                // If this is the final row/column and no 0s remain in the grid this is a solution - add to the solution
                // list.
                if (new String(newGrid).chars().noneMatch(c -> c == 0)) {
                    solutions.add(newGrid);
                }
            } else {
                // Reevaluate the remaining available letters after the latest word has been placed in the grid.
                char[] remaining = gridHelper.getRemainingChars(originalLetters, newGrid);
                try {
                    // Recursively attempt to fill the next row/column.
                    fill(newGrid, originalLetters, remaining, coordsList, level + 1, wordSize, solutions);
                } catch (NoPossibleWordsException e) {
                    LOGGER.debug("Using word {} in row/column {} left no possible words in the next row/column. " +
                                    "Attempting next word.",
                            word,
                            level + 1);
                }
            }
        }
    }

    private static void fillInWord(char[] grid, WordCoords coords, String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            grid[coords.getHorizontal().get(i)] = c;
            grid[coords.getVertical().get(i)] = c;
        }
    }
}
