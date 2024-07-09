package com.Fachaufgabe.CsvApiApplication.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CsvUtil {
    public String convertJsonToCsv(String jsonResponse) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        return jsonResponse;
    }
}
