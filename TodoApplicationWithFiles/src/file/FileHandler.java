package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {

    public static String createNewJsonFile(String fileName) {
        System.out.println("Creating a new file: " + fileName);

        // .json
        if (!fileName.endsWith(".json")) {
            fileName += ".json";
        }

        String path = "src/" + fileName;
        boolean fileNotExists = true;

        File jsonFile = new File(path);
        try {
            fileNotExists = jsonFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }

        return fileNotExists ? "File created: " + jsonFile.getName() : "File already exists.";
    }

    public static String writeJsonToFile(String filePath, String fileName, String json) {

        // .json
        if (!fileName.endsWith(".json")) {
            fileName += ".json";
        }

        try{
            FileWriter writer = new FileWriter(filePath + "/" + fileName);
            writer.write(json);
            writer.close();
            return "json written to file: " + fileName;
        }catch(IOException e){
            return "An error occurred while writing to the file: " + e.getMessage();
        }

    }

    public static String readJsonFromFile(String filePath, String fileName) {
        if(!fileName.endsWith(".json")) {
            fileName += ".json";
        }

        try {
            File jsonFile = new File(filePath + "/" + fileName);
            Scanner sc = new Scanner(jsonFile);

            StringBuilder json = new StringBuilder();
            while (sc.hasNextLine()) {
                json.append(sc.nextLine());
            }
            return json.toString();
        }catch (FileNotFoundException e){
            return "File not found: " + e.getMessage();
        }catch (IOException e){
            return  "An error occurred while reading the file: " + e.getMessage();
        }

    }

}
