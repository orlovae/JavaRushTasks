package com.javarush.task.task32.task3210;

/*
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        byte[] bytes = new byte[text.length()];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");

        raf.seek(number);
        raf.read(bytes, 0, text.length());

        String readBytes = new String(bytes);

        number = raf.length();
        raf.seek(number);

        if (readBytes.equals(text)) {
            raf.write("true".getBytes());
        } else {
            raf.write("false".getBytes());
        }

        raf.close();
    }
}
