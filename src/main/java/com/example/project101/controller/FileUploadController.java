package com.example.project101.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.example.project101.ocr.Tess;

@Controller
public class FileUploadController {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload"; // upload.html 뷰 페이지로 이동
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            try {
                // 업로드된 파일을 저장할 폴더 생성 (폴더가 없을 경우)
                File uploadDir = new File(uploadDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // 업로드된 파일을 서버에 저장
                String filePath = uploadDirectory + File.separator + file.getOriginalFilename();
                file.transferTo(new File(filePath));

                // 이미지에서 텍스트 추출 및 번역
                String jsonKeyFilePath = "";
                Map<String, String> ocrAndTranslationResult = Tess.performOCRAndTranslation(filePath, jsonKeyFilePath);

                // 모델에 결과 추가하여 uploaded.html로 전달
                model.addAttribute("fileName", file.getOriginalFilename());
                model.addAttribute("extractedText", ocrAndTranslationResult.get("extractedText"));
                model.addAttribute("detectedLanguage", ocrAndTranslationResult.get("detectedLanguage"));
                model.addAttribute("translatedText", ocrAndTranslationResult.get("translatedText"));

                return "uploaded"; // 업로드 완료 페이지로 이동
            } catch (IOException e) {
                // 파일 업로드 중 오류 발생 시 처리
                e.printStackTrace();
                model.addAttribute("error", "Error occurred during file upload: " + e.getMessage());
            }
        } else {
            // 업로드할 파일이 없을 경우 처리
            model.addAttribute("error", "Please select a file to upload.");
        }
        return "error"; // 에러 페이지로 이동
    }
}