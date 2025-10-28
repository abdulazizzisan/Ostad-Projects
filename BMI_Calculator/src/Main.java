import java.util.Scanner;

public class Main {

    // 1. user weight -> float
    // 2. user height -> float
    static float userInput(Scanner sc, String propertyName){
        System.out.println("Enter your" + propertyName + ": ");
        float value;
        if(sc.hasNextFloat()){
            value = sc.nextFloat();
            return value;
        } else {
            System.out.println("Invalid input");
            return -1;
        }
    }

    private static float calculateBMI(float weight, float height){
        return weight / (height * height);
    }

    private static String categorizeBMI(float bmi){
        if(bmi < 18.5){
            return "Underweight";
        } else if(bmi > 25){
            return "Overweight";
        } else {
            return "Normal";
        }
    }

    private static void displayResult(float bmi, String category){
        System.out.println("Your BMI report:");
        System.out.printf("Your BMI: %.2f \n", bmi);
        System.out.println("Your Category: " + category);
    }
    // bmi = 2.2343234, bmi = 2.23

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        float weight = userInput(scanner, "weight");
        float height = userInput(scanner, "height");

        float bmi = calculateBMI(weight, height);

        String category = categorizeBMI(bmi);

        displayResult(bmi, category);

        scanner.close();
    }
}