package com.Fachaufgabe.CsvApiApplication.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvUtil {
    public String convertJsonToCsv(String jsonResponse) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        // Ergebnisliste für CSV-Zeilen
        List<String> csvLines = new ArrayList<>();

        // Headerzeile erstellen
        StringBuilder header = new StringBuilder();
        header.append("cfres_id,");
        header.append("product_res_number,");
        header.append("event_date_initiated,");
        header.append("event_date_posted,");
        header.append("recall_status,");
        header.append("event_date_terminated,");
        header.append("res_event_number,");
        header.append("product_code,");
        header.append("k_numbers,");
        header.append("product_description,");
        header.append("code_info,");
        header.append("recalling_firm,");
        header.append("address_1,");
        header.append("city,");
        header.append("state,");
        header.append("postal_code,");
        header.append("additional_info_contact,");
        header.append("reason_for_recall,");
        header.append("root_cause_description,");
        header.append("action,");
        header.append("product_quantity,");
        header.append("distribution_pattern");
        csvLines.add(header.toString());

        // Datenzeilen erstellen
        JsonNode resultsNode = rootNode.get("results");
        if (resultsNode.isArray()) {
            for (JsonNode result : resultsNode) {
                ArrayNode kNumbersNode = (ArrayNode) result.get("k_numbers");
                String kNumbers = String.join("|", kNumbersNode.findValuesAsText("k_number"));

                StringBuilder csvLine = new StringBuilder();
                csvLine.append(escapeString(result.get("cfres_id").asText())).append(",");
                csvLine.append(escapeString(result.get("product_res_number").asText())).append(",");
                csvLine.append(escapeString(result.get("event_date_initiated").asText())).append(",");
                csvLine.append(escapeString(result.get("event_date_posted").asText())).append(",");
                csvLine.append(escapeString(result.get("recall_status").asText())).append(",");
                csvLine.append(escapeString(result.get("event_date_terminated").asText())).append(",");
                csvLine.append(escapeString(result.get("res_event_number").asText())).append(",");
                csvLine.append(escapeString(result.get("product_code").asText())).append(",");
                csvLine.append(escapeString(kNumbers)).append(",");
                csvLine.append(escapeString(result.get("product_description").asText())).append(",");
                csvLine.append(escapeString(result.get("code_info").asText())).append(",");
                csvLine.append(escapeString(result.get("recalling_firm").asText())).append(",");
                csvLine.append(escapeString(result.get("address_1").asText())).append(",");
                csvLine.append(escapeString(result.get("city").asText())).append(",");
                csvLine.append(escapeString(result.get("state").asText())).append(",");
                csvLine.append(escapeString(result.get("postal_code").asText())).append(",");
                csvLine.append(escapeString(result.get("additional_info_contact").asText())).append(",");
                csvLine.append(escapeString(result.get("reason_for_recall").asText())).append(",");
                csvLine.append(escapeString(result.get("root_cause_description").asText())).append(",");
                csvLine.append(escapeString(result.get("action").asText())).append(",");
                csvLine.append(escapeString(result.get("product_quantity").asText())).append(",");
                csvLine.append(escapeString(result.get("distribution_pattern").asText()));

                csvLines.add(csvLine.toString());
            }
        }

        // CSV-Zeilen zu einem String zusammenführen
        return String.join("\n", csvLines);
    }

    // Methode zum Escapen von Strings für CSV
    private String escapeString(String str) {
        // Falls der String null ist oder ein Komma enthält, in Anführungszeichen setzen und doppelte Anführungszeichen escapen
        if (str == null || str.contains(",")) {
            return "\"" + str.replaceAll("\"", "\"\"") + "\"";
        } else {
            return str;
        }
    }
}
