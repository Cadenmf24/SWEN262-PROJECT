package Database;

import java.io.IOException;

//import com.opencsv.exceptions.CsvValidationException;

public interface DatabaseImporter {
    void importData(String fileName) throws IOException;
}
