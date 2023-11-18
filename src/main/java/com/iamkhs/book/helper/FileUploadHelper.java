package com.iamkhs.book.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public boolean uploadFile(MultipartFile file){
        boolean f = false;

        try {
//            String UPLOAD_DIR = "/Users/iamkhs/IdeaProjects/SpringBootBookRest/src/main/resources/static/image";
            String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator+file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            f = true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
