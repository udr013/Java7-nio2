package com.udr013.path;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathThings {

    public static void main(String[] args) throws IOException {
        //path can refer to a non existing file or directory
        Path path = Paths.get("../../new.txt"); //two folders up
        System.out.println(path.toString());
        System.out.println(path.toAbsolutePath());
//        System.out.println(path.getAbsolutePath()); //doesn't exist!! nosuch method
        // getRoot  returns null for relative path
        System.out.println(path.getRoot()); //null
        System.out.println(path.getParent()); // ../..
        File file = path.toFile();
        Path path2 = file.toPath();
        System.out.println(file.createNewFile()); //actually create the file...
        System.out.println("file.getname():"  + file.getName()); //file getname! new.txt
        System.out.println("file.getname(0):"  + path.getName(0)); //path getname!  ..
        System.out.println("path.getname(2):"  + path
                .getName(path.getNameCount()-1)); // new.txt
        System.out.println("path.getnameCount:"  + path.getNameCount()); // 3
        System.out.println(path2);
        System.out.println(path2.toString());
        path2  = Paths.get(file.getCanonicalPath());// /home/udr013/IdeaProjects
        System.out.println("getParent" + path2.getParent());

        System.out.println(path2.subpath(0,2)); //  home/udr013

        System.out.println(path2.getRoot());  // get root returns '/' for absolute path

        System.out.println(path.getFileName()); //new.txt
        System.out.println("resolve:" +path.resolve(path2)); //what's the use?

        System.out.println(path.compareTo(path2));  // -1
        System.out.println(path2.startsWith(path)); //false
        System.out.println(path2.endsWith(path.getFileName())); //true
    }
}
