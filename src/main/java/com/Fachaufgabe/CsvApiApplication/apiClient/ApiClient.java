package com.Fachaufgabe.CsvApiApplication.apiClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiClient {

    public String callExternalApi(String type, String value, Integer limit) {
        String url = "https://api.fda.gov/device/recall.json?search=" + type + ":\"" + value + "\"&limit=" + limit;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
