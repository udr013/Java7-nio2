package com.udr013.attributes;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Set;

public class ModifyIndividualAttributes {

    public static void main(String[] args) {
        Path path = Paths.get("newfolder/another/blazing/folder/text4.txt");
        try {
            //Note: all set methods return path

            Files.setAttribute(path, "lastModifiedTime", FileTime.fromMillis(0));
            System.out.println(Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS));

            Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
            System.out.println(Files.getLastModifiedTime(path));

            UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
            UserPrincipal owner = lookupService.lookupPrincipalByName("udr013"); //UserPrincipalNotFoundException

//          java.nio.file.FileSystemException: newfolder/another/blazing/folder/text4.txt: Operation not permitted
            Files.setOwner(path, owner); //returns path

            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrw-rw-");
            Files.setPosixFilePermissions(path, permissions);
//            Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rwxrw-rw-"));
            System.out.println(Files.getPosixFilePermissions(path)); //[OTHERS_WRITE, OWNER_EXECUTE, GROUP_WRITE, OWNER_WRITE, GROUP_READ, OWNER_READ, OTHERS_READ]

            System.out.println(Files.isExecutable(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
