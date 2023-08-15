package com.example.project101.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.project101.ocr.Tess;

@Controller
public class FileUploadController {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload"; // upload.html 뷰 페이지로 이동
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> handleFileUpload(@RequestPart("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // 업로드된 파일을 저장할 폴더 생성 (폴더가 없을 경우)
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // 파일 이름 변경
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = uploadDirectory + File.separator + fileName;

            try {
                // 임시 폴더에 파일 저장
                String tempFilePath = "C:/temp/" + fileName;
                file.transferTo(new File(tempFilePath));

                // 이미지 이동
                Files.move(Paths.get(tempFilePath), Paths.get(filePath));

                // 이미지 파일을 사용하여 OCR 및 번역 수행
                File imageFile = new File(filePath);
                String jsonKeyFilePath = "C:/translation_api_key/spherical-entry-391823-2f5e9256772c.json"; // JSON 키 파일 경로

                Map<String, String> result = Tess.performOCRAndTranslation(imageFile, jsonKeyFilePath);

                return ResponseEntity.ok(result);
            } catch (IOException e) {
                e.printStackTrace();
                Map<String, String> errorResult = new HashMap<>();
                errorResult.put("error", "Error occurred during file upload and OCR: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
            }
        } else {
            // 업로드할 파일이 없을 경우 처리
            Map<String, String> errorResult = new HashMap<>();
            errorResult.put("error", "Please select a file to upload.");
            return ResponseEntity.badRequest().body(errorResult);
        }
    }
}