package com.perobscurus.api.controllers;

import com.google.common.base.Preconditions;
import com.perobscurus.api.messages.FileUpload;
import com.perobscurus.api.messages.ImmutableFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    // Local directory for uploads
    final File dir;

    @Autowired
    public FileUploadController(@Value("${upload.dir}") final String path) {
        dir = new File(path);
        Preconditions.checkArgument(
                dir.exists() &&
                        dir.canWrite(), String.format("%s does not exist or is not accessible", path)
        );
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public FileUpload handleFileUpload(@RequestParam("file") MultipartFile file) {

        final File localFile = new File(dir, uniqueName(file.getOriginalFilename()));

        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ImmutableFileUpload.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .build();
    }

    private String uniqueName(final String basename) {
        return String.format("%s-%s", basename, UUID.randomUUID().toString());
    }
}