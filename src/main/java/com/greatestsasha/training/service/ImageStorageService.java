package com.greatestsasha.training.service;

import com.greatestsasha.training.model.dto.UploadResponse;
import com.greatestsasha.training.util.InputStreamCollector;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageStorageService {

    public static final String UPLOAD_TIME_TEMPLATE = "upload file execution time {} ms";
    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @SneakyThrows
    public Mono<UploadResponse> uploadFile(Mono<FilePart> filePartMono) {
        return filePartMono
                .subscribeOn(Schedulers.boundedElastic())
                .map(multipartFile -> {
                    long startMillis = System.currentTimeMillis();
                    String filename = multipartFile.filename();
                    File temp = new File(filename);

                    temp.canWrite();
                    temp.canRead();
                    try {
                        log.debug(temp.getAbsolutePath());
                        // blocking to complete io operation
                        multipartFile.transferTo(temp).block();

                        var uploadObjectArgs = UploadObjectArgs.builder()
                                .bucket(bucketName)
                                .object(filename)
                                .filename(temp.getAbsolutePath())
                                .build();

                        ObjectWriteResponse response = minioClient.uploadObject(uploadObjectArgs);
                        temp.delete();
                        log.debug(UPLOAD_TIME_TEMPLATE, System.currentTimeMillis() - startMillis);
                        return getResponse(response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).log();
    }

    public Mono<InputStreamResource> download(String name) {
        return Mono.fromCallable(() -> {
                    GetObjectArgs objectArgs = GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(name)
                            .build();

                    InputStream response = minioClient.getObject(objectArgs);
                    return new InputStreamResource(response);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<UploadResponse> uploadStream(FilePart filePart) {
        return filePart.content()
                .subscribeOn(Schedulers.boundedElastic())
                .reduce(new InputStreamCollector(),
                        (collector, dataBuffer) -> collector.collectInputStream(dataBuffer.asInputStream())
                ).map(inputStreamCollector -> {
                    long startMillis = System.currentTimeMillis();
                    try {
                        String contentType = Objects.requireNonNull(filePart.headers().getContentType()).toString();
                        log.debug("ContentType {}", contentType);

                        PutObjectArgs args = PutObjectArgs.builder()
                                .object(filePart.filename())
                                .contentType(contentType)
                                .bucket(bucketName)
                                .stream(inputStreamCollector.getStream(), inputStreamCollector.getStream().available(), -1)
                                .build();

                        ObjectWriteResponse response = minioClient.putObject(args);
                        log.debug(UPLOAD_TIME_TEMPLATE, System.currentTimeMillis() - startMillis);

                        return getResponse(response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).log();
    }

    private static UploadResponse getResponse(ObjectWriteResponse response) {
        return UploadResponse.builder()
                .bucket(response.bucket())
                .objectName(response.object())
                .build();
    }
}