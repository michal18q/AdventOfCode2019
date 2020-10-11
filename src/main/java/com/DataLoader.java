package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Long.parseLong;

public class DataLoader {

    public static HashMap<Long, Long> loadDataFromFile(String fileName) {

        HashMap<Long, Long> memory = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String[] program = reader.readLine().split(",");
            for (int i = 0; i < program.length; i++) {
                memory.put((long)i, parseLong(program[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memory;
    }
}