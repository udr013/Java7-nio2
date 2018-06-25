package com.udr013;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FilesThings {

    public static void main(String[] args) {
        // permissions are not set according...
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
        FileAttribute<Set<PosixFilePermission>> fileAttributes = PosixFilePermissions
                .asFileAttribute(permissions);

        try {
            Path path = Files.createDirectories(Paths.get("newfolder/another/blaze/bla.tst"), fileAttributes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
