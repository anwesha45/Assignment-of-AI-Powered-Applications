package com.example.ai.controller;

import com.example.ai.model.Models.EmailRequest;
import com.example.ai.model.Models.EmailResponse;
import com.example.ai.service.OpenAIService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class EmailController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/generate-email")
    public ResponseEntity<?> generateEmail(@RequestBody EmailRequest request) {
        long startTime = System.currentTimeMillis();
        try {
            String prompt = "Generate a professional email based on the following details:\n" +
                    "\n" +
                    "Purpose: " + request.getPurpose() + "\n" +
                    "Recipient Name: " + request.getRecipient_name() + "\n" +
                    "Tone: " + request.getTone() + "\n" +
                    "\n" +
                    "Return the response in strictly valid JSON format like this:\n" +
                    "{\n" +
                    "  \"subject\": \"Email Subject Line\",\n" +
                    "  \"body\": \"The full body of the email...\"\n" +
                    "}";

            String rawResponse = openAIService.getCompletion(prompt);
            
            Pattern pattern = Pattern.compile("\\{[\\s\\S]*\\}");
            Matcher matcher = pattern.matcher(rawResponse);

            if (matcher.find()) {
                JSONObject json = new JSONObject(matcher.group(0));
                long endTime = System.currentTimeMillis();
                return ResponseEntity.ok(new EmailResponse(
                        json.getString("subject"),
                        json.getString("body"),
                        (endTime - startTime) + "ms"
                ));
            } else {
                 return ResponseEntity.internalServerError().body("Failed to parse AI response");
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error handling request: " + e.getMessage());
        }
    }
}
