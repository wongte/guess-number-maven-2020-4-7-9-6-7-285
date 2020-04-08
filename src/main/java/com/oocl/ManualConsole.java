package com.oocl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ManualConsole implements GameIO {
    Queue<String> input = new LinkedList<>();
    List<String> output = new LinkedList<>();

    public List<String> getOutput() {
        return output;
    }

    public void addInputLine(String line) {
        input.add(line);
    }

    @Override
    public String readInputFromConsole() {
        return input.poll();
    }

    @Override
    public void displayResultToConsole(String result) {
        output.add(result);
    }
}
