package com.code.challenge.argument;

import com.code.challenge.obj.WordSquareInput;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArgumentValidator {
    public void validate(WordSquareInput input) {
        List<String> validationErrors = new ArrayList<>();
        int length = input.getSize();
        int numberChars = input.getChars().length;
        if (numberChars != length * length) {
            validationErrors.add("Number of letters must equal the square of the provided length.");
        }
        for (char c : input.getChars()) {
            if (!Character.isAlphabetic(c)) {
                validationErrors.add("Provided characters must all be letters.");
                break;
            }
        }
        if (!validationErrors.isEmpty()) {
            StringBuilder exceptionMessageBuilder = new StringBuilder();
            validationErrors.forEach(error -> exceptionMessageBuilder.append(error).append(". "));
            throw new IllegalArgumentException(exceptionMessageBuilder.toString());
        }
    }
}
