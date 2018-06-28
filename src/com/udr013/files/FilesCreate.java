package com.udr013.files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FilesCreate {

    public static void main(String[] args) {
        // permissions are not set according...
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
        FileAttribute<Set<PosixFilePermission>> fileAttributes = PosixFilePermissions.asFileAttribute(permissions);

        try {             //creates only dirs!
            Path path = Files.createDirectories(Paths.get("newfolder/another/blazing/folder"), fileAttributes);
            Path newFile = Paths.get(path.toString(), "text.txt"); //notice toString()! // for createfile
            if (!Files.exists(newFile, LinkOption.NOFOLLOW_LINKS)) {
                Files.createFile(newFile, fileAttributes); // throws FileAlreadyExistsException
            }

            PrintWriter printWriter = new PrintWriter(new FileWriter(newFile.toFile(),true),false);
            printWriter.print("blabal");
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("caught exception: " + e.getClass() + " - " + e.getMessage());
            e.getCause();
        }

    }
}
