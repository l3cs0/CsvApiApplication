package com.Fachaufgabe.CsvApiApplication.controller;

import com.Fachaufgabe.CsvApiApplication.service.ApiService;
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
        String serviceResponse = apiService.fetchAndConvertToCsv(type, value, limit);
        return ResponseEntity.ok().body(serviceResponse);

    }
}
