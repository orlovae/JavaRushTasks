package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> absolutPathFileList = new ArrayList<>();

        File file = new File(root);
        File[] foldersAndFiles = file.listFiles();
        Queue<File> fileQueue = new PriorityQueue<>();
        Collections.addAll(fileQueue, foldersAndFiles);

        while (!fileQueue.isEmpty()){
            File firstFile = fileQueue.remove();
            if (firstFile.isDirectory()) {
                Collections.addAll(fileQueue, firstFile.listFiles());
            } else {
                absolutPathFileList.add(firstFile.getAbsolutePath());
            }
        }

        return absolutPathFileList;

    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try {
            list = getFileTree(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String item:list
             ) {
            System.out.println(item);
        }
    }
}
