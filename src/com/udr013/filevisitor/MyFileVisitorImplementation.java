package com.udr013.filevisitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//we need to implement all 4 methods of the fileVisitor interface !
public class MyFileVisitorImplementation implements FileVisitor<Path> {

    Map<String, List<String>> files = new HashMap<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        String dirName = dir.getFileName().toString();
        if (dirName.startsWith("code")){
            return FileVisitResult.SKIP_SUBTREE;
        } else {
            return FileVisitResult.CONTINUE;
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String fileName = file.getFileName().toString(); //Path.toString
        if(fileName.endsWith(".txt")) {
            List<String> tips = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) { //with resources

                String line =null;
                while((line = reader.readLine()) != null){
                    tips.add(line);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            files.put(fileName.substring(0, fileName.length()-4), tips);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println(exc.getMessage());
        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
