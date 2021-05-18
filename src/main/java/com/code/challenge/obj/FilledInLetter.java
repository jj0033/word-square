package com.code.challenge.obj;

/**
 * Wrapper class representing letters on a grid already filled in for a partially completed word on the grid.
 */
public class FilledInLetter {
    private final int index;
    private final char letter;

    /**
     * Constructor.
     *
     * @param index  the index within the word that is filled.
     * @param letter the character contained in that index.
     */
    public FilledInLetter(int index, char letter) {
        this.index = index;
        this.letter = letter;
    }

    public int getIndex() {
        return index;
    }

    public char getLetter() {
        return letter;
    }
}
