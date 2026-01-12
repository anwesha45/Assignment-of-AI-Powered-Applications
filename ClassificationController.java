package com.example.ai.controller;

import com.example.ai.model.Models.ClassificationRequest;
import com.example.ai.model.Models.ClassificationResponse;
import com.example.ai.service.OpenAIService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ClassificationController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/classify")
    public ResponseEntity<?> classifyText(@RequestBody ClassificationRequest request) {
        try {
            if (request.getText() == null || request.getText().isEmpty()) {
                return ResponseEntity.badRequest().body("Text input is required");
            }

            String prompt = "Classify the following text into one of these categories: Complaint, Query, Feedback, or Other. \n" +
                    "Also provide a confidence score between 0 and 1.\n" +
                    "\n" +
                    "Text: \"" + request.getText() + "\"\n" +
                    "\n" +
                    "Return the response in strictly valid JSON format like this: \n" +
                    "{ \"category\": \"CategoryName\", \"confidence\": 0.95 }";

            String rawResponse = openAIService.getCompletion(prompt);

            Pattern pattern = Pattern.compile("\\{[\\s\\S]*\\}");
            Matcher matcher = pattern.matcher(rawResponse);

            if (matcher.find()) {
                JSONObject json = new JSONObject(matcher.group(0));
                return ResponseEntity.ok(new ClassificationResponse(
                        json.getString("category"),
                        json.getDouble("confidence")
                ));
            } else {
                 return ResponseEntity.ok(new ClassificationResponse("Unknown", 0.0));
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error handling request: " + e.getMessage());
        }
    }
}
