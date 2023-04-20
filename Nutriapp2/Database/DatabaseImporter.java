package Nutriapp2.Database;

import com.opencsv.exceptions.CsvValidationException;

public interface DatabaseImporter {
    void importData(String fileName) throws CsvValidationException;
}
