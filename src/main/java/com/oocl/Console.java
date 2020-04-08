package com.oocl;

import java.util.Scanner;

public class Console {
    public String readInputFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayResultToConsole(String result) {
        System.out.println(result);
    }
}
