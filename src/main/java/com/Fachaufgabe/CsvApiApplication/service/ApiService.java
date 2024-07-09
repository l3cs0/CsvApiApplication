package com.Fachaufgabe.CsvApiApplication.service;
import com.Fachaufgabe.CsvApiApplication.apiClient.ApiClient;
import com.Fachaufgabe.CsvApiApplication.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private CsvUtil csvUtil;

    public String fetchAndConvertToCsv(String type, String value, Integer limit) {
        String jsonResponse = apiClient.callExternalApi(type, value, limit);
        return csvUtil.convertJsonToCsv(jsonResponse);
    }

}
