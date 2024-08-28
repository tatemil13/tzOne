package org.example.task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main{

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("java .\\Main.java values.json tests.json report.json\n");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader valuesReader = new FileReader(valuesFilePath);
             FileReader testsReader = new FileReader(testsFilePath);
             FileWriter reportWriter = new FileWriter(reportFilePath)) {

            JsonParser parser = new JsonParser();
            JsonObject values = parser.parse(valuesReader).getAsJsonObject();
            JsonObject tests = parser.parse(testsReader).getAsJsonObject();

            populateReport(tests, values);

            gson.toJson(tests, reportWriter);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void populateReport(JsonObject tests, JsonObject values) {
        for (String key : tests.keySet()) {
            JsonElement testElement = tests.get(key);
            if (testElement.isJsonObject()) {
                JsonObject testObject = testElement.getAsJsonObject();
                populateReport(testObject, values);
            } else if (testElement.isJsonArray()) {
                JsonArray testArray = testElement.getAsJsonArray();
                JsonArray reportArray = new JsonArray();
                for (JsonElement arrayElement : testArray) {
                    JsonObject arrayObject = arrayElement.getAsJsonObject();
                    populateReport(arrayObject, values);
                    reportArray.add(arrayObject);
                }
                tests.add(key, reportArray);
            } else {
                String valueId = testElement.getAsString();
                JsonElement valueElement = values.get(valueId);
                if (valueElement != null) {
                    tests.addProperty(key, valueElement.getAsString());
                }
            }
        }
    }
}