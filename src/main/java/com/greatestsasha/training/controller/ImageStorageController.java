package com.greatestsasha.training.controller;

import com.greatestsasha.training.model.dto.UploadResponse;
import com.greatestsasha.training.service.ImageStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class ImageStorageController {

    private final ImageStorageService imageStorageService;

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<UploadResponse> upload(@RequestPart("file") Mono<FilePart> file) {
        return imageStorageService.uploadFile(file);
    }

    @PostMapping(path = "/stream", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MULTIPART_FORM_DATA_VALUE)
    public Mono<UploadResponse> uploadStream(@RequestPart(value = "file") FilePart file) {
        return imageStorageService.uploadStream(file);
    }

    @GetMapping
    public ResponseEntity<Mono<InputStreamResource>> download(@RequestParam(value = "filename") String fileName) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(imageStorageService.download(fileName));
    }
}