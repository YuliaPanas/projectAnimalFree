package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.FileDto;
import com.example.projectAnimalFree.dto.FilesDto;
import com.example.projectAnimalFree.entity.FilesEntity;
import com.example.projectAnimalFree.repository.FilesRepository;
import com.example.projectAnimalFree.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController {
    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    private final FileService fileService;

    private final FilesRepository filesRepository;

    public FileController(FileService fileService, FilesRepository filesRepository) {
        this.fileService = fileService;
        this.filesRepository = filesRepository;
    }

    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public ResponseEntity<FileDto> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            FileDto fileDto = fileService.saveFile(file);
            return new ResponseEntity<>(fileDto, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<?> getFileById(@PathVariable Long id) throws IOException {
        String base64ImageByName = fileService.getBase64ImageById(id);
        return ResponseEntity.ok(base64ImageByName);
    }

    @GetMapping("/file/post/{id}")
    public ResponseEntity<FilesDto> getFile(@PathVariable Long id) throws IOException {
        String base64ImageByName = fileService.getBase64ImageByPostId(id);
        return ResponseEntity.ok(new FilesDto(base64ImageByName));
    }

    @GetMapping("/file/url/{id}")
    public ResponseEntity<byte[]> getFileByUrl(@PathVariable Long id) throws IOException {
        byte[] byteArrayImageById = fileService.getByteArrayImageById(id);
        return ResponseEntity.ok(byteArrayImageById);
    }

//    @GetMapping("/file/url/{id}")
//    public void getImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
//
//        FilesEntity files = filesRepository.findById(id).get();
//        File file = new File(files.getPath());
//        response.setContentType("application/png");
//        response.addHeader(CONTENT_DISPOSITION, "attachment; filename=image.jpg");
//        InputStream targetStream = new FileInputStream(file);
//        FileCopyUtils.copy(targetStream, response.getOutputStream());
//    }

}
