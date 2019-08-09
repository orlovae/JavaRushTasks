package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private int allFolders = -1;
    private int allFiles;
    private long sizeAllFiles;

    public int getAllFolders() {
        return allFolders;
    }

    public int getAllFiles() {
        return allFiles;
    }

    public long getSizeAllFiles() {
        return sizeAllFiles;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (attrs.isDirectory()) {
            allFolders++;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            allFiles++;
            sizeAllFiles = sizeAllFiles + attrs.size();
        }
        return FileVisitResult.CONTINUE;
    }
}
