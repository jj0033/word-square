package com.code.challenge.obj;

import java.util.Collections;
import java.util.List;

/**
 * Wrapper class representing a word on the word square, with its coordinates in a one-dimensional array representation
 * of that square.
 */
public class WordCoords {
    private final List<Integer> horizontal;
    private final List<Integer> vertical;
    private final int joinIndex;

    /**
     * Constructor.
     *
     * @param horizontal the indexes of the letters in the horizontal word.
     * @param vertical   the indexes of the letters in the vertical word.
     * @param joinIndex  the index within the word (NOT the grid) where the horizontal and vertical words meet - i.e.
     *                   for the word in row/column one this will be 0, for column 2 this will be 1, etc.
     */
    public WordCoords(List<Integer> horizontal, List<Integer> vertical, int joinIndex) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.joinIndex = joinIndex;
    }

    public List<Integer> getHorizontal() {
        return Collections.unmodifiableList(horizontal);
    }

    public List<Integer> getVertical() {
        return Collections.unmodifiableList(vertical);
    }

    public int getJoinIndex() {
        return joinIndex;
    }
}
