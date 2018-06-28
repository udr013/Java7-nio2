package com.udr013.files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FilesCopy {

    public static void main(String[] args) {
        // permissions are not set according...

        try {             //creates only dirs!
            // NoSuchFileException if source doesn't exist
            Path source = Paths.get("newfolder/another/blazing/folder/text4.txt");
            System.out.println(Files.exists(source)); //truw
            Path target = Paths.get("newfolder/another/blazing/folder/text4copy.txt");
            //without StandardCopyOption.REPLACE_EXISTING will throw FileAlreadyExistsException
            System.out.println(Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING)); //prints path
        } catch (IOException e) {
            System.out.println("caught exception: " + e.getClass() + " - " + e.getMessage());
            e.getCause();
        }

        try(OutputStream outputStream = new FileOutputStream("newfolder/another/blazing/folder/text4.txt", true)){

            Path source = Paths.get("newfolder/another/blazing/folder/text2.txt");
            System.out.println(Files.copy(source, outputStream)); //prints amount written  chars ,no StandardCopyOption
        } catch (IOException e) {
            System.out.println("caught exception: " + e.getClass() + " - " + e.getMessage());
            e.getCause();
        }

        try(InputStream in = System.in) {

            Path target = Paths.get("newfolder/another/blazing/folder/text3.txt");
            //without StandardCopyOption.REPLACE_EXISTING will throw FileAlreadyExistsException
            System.out.println(Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING)); //prints path
        } catch (IOException e) {
            System.out.println("caught exception: " + e.getClass() + " - " + e.getMessage());
            e.getCause();
        }

    }
}
