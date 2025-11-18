package zisan.dev.Animals;

public class Dog implements Animal{

    @Override
    public String makeSound() {
        return "Gheu Gheu";
    }

    @Override
    public int walksOn() {
        return 4;
    }
}
