package Database;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
//Having trouble refering to the ingredient manager in guest so that each sting line is added to the list<String> ingredients
//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
//import com.opencsv.exceptions.CsvValidationException;

import GuestMode.IngredientManager;
import food.Food;
import food.Ingredient;

public class CSVDataImporter implements DatabaseImporter{
    List<Ingredient> ingredients = new ArrayList<>();
    @Override
    public void importData(String fileName) throws IOException{
        String filerString = fileName;
        String exportedDatafile = "Nutriapp2/file.csv";
        String delimiter = ",";
        boolean isFirstLine = true;
        int[] columnstoKeep = {1,3,4,5,7,8};
        int numRowsToRead = 25; 
        
        System.out.print("Declared the columns that will be kept\n");

        //read given csv file and store the specified selected data in the list
        List<String[]> selectedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filerString))) {
            String line = "";
            int count = 0;
            System.out.print("reading data and adding to selectedData\n");
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { // Skip the first line
                    isFirstLine = false;
                    continue;
                }
                count++;
                if (count > numRowsToRead) {
                    break; // Exit the loop if we've read the desired number of rows
                }
                String[] fields = line.split(delimiter);//(delimiter + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                List<String> selectedFields = new ArrayList<>();
                String name = fields[1];
                double calories = Double.parseDouble(fields[3]);
                double protein  = Double.parseDouble(fields[4]);
                double fat = Double.parseDouble(fields[5]);
                double carbs = Double.parseDouble(fields[7]);
                double fiber = Double.parseDouble(fields[8]);
                Ingredient ingredient = new Ingredient(name, (int) calories, (int)protein, (int) fat, (int) carbs, (int) fiber);
                for (int i : columnstoKeep) {
                    selectedFields.add(fields[i]);//.replaceAll("^\"|\"$", ""));
                }
                ingredients.add(ingredient);
                
                //System.out.print(ingredient.getName()+"\n");
                //IngredientManager Manager = new IngredientManager(ingredients);
                //Manager.addIngredient(ingredient);
                //Manager.getIngredients();
                
                selectedData.add(selectedFields.toArray(new String[0]));
            }
        }
        // Writng into the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportedDatafile))) {
            //System.out.print("for loop begins\n");
            for (String[] row : selectedData) {
                String lineToWrite = String.join(delimiter, row);
                writer.write(lineToWrite);
                writer.newLine();
            }
        }
    }
    public List<Ingredient> getData(){
        return ingredients;
    }
}
