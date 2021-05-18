package com.code.challenge.solve;

import com.code.challenge.obj.FilledInLetter;
import com.code.challenge.obj.WordCoords;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper methods for solving word squares.
 */
@Component
public class GridHelper {
    /**
     * Return the indexes of all words in a word square, where the word square is represented by a one dimensional array
     * of chars, given a word length.
     *
     * @param wordSize the length of each word in the square.
     *
     * @return the indexes of each word in the square.
     */
    public List<WordCoords> getWordCoords(int wordSize) {
        List<WordCoords> result = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < wordSize; i++) {
            int end = start + wordSize;
            List<Integer> horizontal = new ArrayList<>();
            List<Integer> vertical = new ArrayList<>();
            int verticalCoord = i;
            for (int j = start; j < end; j++) {
                horizontal.add(j);
                vertical.add(verticalCoord);
                verticalCoord += wordSize;
            }
            result.add(new WordCoords(horizontal, vertical, i));
            start += wordSize;
        }
        return result;
    }

    /**
     * Given a char array and indexes on that array, return any indexes that are not "empty" (i.e. 0 value) along with
     * the value that index contains.
     *
     * @param grid   the character array.
     * @param coords contains indexes for that array.
     *
     * @return filled letter list.
     */
    public Collection<FilledInLetter> getFilledInLetters(char[] grid, WordCoords coords) {
        Collection<FilledInLetter> result = new ArrayList<>();
        List<Integer> horizontalCoords = coords.getHorizontal();
        for (int i = 0; i < horizontalCoords.size(); i++) {
            char letter = grid[horizontalCoords.get(i)];
            if (letter != 0) {
                result.add(new FilledInLetter(i, letter));
            }
        }
        return result;
    }

    /**
     * Given an original char array and another array containing used values, remove the used values from the original
     * array and return a new array with those values removed.
     *
     * @param original the original array.
     * @param used     the used values.
     *
     * @return the remaining values in an array.
     */
    public char[] getRemainingChars(char[] original, char[] used) {
        List<Character> charList = new String(original).chars().mapToObj(i -> (char) i).collect(Collectors.toList());
        for(var c : used) {
            int index = charList.indexOf(c);
            if (index != -1) {
                charList.remove(index);
            }
        }
        return charList.stream().map(String::valueOf).collect(Collectors.joining()).toCharArray();
    }
}
