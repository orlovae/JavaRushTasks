package com.javarush.task.task31.task3112;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("/home/alex/Загрузки"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException, URISyntaxException {

        String fileName = Paths.get(new URI(urlString).getPath()).getFileName().toString();
        Path moveTo = Paths.get(downloadDirectory.toString() + File.separator + fileName);

        URL url = new URL(urlString);

        Path tempFile = Files.createTempFile(null, null);

        InputStream inputStream = url.openStream();


        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        Path file = Files.move(tempFile, moveTo, StandardCopyOption.REPLACE_EXISTING);

        inputStream.close();


        return file;

    }
}
