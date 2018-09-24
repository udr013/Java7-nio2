package com.udr013.filevisitor;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

// extends SimpleFileVisitor so we don't need to implement all methods of VileVisitor Interface
public class ListFileNames extends SimpleFileVisitor<Path> {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("/home/bla");
        ListFileNames listFileNames = new ListFileNames();
        Files.walkFileTree(path, listFileNames);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes){
        System.out.println("File name : " +  file.getFileName());
        return FileVisitResult.CONTINUE;
    }
}
