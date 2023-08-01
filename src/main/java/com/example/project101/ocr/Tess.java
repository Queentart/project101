package com.example.project101.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tess {
    public static Map<String, String> performOCRAndTranslation(File imageFile, String jsonKeyFilePath) {
        Map<String, String> result = new HashMap<>();

        // Tesseract OCR 인스턴스 생성
        ITesseract tesseract = new Tesseract();

        // OCR 언어 설정
        // 다수의 언어 설정 (예: 영어, 프랑스어, 독일어)
        String[] languages = {"eng", "fra", "deu", "chi_sim", "jpn"};
        for (String language : languages) {
            tesseract.setLanguage(language);
        }

        try {
            // 이미지 파일로부터 텍스트 추출
            String extractedText = tesseract.doOCR(imageFile);

            // JSON 키 파일을 사용하여 인증 정보 로드
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonKeyFilePath));

            // Translate 서비스 클라이언트 생성
            Translate translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();

            // 텍스트 언어 감지
            String detectedLanguage = detectLanguage(extractedText, translate);

            // 번역 서비스 호출하여 번역
            Translation translation = translate.translate(extractedText, Translate.TranslateOption.targetLanguage("ko"));

            // 디코딩된 텍스트
            String decodedText = StringEscapeUtils.unescapeHtml4(translation.getTranslatedText());

            // 추출된 텍스트와 번역 결과를 합쳐서 Map에 저장
            result.put("extractedText", extractedText);
            result.put("detectedLanguage", detectedLanguage);
            result.put("translatedText", decodedText);

            return result;
        } catch (TesseractException | IOException e) {
            e.printStackTrace();
            result.put("error", "Error occurred during OCR and translation.");
            return result;
        }
    }

    private static String detectLanguage(String textToDetect, Translate translate) {
        // 텍스트 언어 감지
        return translate.detect(textToDetect).getLanguage();
    }
}