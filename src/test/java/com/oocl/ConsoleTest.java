package com.oocl;

import com.oocl.io.Console;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ConsoleTest {
    @Test
    public void test_readInputFromConsole() {
        String expected = "1 2 3 4";
        InputStream inputStream = new ByteArrayInputStream(expected.getBytes());
        System.setIn(inputStream);

        com.oocl.io.Console console = new com.oocl.io.Console();
        String actualResult = console.readInputFromConsole();

        Assert.assertEquals(expected, actualResult);
    }

    @Test
    public void test_displayResultToConsole() {
        String expected = "1A1B";
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        com.oocl.io.Console console = new Console();
        console.displayResultToConsole(expected);
        Assert.assertEquals(expected + "\r\n", outputStream.toString());
    }
}