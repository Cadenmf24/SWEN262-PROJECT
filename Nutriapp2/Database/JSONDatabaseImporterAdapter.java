package Database;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class JSONDatabaseImporterAdapter implements DatabaseImporterAdapter{
    private final DatabaseImporter dataImporter;

    public JSONDatabaseImporterAdapter(DatabaseImporter dataImporter) {
        this.dataImporter = dataImporter;
    }

    @Override
    public void importData(String fileName) {
        // Code to convert JSON file to CSV file
        String csvFileName = convertToCSV(fileName);
        // Use the CSVDataImporter to import the data
        try {
            dataImporter.importData(csvFileName);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private String convertToCSV(String fileName) {
        String outputFilename = "data.csv";

        try {
            // Load the JSON file into a Jackson tree model
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(fileName));

            // Extract the header row from the keys of the first object
            ObjectNode firstObjectNode = (ObjectNode) ((ArrayNode) rootNode).get(0);
            List<String> header = new ArrayList<>();
            Iterator<String> fieldNames = firstObjectNode.fieldNames();
            while (fieldNames.hasNext()) {
                header.add(fieldNames.next());
            }

            // Open a CSV file for writing
            FileWriter fileWriter = new FileWriter(outputFilename);
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(header.toArray(new String[0])));

            // Write each object in the JSON file to a row in the CSV file
            for (JsonNode node : rootNode) {
                List<Object> row = new ArrayList<>();
                Iterator<JsonNode> elements = node.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    if (element.isValueNode()) {
                        row.add(element.asText());
                    } else {
                        row.add("");  // Handle null or object/array nodes
                    }
                }
                csvPrinter.printRecord(row);
            }

            // Close the CSV printer and file writer
            csvPrinter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFilename;
    }
}
