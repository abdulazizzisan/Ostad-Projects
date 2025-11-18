package zisan.dev.Animals;

public class Bird implements Animal{
    @Override
    public String makeSound() {
        return "kichir michir";
    }

    @Override
    public int walksOn() {
        return 2;
    }
}
