package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class DataLoader {

    public static int[] loadDataFromFile(String fileName) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String[] program = reader.readLine().split(",");
            int[] memory = new int[program.length];
            for (int i = 0; i < program.length; i++) {
                memory[i] = parseInt(program[i]);
            }
            return memory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[0];
    }
}