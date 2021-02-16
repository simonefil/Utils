package com.Utils;

import java.io.File;

public class Dir {

    public static String combine(String parRootDir, String... parDirs){
        String tFinalString = "";

        tFinalString = parRootDir;

        for (String tSubDir : parDirs) {
            tFinalString += "/" + tSubDir;
        }

        if (File.separatorChar == '\\') {
            tFinalString = tFinalString.replace("/", "\\");
        }
        else {
            tFinalString = tFinalString.replace('\\', '/');
        }

        return tFinalString;
    }
}
