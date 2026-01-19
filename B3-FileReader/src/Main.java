import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String path = "./notes";

        File file = new File("./notes");
        String[] arr = file.list();
        assert arr != null;

        arr = Arrays.stream(arr).map(s -> s.substring(0, s.length() - 4)).toArray(String[]::new);

        System.out.println(Arrays.toString(arr));
//        Scanner sc = new Scanner(System.in);
//
//        // absolute path: C:\\Users\\YourName\\Documents\\note-1.txt
//        // relative path: ..\\note-1.txt
//
//        System.out.println("Welcome to the File Based Note Taking Application.");
//        System.out.println("Please Enter your note: ");
//        String note = sc.nextLine();
//
//        // Create a new file
//        File file = new File("note-1.txt");
//        try {
//            boolean fileCreated = file.createNewFile();
//            if (fileCreated) {
//                System.out.println("File created successfully: " + file.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException ex) {
//            System.out.println("An error occurred while creating the file: " + ex.getMessage());
//        }
//
//        // Write note(text) to file
//        try {
//            FileWriter writer = new FileWriter("note-2.txt");
//            writer.write(note);
//            writer.close();
//        } catch (IOException ex) {
//            System.out.println("An error occurred while writing to the file: " + ex.getMessage());
//        }
//
//        // Read note(text) from file
//        File file = new File("note-2.txt");
//        try{
//            Scanner fileReader = new Scanner(file);
//
//            System.out.println("Note from file is: ");
//            while (fileReader.hasNextLine()){
//                System.out.println(fileReader.nextLine());
//            }
//
////            String content = fileReader.useDelimiter("\\Z").next();
//
////            System.out.println(content);
//
//            fileReader.close();
//        }catch (IOException ex){
//            System.out.println("An error occurred while reading the file: " + ex.getMessage());
//        }
//
//        // Update note(text) in file
//        try{
//            Scanner fileReader = new Scanner(file);
//            System.out.println("This is the existing note: ");
//            System.out.println("Please enter what you want to add: ");
//            String updatePart = sc.nextLine();
//
//
//            FileWriter writer = new FileWriter("note-2.txt", true);
//            writer.write("\n" + updatePart);
//            writer.close();
//            fileReader.close();
//
//        }catch (IOException ex){
//            System.out.println("An error occurred while updating the file: " + ex.getMessage());
//        }
    }
}