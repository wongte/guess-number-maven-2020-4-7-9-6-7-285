package com.oocl;

import java.util.Scanner;

public class Console implements GameIO {
    @Override
    public String readInputFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void displayResultToConsole(String result) {
        System.out.println(result);
    }
}
