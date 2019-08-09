package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private boolean containsName;
    private String partOfContent;
    private boolean containsContent;
    private int minSize;
    private boolean isMinSize;
    private int maxSize;
    private boolean isMaxSize;

    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        containsName = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) {
            containsName = false;
        }

        String contentStr = new String(Files.readAllBytes(file));

        containsContent = true;
        if (partOfContent != null && !contentStr.contains(partOfContent)) {
            containsContent = false;
        }

        isMinSize = true;
        if (content.length < minSize) {
            isMinSize = false;
        }

        isMaxSize = true;
        if (content.length > maxSize) {
            isMaxSize = false;
        }

        if (containsName && containsContent && isMinSize && isMaxSize) {
            foundFiles.add(file);
        }

        return super.visitFile(file, attrs);
    }
}
