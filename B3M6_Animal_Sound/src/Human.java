public class Human implements Animal{

    @Override
    public void makeSound() {
        System.out.println(talk());
    }

    public String talk(){
        return "Hello, I'm a human and I can talk!";
    }
}
