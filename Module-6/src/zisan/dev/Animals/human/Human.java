package zisan.dev.Animals.human;

import zisan.dev.Animals.Animal;
import zisan.dev.Animals.Mortal;

public class Human implements Animal, Mortal {
    public String name;
    public int age;

    @Override
    public String makeSound() {
        return "Hello Sir";
    }

    @Override
    public int walksOn() {
        return 2;
    }

    @Override
    public void die() {
        System.out.println("You just died");
    }
}
