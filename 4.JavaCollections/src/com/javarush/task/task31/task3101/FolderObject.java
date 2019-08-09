package com.javarush.task.task31.task3101;

import java.io.File;
import java.util.*;

public class FolderObject {

    public List<File> getAllFoldersAndFiles (File file) {
        List<File> list = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File item:files
                    ) {
                list.addAll(getAllFoldersAndFiles(item));
            }
        } else {
            list.add(file);
        }
        return list;
    }

    public List<File> getSortedFiles (List<File> unsortedFileList, long limit) {
        List<File> sortedFileList = new ArrayList<>();

        for (File item:unsortedFileList
             ) {
            if (item.length() <= limit) {
                sortedFileList.add(item);
            }
        }

        Collections.sort(sortedFileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        return sortedFileList;
    }
}
