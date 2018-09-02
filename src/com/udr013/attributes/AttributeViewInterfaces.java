package com.udr013.attributes;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AttributeViewInterfaces {

    public static void main(String[] args) {
        Path path = Paths.get("newfolder/another/blazing/folder/text3.txt");

        //FileAttributeView       this is null...
        FileAttributeView fileAttributeView = Files.getFileAttributeView(path, FileAttributeView.class);
        System.out.println(fileAttributeView); //null


        //BasicFileAttributesView
        fileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        System.out.println(fileAttributeView); //sun.nio.fs.UnixFileAttributeViews$Basic@677327b6
        System.out.println(fileAttributeView.name()); //basic

        try {
            //void method
            ((BasicFileAttributeView) fileAttributeView).setTimes(FileTime.fromMillis(0),FileTime.fromMillis(7638783),FileTime.from(12, TimeUnit.DAYS));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //DosFileAttributeView & DosFileAttributes
        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        System.out.println(dosFileAttributeView.name()); //dos

        try {
            System.out.println("size" + dosFileAttributeView.readAttributes().size());
            DosFileAttributes dosFileAttributes = dosFileAttributeView.readAttributes();
            Object o = dosFileAttributeView.readAttributes().fileKey();
            System.out.println(o.toString());
            System.out.println(dosFileAttributes.isArchive());
            System.out.println(dosFileAttributes.isHidden());
            System.out.println(dosFileAttributes.isReadOnly());
            System.out.println(dosFileAttributes.isSystem());
//            for(String s : dosFileAttributeView.readAttributes().fileKey())
            //all are void
            dosFileAttributeView.setArchive(true);
            dosFileAttributeView.setHidden(false);
            dosFileAttributeView.setReadOnly(true);
            dosFileAttributeView.setSystem(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            DosFileAttributes dosFileAttributes = dosFileAttributeView.readAttributes();
            System.out.println(dosFileAttributes.isArchive());
            System.out.println(dosFileAttributes.isHidden());
            System.out.println(dosFileAttributes.isReadOnly());
            System.out.println(dosFileAttributes.isSystem());
        } catch (IOException e) {
            e.printStackTrace();
        }


        //PosixFileAttributeView & PosixFileAttributes
        PosixFileAttributeView posixFileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        System.out.println(posixFileAttributeView.name()); //posix

        try {
            GroupPrincipal groupPrincipal = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByGroupName("udr013");
            posixFileAttributeView.setGroup(groupPrincipal); // if permitted else java.nio.file.FileSystemException
            posixFileAttributeView.setPermissions(PosixFilePermissions.fromString("rwxrw-rw-"));
            UserPrincipal userPrincipal = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("udr013");
            posixFileAttributeView.setOwner(userPrincipal); // if permitted else java.nio.file.FileSystemException
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PosixFileAttributes posixFileAttributes = posixFileAttributeView.readAttributes();
            GroupPrincipal group = posixFileAttributeView.readAttributes().group();
            UserPrincipal owner = posixFileAttributeView.readAttributes().owner();
            Set<PosixFilePermission> permissions = posixFileAttributeView.readAttributes().permissions();

            System.out.println("group: "+ group + ", owner: " + owner + ", permissions: " + permissions);
            //group: udr013, owner: udr013, permissions: [OTHERS_READ, OWNER_WRITE, GROUP_READ, OTHERS_WRITE, OWNER_READ, OWNER_EXECUTE, GROUP_WRITE]
        } catch (IOException e) {
            e.printStackTrace();
        }


        //FileOwnerAttributesView
        FileOwnerAttributeView fileOwnerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        System.out.println(fileOwnerAttributeView.name()); //owner

        try {
            UserPrincipal userPrincipal = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("udr013");
            fileOwnerAttributeView.setOwner(userPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(fileOwnerAttributeView.getOwner());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //UserDefinedFileAttributeView
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        System.out.println(userDefinedFileAttributeView.name()); // users
        try {
            userDefinedFileAttributeView.write("blabla", Charset.defaultCharset().encode("true"));
            List<String> list = userDefinedFileAttributeView.list();
            for (String l : list) {
                System.out.println(" list" + l); //DOSATTRIB
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //AclFileAttributeView this only works on windows OS
        AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(path, AclFileAttributeView.class);
        System.out.println(aclFileAttributeView); //null
//      System.out.println(fileAttributeView.name()); // nullpointer

//        try {
//            List<AclEntry> acl = aclFileAttributeView.getAcl(); //nullpointer
//            System.out.println(acl);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }
}
