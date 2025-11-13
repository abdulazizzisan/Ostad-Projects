import java.util.UUID;

// make getter setter for me
public class Student {

    private final String studentId;
    private String name;
    private int age;
    private double grade;


    // Constructor with all fields.
    public Student(String studentId, String name, int age, Double grade){
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Constructor overloading for auto generating id
    public Student(String name, int age, Double grade){

        String studentId = UUID.randomUUID().toString().substring(0, 8); // "aset"

        // this(...) // specific to jdk 22+

        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.grade = grade;

    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }

    public String toStringWithoutId(){
        return "Name: " + this.name + ", Age: " + this.age + ", Grade: " + this.grade;
    }

    public void printStudentInfo(){
        System.out.println("Student info: ");
        System.out.println(this.toString());
    }

    public String getStudentId(){
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
