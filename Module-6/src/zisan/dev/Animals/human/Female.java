package zisan.dev.Animals.human;

public class Female extends Human {
    @Override
    public String makeSound(){
        return "Hello Miss";
    }

    public void printSound(){
        System.out.println(this.makeSound());
    }
}
