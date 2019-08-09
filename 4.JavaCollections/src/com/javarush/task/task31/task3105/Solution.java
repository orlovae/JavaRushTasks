package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, ByteArrayOutputStream> zipEntryMap = new TreeMap<>();

        Path fileName = Paths.get(args[0]);
        Path newFile = Paths.get("new" +
                File.separator + fileName.getFileName().toString());
        Path zip = Paths.get(args[1]);


        FileInputStream fis = new FileInputStream(args[1]);
        ZipInputStream zipInputStream = new ZipInputStream(fis);

        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (!entry.getName().equals(newFile.toString())) {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipInputStream.read(buffer)) != -1)
                    byteArrayOutputStream.write(buffer, 0, count);

                zipEntryMap.put(entry.getName(), byteArrayOutputStream);
                zipInputStream.closeEntry();
            }
        }
        zipInputStream.close();

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(args[1]));

        zipOutputStream.putNextEntry(new ZipEntry(newFile.normalize().toString()));
        Files.copy(fileName, zipOutputStream);

        for (Map.Entry item:zipEntryMap.entrySet()
             ) {
            ZipEntry zipEntry = new ZipEntry(item.getKey().toString());
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write((item.getValue().toString().getBytes()));
            zipOutputStream.closeEntry();
        }

        zipOutputStream.close();
    }
}
