package com.code.challenge;

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

    public Collection<String> possibleWords(char[] chars, int length) {
        return words.stream()
                .filter(word -> word.length() == length)
                .filter(word -> wordCanBeSpelt(word, chars))
                .collect(Collectors.toList());
    }

    private static boolean wordCanBeSpelt(String word, char[] chars) {
        List<Character> charList = new String(chars).chars().mapToObj(i -> (char) i).collect(Collectors.toList());
        for (char c : word.toCharArray()) {
            int index = charList.indexOf(c);
            if (index != -1) {
                charList.remove(index);
            } else {
                return false;
            }
        }
        return true;
    }
}
