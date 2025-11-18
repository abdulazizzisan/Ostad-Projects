package zisan.dev;


import zisan.dev.Animals.Animal;
import zisan.dev.Animals.human.Female;
import zisan.dev.Animals.human.Human;
import zisan.dev.Animals.human.Male;

public class Main {
    public static void main(String[] args) {
//        Animal dog = new Bird();
//        String dogSound = dog.makeSound();
//        System.out.println("Dog makes the sound: " + dogSound);
//
//        int dogWalksOn = dog.walksOn();
//        System.out.println("Dog walks on " + dogWalksOn + " legs");

        Male person = new Male();

//        System.out.println(person.makeSound());

        Human person2 = new Female();

        System.out.println(person2.makeSound());

        person2.die();
        person.die();

//        System.out.println("She walks on " + person2.walksOn());

//        person2.name = "Sokhina";
//        person2.age = 30;

//        person2.printSound();

    }
}