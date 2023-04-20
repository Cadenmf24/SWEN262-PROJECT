package Nutriapp2.Database;

import com.opencsv.exceptions.CsvValidationException;

public class Client {
    public static void main(String[] args) throws CsvValidationException {

        //CSV

        CSVDataImporter csvDataImporter = new CSVDataImporter(); // Create the Adaptee object
        csvDataImporter.importData("data.csv"); // Call the importData method on the Adapter

        //JSON
        DatabaseImporterAdapter dataImporterAdapter = new JSONDatabaseImporterAdapter(csvDataImporter); // Create the Adapter object and pass in the Adaptee object
        dataImporterAdapter.importData("data.json"); // Call the importData method on the Adapter
    }
}
