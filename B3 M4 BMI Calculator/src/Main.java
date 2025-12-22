import java.util.Scanner;

public class Main {

    public static double weightInKg;
    public static double heightInMeters;
    static boolean isHeightMeter;

    public static void main(String[] args) {

        getUserInput();

        double bmi = calculateBMI(weightInKg, heightInMeters);

        String category = getBmiCategory(bmi);
//
//        System.out.println("Your BMI is: " + bmi);
//        System.out.println("And Category is: " + category);

        printBmiResult(bmi, category);
        printBmiResult(category);

    }

    public static void getUserInput(){

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your weight in kilograms: ");
        if(input.hasNextDouble()){
            weightInKg = input.nextDouble();
        }

        System.out.println("Which measurement system do you want to use for height?");
        System.out.println("1. Meters");
        System.out.println("2. Feet");
        int choice = input.nextInt();

//        isHeightMeter = (choice == 1);

        if(choice == 1){
            isHeightMeter = true;
        }else {
            isHeightMeter = false;
        }

        System.out.print("Enter your height: ");
        if(input.hasNextDouble()) {
            heightInMeters = input.nextDouble();
        }

    }

    public static double calculateBMI(double weight, double height) {
        double bmi;
        if(!isHeightMeter){
            height = height * 0.3048;
        }
        bmi = weight / (height * height);
//        bmi = weightInKg / Math.pow(heightInMeters, 2);
        return bmi;
    }

    public static String getBmiCategory(double bmi) {

        if (bmi > 0 && bmi < 18.5) return "Underweight";

        else if (bmi >= 18.5 && bmi < 25) return "Normal";

        else if (bmi >= 25 && bmi <= 30) return "Overweight";

        else if (bmi > 30) return "Obese";

        return "invalid input";
    }

    static void printBmiResult(double bmi, String category){
        System.out.println("-----------------------------------");
        System.out.println("Your BMI is: " + bmi);
        System.out.println("And Category is: " + category);
        System.out.println("-----------------------------------");
    }

    static void printBmiResult(String category){
        System.out.println("-----------------------------------");
        System.out.println("Category is: " + category);
        System.out.println("-----------------------------------");
    }
}