import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        Student student1 = new Student();
//
//        student1.name = "Zisan";
//
//        System.out.println(student1.name);

//        student1.setName("John Doe");
//
//        System.out.println("Full Name: " + student1.getName());
//        System.out.println("First Name: " + student1.getFirstname());
//        System.out.println("Last Name: " + student1.getLastname());

        Student student1 = new Student("Abdul Aziz", 16, "male",
                10, 'a', "Dhaka", "01712345678",
                "Notre Dame High School");

        Student student2 = new Student("Fahim Hossain", 15, "MaLE",
                12, 'b', "Chittagong", "01812345678");

//        System.out.println("Male".equals("male")); // false

        Student student3 = new Student();
        student3.setName("Kazi Imtiaz");
        student3.setAge(17);
        student3.setGender("male");
        student3.setRollNumber(15);
        student3.setSection('c');
        student3.setAddress("Sylhet");
        student3.setParentContact("01912345678");
        student3.setInstitutionName("Notre Dame High School");

        System.out.println(student1);

        student1.setName("Abdul Aziz Zisan");

        System.out.println(student1);
//
//        Student student4 = new Student();
//
//        System.out.println("Please enter student name: ");
//        student4.setName(sc.nextLine());
//
//        System.out.println("Please enter student age: ");
//        student4.setAge(sc.nextInt());
//        sc.nextLine(); // consume the newline
//
//        System.out.println("Please enter student gender: ");
//        student4.setGender(sc.nextLine());
//
//        System.out.println("Please enter student roll number: ");
//        student4.setRollNumber(sc.nextInt());
//        sc.nextLine(); // consume the newline
//
//        System.out.println("Please enter student section: ");
//        student4.setSection(sc.next().charAt(0));
//        sc.nextLine(); // consume the newline
//
//        System.out.println("Please enter student address: ");
//        student4.setAddress(sc.nextLine());
//        System.out.println("Please enter parent contact: ");
//        student4.setParentContact(sc.nextLine());
//
//        System.out.println("Please enter institution name: ");
//        student4.setInstitutionName(sc.nextLine());
//        System.out.println(student4);
//
//        System.out.println(student4);

        String read = student1.read("bangla");
        System.out.println(read);
        // enum class


    }
}