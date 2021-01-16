package com.grundszok.piotr.app.services;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public class FileDirectoryService {
    public Map<Integer, String> listFiles(FilenameFilter filenameFilter, String directory) {
        Map<Integer, String> numberToFilenameMap = new HashMap<>();

        File f = new File(directory);

        File[] files = f.listFiles(filenameFilter);

        for (int i = 0; i < files.length; i++) {
            numberToFilenameMap.put(i + 1, files[i].getName());
        }
        return numberToFilenameMap;
    }
}
