package Nutriapp2.Database;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//Having trouble refering to the ingredient manager in guest so that each sting line is added to the list<String> ingredients
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import Nutriapp2.GuestMode.IngredientManager;
import Nutriapp2.food.Ingredient;

public class CSVDataImporter implements DatabaseImporter{
    @Override
    public void importData(String fileName) throws CsvValidationException {
        String updatedcsvFile = "Nutriapp2/Database/data.csv";

        List<Integer> columnsToKeep = new ArrayList<>();
        columnsToKeep.add(0);  // Keep column NDB_No (id)
        columnsToKeep.add(1);  // Keep column Shrt_Desc (name)
        columnsToKeep.add(3);  // Keep column Energ_Kcal (calories)
        columnsToKeep.add(4);  // Keep column Protein_(g) (protein)
        columnsToKeep.add(5);  // Keep column Lipid_Tot_(g) (fat)
        columnsToKeep.add(7);  // Keep column Carbohydrt_(g) (carbs)
        columnsToKeep.add(8);  // Keep column Fiber_TD_(g) (fiber)
        
        try (CSVReader reader = new CSVReader(new FileReader(fileName));
                CSVWriter writer = new CSVWriter(new FileWriter(updatedcsvFile))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String[] trimmedLine = trimLine(nextLine, columnsToKeep);
                writer.writeNext(trimmedLine);
            }
            
            // Close the CSV reader and CSV writer
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String[] trimLine(String[] line, List<Integer> columnsToKeep) {
        String[] trimmedLine = new String[columnsToKeep.size()];
        for (int i = 0; i < columnsToKeep.size(); i++) {
            int columnIndex = columnsToKeep.get(i);
            trimmedLine[i] = line[columnIndex];
            Ingredient ingredient = IngredientConverter.convertStringToIngredient(trimmedLine[i]);
            List<Ingredient> list = new ArrayList<Ingredient>();
            IngredientManager Manager = new IngredientManager(list);
            Manager.addIngredient(ingredient);
        }
        return trimmedLine;
    }
}
