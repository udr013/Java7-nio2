package com.udr013.attributes;


import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

public class AccessAttributes{
        public static void main(String[] args) throws Exception {

            Path path = Paths.get("newfolder/another/blazing/folder/text4.txt");
            Path path2 = Paths.get("newfolder/another/blazing/folder/text4copy.txt");
            Path path3 = Paths.get("newfolder/another/blazing/text4.txt");

            long size = Files.size(path);
            System.out.println("size:" + size);

            Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path);
            System.out.println(posixFilePermissions); //[OWNER_READ, OTHERS_READ, GROUP_READ, OWNER_WRITE]

            System.out.println("isDirectory:" + Files.isDirectory(path));
            System.out.println("isExecutable:" + Files.isExecutable(path)); //false
            System.out.println("isHidden:" + Files.isHidden(path)); //false
            System.out.println("isReadable:" + Files.isReadable(path));
            System.out.println("isSymbolicLink:" + Files.isSymbolicLink(path)); //false
            System.out.println("isWritable:" + Files.isWritable(path));

            System.out.println("isSameFile:" + Files.isSameFile(path, path2)); //false
            System.out.println("isSameFile:" + Files.isSameFile(path, path3)); //false


            FileTime lastModifiedTime = Files.getLastModifiedTime(path);
            System.out.println("getLastModifiedTime:" + lastModifiedTime); //2018-06-28T09:48:15Z

            UserPrincipal owner = Files.getOwner(path);
            System.out.println("getOwner:" + owner);

            Object attribute = Files.getAttribute(path2, "unix:uid", LinkOption.NOFOLLOW_LINKS);
            System.out.println(attribute);

        }
}
