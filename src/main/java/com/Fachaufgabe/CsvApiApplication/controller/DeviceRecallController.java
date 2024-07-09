package com.Fachaufgabe.CsvApiApplication.controller;

import com.Fachaufgabe.CsvApiApplication.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DeviceRecallController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/download")
    public ResponseEntity<String> downloadCsv(@RequestParam String type, String value, Integer limit) {
        String serviceResponse = null;
        try {
            serviceResponse = apiService.fetchAndConvertToCsv(type, value, limit);
            return ResponseEntity.ok().body(serviceResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch and convert data: " + e.getMessage());
        }
    }
}
