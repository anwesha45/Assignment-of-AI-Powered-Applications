package com.example.ai.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class Models {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClassificationRequest {
        private String text;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClassificationResponse {
        private String category;
        private double confidence;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailRequest {
        private String purpose;
        private String recipient_name;
        private String tone;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmailResponse {
        private String subject;
        private String body;
        private String response_time;
    }
}
