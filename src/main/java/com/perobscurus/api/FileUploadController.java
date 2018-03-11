package com.perobscurus.api;

import com.perobscurus.api.messages.FileUpload;
import com.perobscurus.api.messages.ImmutableFileUpload;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    public FileUploadController() {
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public FileUpload handleFileUpload(@RequestParam("file") MultipartFile file) {

        final String localName = String.format("%s/%s-%s", "/tmp", UUID.randomUUID().toString(), file.getOriginalFilename());

        try {
            file.transferTo(new File(localName));
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return ImmutableFileUpload.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .build();
    }

    // todo: figure out how this can be leveraged
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

}