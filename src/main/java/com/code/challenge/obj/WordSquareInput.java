package com.code.challenge.obj;

public class WordSquareInput {
    private final int size;
    private final char[] chars;

    public WordSquareInput(int size, char[] chars) {
        this.size = size;
        this.chars = chars;
    }

    public int getSize() {
        return size;
    }

    public char[] getChars() {
        return chars;
    }
}
