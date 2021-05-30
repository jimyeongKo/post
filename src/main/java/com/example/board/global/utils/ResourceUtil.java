package com.example.board.global.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class ResourceUtil {

    private static String PATH;

    @Value("${app.filePath}")
    public void setUploadPath(String path) {
        PATH = path;
    }

    public static void saveFile(MultipartFile file, String location) {
        File targetFile = new File(PATH + location);

        if(!targetFile.isDirectory()) {
            targetFile.mkdirs();
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(PATH + filePath);

        if (file.exists()) {
            file.delete();
        }
    }

}
