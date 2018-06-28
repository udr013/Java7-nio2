package com.udr013.files;

import java.io.IOException;
import java.nio.file.*;

public class FilesMove {

    public static void main(String[] args) {
        Path source = Paths.get("newfolder/another/blazing/folder/text.txt");
        // you need to specify the exact file, not just folder
        Path destination = Paths.get("newfolder/another/blazing/text5.txt"); //moves and rename

        Path sourcefolder = Paths.get("newfolder/blazing");
        Path renamesourcefolder = Paths.get("newfolder/another/blazing"); //note "parent" must exist

        try {
//            java.nio.file.NoSuchFileException: newfolder/another/blazing/folder/text3.txt
            Files.move(source , destination);
//            java.nio.file.NoSuchFileException: newfolder/blazing -> newfolder/blazin/blaing2
            Files.move(sourcefolder , renamesourcefolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
