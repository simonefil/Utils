package com.Utils;

public class JavaInfo {

    private static String javaPath;

    public static String getJavaPath() {
        if (javaPath == null) {
            javaPath = System.getProperty("java.home");

            if (OSInfo.getOs() == OSInfo.OS.WINDOWS)
                javaPath = Dir.combine(javaPath, "bin/java.exe");
            else
                javaPath = Dir.combine(javaPath, "java");
        }
        return javaPath;
    }
}
