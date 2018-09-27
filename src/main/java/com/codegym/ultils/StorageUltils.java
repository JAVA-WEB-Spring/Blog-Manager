package com.codegym.ultils;

import java.io.File;

public class StorageUltils {
    public static final String FEATURE_LOCATION = "C:\\Users\\DELL\\IdeaProjects\\blog-manager\\src\\main\\resources\\img";

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return fileName.substring(dotIndex);
    }

    public static void removeFeature(String fileName) {
        File file = new File(FEATURE_LOCATION + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

}
