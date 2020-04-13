package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameInputValidatorTest {
    private GameInputValidator gameInputValidator;

    @Before
    public void setUp() {
        gameInputValidator = new GameInputValidator(" ", 4, 9);
    }

    @Test
    public void test_convert_to_integer_array() {
        String rawInput = "1 2 3 4";
        int[] expectedArray = {1, 2, 3, 4};
        try {
            int[] result = gameInputValidator.validateAndConvertIntegerArray(rawInput);
            Assert.assertArrayEquals(expectedArray, result);
        } catch (Exception exception) {
            Assert.fail();
        }
    }

    @Test (expected = InvalidInputException.class)
    public void test_validate_numbers_in_range_with_out_range_value() throws InvalidInputException {
        String uniqueGuess = "1 2 10 4";
        gameInputValidator.validateAndConvertIntegerArray(uniqueGuess);
    }

    @Test(expected = InvalidInputException.class)
    public void test_validate_raw_input_format_with_incorrect_format() throws InvalidInputException {
        String rawInput = "1 2";
        gameInputValidator.validateAndConvertIntegerArray(rawInput);
    }

    @Test(expected = InvalidInputException.class)
    public void test_validate_unique_number_in_guess_with_duplicated_guess() throws InvalidInputException {
        String uniqueGuess = "1 2 2 4";
        gameInputValidator.validateAndConvertIntegerArray(uniqueGuess);
    }

}
