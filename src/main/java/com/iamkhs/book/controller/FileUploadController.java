package com.iamkhs.book.controller;

import com.iamkhs.book.helper.FileUploadHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    private final FileUploadHelper helper;

    public FileUploadController(FileUploadHelper helper) {
        this.helper = helper;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        if (helper.uploadFile(file)){
//            return ResponseEntity.ok("File is Successfully uploaded");
            return ResponseEntity.ok(ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
