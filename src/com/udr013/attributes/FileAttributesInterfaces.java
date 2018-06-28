package com.udr013.attributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;

public class FileAttributesInterfaces {

    public static void main(String[] args) {
        Path path = Paths.get("newfolder/another/blazing/folder");
        // no exception returns null
        try {
            // interface BasicFileAttributes
            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, DosFileAttributes.class); //throw IOException
            basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class); //throw IOException
            System.out.println(basicFileAttributes.isDirectory());
            System.out.println(basicFileAttributes.fileKey());
            // interface DosFileAttributes
            DosFileAttributes dosFileAttributes = (DosFileAttributes) basicFileAttributes; //throw IOException
            System.out.println(dosFileAttributes.isSystem());
            System.out.println(dosFileAttributes.isHidden());
            System.out.println(dosFileAttributes.isArchive());
            System.out.println(dosFileAttributes.isReadOnly());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // interface BasicFileAttributes
//        BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        //Interface PosixFileAttributes
        PosixFileAttributeView fileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        try {
            PosixFileAttributes posixFileAttributes = fileAttributeView.readAttributes();
            System.out.println(posixFileAttributes.group());
            System.out.println(posixFileAttributes.owner());
            System.out.println(posixFileAttributes.permissions());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
