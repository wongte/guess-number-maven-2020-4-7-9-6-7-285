package com.oocl;

public class InvalidInputException extends Exception {

    public static final String INVALID_INPUT_MESSAGE = "Wrong Input，Input again";

    public InvalidInputException() {
        super(INVALID_INPUT_MESSAGE);
    }
}
