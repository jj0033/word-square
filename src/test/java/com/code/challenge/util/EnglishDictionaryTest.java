package com.code.challenge.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

public class EnglishDictionaryTest {
    private static final int TEST_WORD_LENGTH = 4;
    private static final char[] TEST_CHARS = {'a', 'a', 'c', 'c', 'd', 'e', 'e', 'e', 'e', 'm', 'm', 'n', 'n', 'n', 'o', 'o'};
    private EnglishDictionary testDictionary;

    @Before
    public void setUp() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Collection<String> allowedWords = Arrays.asList("mean", "medd", "jase", "maacc", "maac");
        testDictionary = generateTestInstance(allowedWords);
    }

    @Test
    public void test_dictionary_instance_returns_expected_values() {
        Collection<String> list = testDictionary.possibleWords(TEST_CHARS, TEST_WORD_LENGTH);

        // Expect 2 words - "mean" and "maac"
        // "medd" -> only 1 d in char list
        // "jase" -> no j or s
        // "maacc" -> enough correct letters in char array however word is incorrect length
        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.contains("mean"));
        Assert.assertTrue(list.contains("maac"));
    }

    private EnglishDictionary generateTestInstance(Collection<String> words) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<EnglishDictionary> constructor = (Constructor<EnglishDictionary>) EnglishDictionary.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        return constructor.newInstance(words);
    }
}