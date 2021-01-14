package com.grundszok.piotr.app.services;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public class FileDirectoryService {
    private String directory = System.getProperty("user.dir");

        public Map<Integer,String> listFiles(FilenameFilter filenameFilter){
            Map<Integer,String> numberToFilenameMap = new HashMap<>();
        try {

            File f = new File(directory);

            File[] files = f.listFiles(filenameFilter);

            for (int i = 0; i < files.length; i++) {
                numberToFilenameMap.put(i+1,files[i].getName());

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
            return numberToFilenameMap;
    }
}
