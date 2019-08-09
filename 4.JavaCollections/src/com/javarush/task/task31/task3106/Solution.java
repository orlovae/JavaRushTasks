package com.javarush.task.task31.task3106;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(args[0]);

        Set<String> stringSet = new TreeSet<>();
        for (int i = 1; i <= args.length - 1; i++) {
            stringSet.add(args[i]);
        }

        List<FileInputStream> streamList = new ArrayList<>();

        for (String item:stringSet
             ) {
            streamList.add(new FileInputStream(item));
        }

        SequenceInputStream inputStream = new SequenceInputStream(Collections.enumeration(streamList));

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        System.out.println(zipInputStream.getNextEntry());

        byte[] buffer = new byte[1024 * 1024];//1MB buffer
        int byteBuffer;
        while ((byteBuffer = zipInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, byteBuffer);
        }

        fileOutputStream.close();
        zipInputStream.close();
    }
}
