package com.code.challenge.argument;

import com.code.challenge.obj.WordSquareInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentValidatorTest {
    private static final int TEST_SIZE = 2;
    private static final char[] TEST_CHARS = {'a', 'b', 'c', 'd'};

    private final ArgumentValidator testValidator = new ArgumentValidator();

    @Mock
    private WordSquareInput mockInput;

    @Before
    public void setUp() {
        when(mockInput.getSize()).thenReturn(TEST_SIZE);
        when(mockInput.getChars()).thenReturn(TEST_CHARS);
    }

    @Test
    public void validate_method_does_not_throw_when_input_is_valid() {
        testValidator.validate(mockInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_method_throws_exception_when_char_array_is_invalid_size() {
        when(mockInput.getChars()).thenReturn(new char[]{'a', 'b', 'c'});

        testValidator.validate(mockInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_method_throws_exception_when_char_array_has_invalid_character() {
        when(mockInput.getChars()).thenReturn(new char[]{'a', 'b', 'c', ' '});

        testValidator.validate(mockInput);
    }
}