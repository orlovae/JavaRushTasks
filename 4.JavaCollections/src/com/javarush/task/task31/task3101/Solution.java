package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        List<File> unsortedFileList = new ArrayList<>();

        String path = args[0];

        File oldFile = new File(args[1]);

        String fileSeparator = System.getProperty("file.separator");
        String newNameFile = oldFile.getParent() + fileSeparator + "allFilesContent.txt";

        File newFile = new File(newNameFile);

        FileUtils.renameFile(oldFile, newFile);

        try (FileOutputStream fos = new FileOutputStream(newFile)){

            File file = new File(path);
            File[] foldersAndFiles = file.listFiles();

            for (File item:foldersAndFiles
                    ) {
                getAllFoldersAndFiles(item, unsortedFileList,50);
            }

            List<File> sortedFileList = getSortedFiles(unsortedFileList);

        for (File item:sortedFileList
             ) {
            System.out.println(item.getAbsolutePath() + " - " + item.getName());
        }

            for (File item:sortedFileList
                    ) {
                FileInputStream fis = new FileInputStream(item);


                byte[] buffer = new byte[1024];
                while (fis.available() > 0) {
                    int data = fis.read(buffer);
                    fos.write(buffer, 0, data);
                }

                fos.write(System.lineSeparator().getBytes());
                fos.flush();
                fis.close();
            }
        }
    }

    public static void getAllFoldersAndFiles(File file, List<File> fileList, int sizeFile) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File item:files
                    ) {
                getAllFoldersAndFiles(item, fileList, sizeFile);
            }
        } else {
            if (file.length() <= sizeFile) {
                fileList.add(file);
            }
        }
    }

    public static List<File> getSortedFiles (List<File> unsortedFileList) {
//        List<File> sortedFileList = new ArrayList<>();

//        for (File item:unsortedFileList
//                ) {
//            if (item.length() <= limit) {
//                sortedFileList.add(item);
//            }
//        }

        Collections.sort(unsortedFileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        return unsortedFileList;
    }
}
