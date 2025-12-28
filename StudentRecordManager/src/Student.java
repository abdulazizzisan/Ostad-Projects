public class Student {
    private String name;
//    private String firstname;
//    private String lastname;
    private int age;
    private String gender;
    private int rollNumber;
    private char section;
    private String address;
    private String parentContact;
    private String institutionName; // Notre Dame High School

    public Student(String name, int age, String gender, int rollNumber, char section, String address, String parentContact, String institutionName) {
        this.name = name;
        this.age = age;
        this.gender = gender.toLowerCase();
        this.rollNumber = rollNumber;
        this.section = section;
        this.address = address;
        this.parentContact = parentContact;
        this.institutionName = institutionName;
    }

    public Student(String name, int age, String gender, int rollNumber, char section, String address, String parentContact) {
        this.name = name;
        this.age = age;
        this.gender = gender.toLowerCase();
        this.rollNumber = rollNumber;
        this.section = section;
        this.address = address;
        this.parentContact = parentContact;
        this.institutionName = "Notre Dame High School";
    }

    public Student() {
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
//
//        this.firstname = name.split(" ")[0];
//        this.lastname = name.split(" ")[1];

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.toLowerCase();
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public char getSection() {
        return section;
    }

    public void setSection(char section) {
        this.section = section;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParentContact() {
        return parentContact;
    }

    public void setParentContact(String parentContact) {
        this.parentContact = parentContact;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String toString(){
        return "Name: " + this.name + "\n" +
                "Age: " + this.age + "\n" +
                "Gender: " + this.gender + "\n" +
                "Roll Number: " + this.rollNumber + "\n" +
                "Section: " + this.section + "\n" +
                "Address: " + this.address + "\n" +
                "Parent Contact: " + this.parentContact + "\n" +
                "Institution Name: " + this.institutionName + "\n";
    }

    public String read(String subjectName){
        if(subjectName.equalsIgnoreCase("Math")){
            return "1 + 1 = 2 \nI'm learning Math!";
        } else if(subjectName.equalsIgnoreCase("Bangla")){
            return "আমার নামঃ " + this.name + ", আমি বাংলা পড়ছি!";
        } else {
            return "আমি ফেইল করব।";
        }
    }

//        public String getFirstname(){
//        return this.firstname;
//    }
//
//    public String getLastname(){
//        return this.lastname;
//    }
}
