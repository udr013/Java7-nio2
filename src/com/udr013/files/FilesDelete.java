package com.udr013.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesDelete {

    public static void main(String[] args) {
        try {
//            java.nio.file.NoSuchFileException:
//            java.nio.file.DirectoryNotEmptyException
            Files.delete(Paths.get("newfolder/another/blazing/folder/text75"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
//            java.nio.file.DirectoryNotEmptyException
            System.out.println(Files.deleteIfExists(Paths.get("newfolder/another/blazing/folder/"))); //true,false
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
