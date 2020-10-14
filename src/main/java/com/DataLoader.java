package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Long.parseLong;

public class DataLoader {

    public static HashMap<Long, Long> loadComputerMemoryFromFile(String fileName) {

        HashMap<Long, Long> memory = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String[] program = reader.readLine().split(",");
            for (int memoryIndex = 0; memoryIndex < program.length; memoryIndex++) {
                long memoryValue = parseLong(program[memoryIndex]);
                memory.put((long) memoryIndex, memoryValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memory;
    }
}