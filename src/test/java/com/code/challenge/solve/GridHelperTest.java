package com.code.challenge.solve;

import com.code.challenge.obj.FilledInLetter;
import com.code.challenge.obj.WordCoords;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GridHelperTest {
    private static final char[] TEST_ORIGINAL_LETTERS = new char[]{'a', 'b', 'c'};
    private static final char[] TEST_USED_LETTERS = new char[]{0, 'b', 0};
    private static final char[] TEST_PARTIAL_GRID = new char[]{'a', 'b', 0, 0, 'b', 0, 0, 0};
    private static final List<Integer> HORIZONTAL_INDEXES_4_LETTER_ROW_2 = Arrays.asList(4, 5, 6, 7);
    private static final List<Integer> VERTICAL_INDEXES_4_LETTER_COLUMN_2 = Arrays.asList(1, 5, 9, 13);
    private static final int JOIN_INDEX_WORD_2 = 1;


    private final GridHelper testHelper = new GridHelper();

    @Mock
    private WordCoords mockCoords;

    @Before
    public void setUp() {
        when(mockCoords.getHorizontal()).thenReturn(HORIZONTAL_INDEXES_4_LETTER_ROW_2);
    }

    @Test
    public void getRemainingChars_method_removes_used_letters_from_grid() {
        char[] result = testHelper.getRemainingChars(TEST_ORIGINAL_LETTERS, TEST_USED_LETTERS);

        assertArrayEquals(new char[]{'a', 'c'}, result);
    }

    @Test
    public void getFilledInLetters_method_gets_expected_filled_in_letters() {
        Collection<FilledInLetter> result = testHelper.getFilledInLetters(TEST_PARTIAL_GRID, mockCoords);

        assertEquals(1, result.size());
        FilledInLetter filled = result.stream().findAny().get();
        assertEquals(0, filled.getIndex());
        assertEquals('b', filled.getLetter());
    }

    @Test
    public void getWordCoords_method_returns_word_index_information() {
        List<WordCoords> result = testHelper.getWordCoords(4);

        assertEquals(4, result.size());
        WordCoords secondWord = result.get(1);
        assertEquals(secondWord.getHorizontal(), HORIZONTAL_INDEXES_4_LETTER_ROW_2);
        assertEquals(secondWord.getVertical(), VERTICAL_INDEXES_4_LETTER_COLUMN_2);
        assertEquals(secondWord.getJoinIndex(), JOIN_INDEX_WORD_2);
    }
}