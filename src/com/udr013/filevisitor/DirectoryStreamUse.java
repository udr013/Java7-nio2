package com.udr013.filevisitor;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class DirectoryStreamUse {

    public static void main(String[] args) throws IOException {


        Path dir = Paths.get("/home/udr013/IdeaProjects/training/nio2-java7/newfolder/");
        // java.nio.file.NoSuchFileException:   /home/udr013/IdeaProjects/training/nio2-java7/newfolder/i
        // java.nio.file.NotDirectoryException: /home/udr013/IdeaProjects/training/nio2-java7/newfolder/index.html
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path value : stream) {
                System.out.println(value + " : " + Files.isDirectory(value));
            }
        }

        System.out.println("_______________________________");
        // the spaces will be taken literal in the globing!!! so first line only returns txt!!
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{txt, java, html}")) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{txt,java,html}")) {

            Iterator iterator = stream.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            //vs while loop
//            for(Object aStream : stream){
//                System.out.println(aStream);
//            }
        }
    }
}
// prints:
//        /home/udr013/IdeaProjects/training/nio2-java7/newfolder/index.html : false
//        /home/udr013/IdeaProjects/training/nio2-java7/newfolder/other : true
//        /home/udr013/IdeaProjects/training/nio2-java7/newfolder/bla.txt : false
//        /home/udr013/IdeaProjects/training/nio2-java7/newfolder/another : true
//        _______________________________
//        /home/udr013/IdeaProjects/training/nio2-java7/newfolder/index.html
//        /home/udr013/IdeaProjects/training/nio2-java7/newfolder/bla.txt
