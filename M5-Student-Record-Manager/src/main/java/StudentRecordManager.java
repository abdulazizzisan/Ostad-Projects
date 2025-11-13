import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRecordManager {
    // Demo 1: Creating many Scanners on files without closing
    public static void demoFileResourceLeak() {
        System.out.println("=== Demo 1: File Scanner Resource Leak ===");
        System.out.println("Creating many file Scanners without closing...\n");

        List<Scanner> scanners = new ArrayList<>();
        int count = 0;

        try {
            // Create a temporary file to read from
            File tempFile = File.createTempFile("test", ".txt");
            tempFile.deleteOnExit();

            // Try to create many scanners
            for (int i = 0; i < 200000; i++) {
                Scanner sc = new Scanner(tempFile);
                scanners.add(sc);
                count++;

                if (i % 100 == 0) {
                    System.out.println("Created " + i + " scanners...");
                }
            }

            System.out.println("\nSuccess! Created " + count + " scanners.");
            System.out.println("(You might hit OS limits with more scanners)");

        } catch (IOException e) {
            System.out.println("\n❌ ERROR after " + count + " scanners!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("\nThis is a resource leak! We ran out of file descriptors.");
        } finally {
            // Clean up
            for (Scanner sc : scanners) {
                sc.close();
            }
        }
    }

    // Demo 2: Show that closing Scanner(System.in) closes System.in
    public static void demoSystemInClosing() {
        System.out.println("\n\n=== Demo 2: What happens when you close Scanner(System.in) ===");

        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc1.nextLine();
        System.out.println("Hello, " + name + "!");

        sc1.close(); // This closes System.in!
        System.out.println("\nScanner closed. Now trying to create another Scanner...");

        try {
            Scanner sc2 = new Scanner(System.in);
            System.out.print("Enter your age: ");
            String age = sc2.nextLine(); // This will fail!
            System.out.println("Age: " + age);
        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            System.out.println("\nThis shows that closing Scanner(System.in) closes the underlying stream!");
        }
    }

    // Demo 3: Good practice with try-with-resources
    public static void demoGoodPractice() {
        System.out.println("\n\n=== Demo 3: Good Practice with Files ===");
        System.out.println("Using try-with-resources automatically closes Scanner:\n");

        try {
            File tempFile = File.createTempFile("demo", ".txt");
            tempFile.deleteOnExit();

            // Write something to the file
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("Java\nPython\nC++");
            }

            // Read with try-with-resources (auto-closes)
            try (Scanner sc = new Scanner(tempFile)) {
                System.out.println("Reading file:");
                while (sc.hasNextLine()) {
                    System.out.println("- " + sc.nextLine());
                }
            } // Scanner automatically closed here!

            System.out.println("\n✓ Scanner was automatically closed!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("SCANNER RESOURCE MANAGEMENT DEMONSTRATION");
        System.out.println("==========================================\n");

        // Run Demo 1 - File resource leak
        demoFileResourceLeak();

        // Run Demo 3 - Good practice
        demoGoodPractice();

        System.out.println("\n\n=== SUMMARY ===");
        System.out.println("1. Not closing Scanners on FILES = resource leak (can run out of file descriptors)");
        System.out.println("2. Scanner(System.in) is special - closing it closes System.in forever!");
        System.out.println("3. Best practice: Use try-with-resources for files, be careful with System.in");
        System.out.println("4. For simple programs that end quickly, not closing Scanner(System.in) is often okay");

        // Uncomment below to see Demo 2 (interactive)
        // System.out.println("\n\nPress Enter to run Demo 2 (interactive)...");
        // new Scanner(System.in).nextLine();
        // demoSystemInClosing();
    }

}
