package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ConsoleTest {
    @Test
    public void test_readInputFromConsole() {
        String expected = "1 2 3 4";
        InputStream inputStream = new ByteArrayInputStream(expected.getBytes());
        System.setIn(inputStream);

        Console console = new Console();
        String actualResult = console.readInputFromConsole();

        Assert.assertEquals(expected, actualResult);
    }
}