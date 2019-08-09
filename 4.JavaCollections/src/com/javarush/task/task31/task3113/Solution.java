package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String inputDirectory = scanner.nextLine();

        if (isDirectory(inputDirectory)) {
            SearchFileVisitor sFV = new SearchFileVisitor();
            Files.walkFileTree(Paths.get(inputDirectory), sFV);
            System.out.println("Всего папок - " + sFV.getAllFolders());
            System.out.println("Всего файлов - " + sFV.getAllFiles());
            System.out.println("Общий размер - " + sFV.getSizeAllFiles());

        } else {
            System.out.println(inputDirectory + " - не папка");
            return;
        }

    }

    private static boolean isDirectory(String inputDirectory) {
        return Files.isDirectory(Paths.get(inputDirectory));
    }
}
