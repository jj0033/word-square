package com.code.challenge.util;

import com.code.challenge.obj.FilledInLetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnglishDictionary {
    private final Collection<String> words;

    private EnglishDictionary(Collection<String> words) {
        this.words = words;
    }

    @Autowired
    public EnglishDictionary(@Value("${com.code.challenge.dictionary-file}") String dictionaryFile) throws IOException {
        this(Files.readAllLines(new File(dictionaryFile).toPath()));
    }

    public Collection<String> possibleWords(char[] chars, int length, int singleLetterNeededIndex, Collection<FilledInLetter> filledLetters) {
        return words.stream()
                .filter(word -> word.length() == length)
                .filter(word -> wordCanBeSpelt(word, chars, singleLetterNeededIndex, filledLetters))
                .collect(Collectors.toList());
    }

    private static boolean wordCanBeSpelt(String word, char[] chars, int singleLetterIndex, Collection<FilledInLetter> filledLetters) {
        List<Character> charList = new String(chars).chars().mapToObj(i -> (char) i).collect(Collectors.toList());
        outer:
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            for (var filled : filledLetters) {
                if (filled.getIndex() == i) {
                    // If the place where the letter will fit is filled, and that place is filled with a letter that
                    // is not the required letter in the word, the word cannot be spelt.
                    if (filled.getLetter() != letter) {
                        return false;
                    } else {
                        // If the place where the letter will fit is filled, and that place is filled the with the
                        // required letter in the word, no need to check if that letter is available in the available
                        // char list.
                        continue outer;
                    }
                }
            }
            int index = charList.indexOf(letter);
            if (index != -1) {
                // If the required letter is available in the char list, remove it.
                charList.remove(index);
                int secondCharIndex = charList.indexOf(letter);
                // Need to remove a duplicate of the letter as well (since words are spelt both horizontally and
                // vertically). However, if this letter is in the position where the row and column meet, this duplicate
                // is not required (only one instance of the letter is needed).
                if (secondCharIndex != -1 && i != singleLetterIndex) {
                    charList.remove(index);
                }
                else if (i != singleLetterIndex){
                    return false;
                }
            } else {
                // If the required letter is not available the word cannot be spelt.
                return false;
            }
        }
        return true;
    }
}
