//Daniel Gallegos

import javax.swing.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //Static fields in a class are called Class fields!!!
    //Static Class Variables are not instance variables!!!
    static Creature player1;
    //Class field
    static Creature player2;
    //Static Methods in a class are called Class Methods
    public static void main(String[] args) {
        menu();
    }
    // Class Method
    public static void menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose one of the following:");
        System.out.println("enter 1 to create two creatures that will battle");
        System.out.println("enter 2 to see detailed out of creatures");
        System.out.println("enter 3 to play battle Creatures:");
        System.out.println("enter 4 reset creatures to prepare for another round");
        System.out.println("enter 5 to quit the program:");
        int choice = input.nextInt();
        switch(choice){
            case 1: player1 = createCreatures();
                    player1.toString();
                    player2 = createCreatures();
                    player2.toString();
                    menu();
                break;
                // to see the detailed information of creatures
            case 2: seeDetails(player1, player2);
                    menu();
                break;
                // to play battle creatures
            case 3:
                System.out.println("Lets play battle Creatures!!!");
                playGame(player1, player2);
                menu();
                break;
                //to reset battle creatures
            case 4:
                resetCreatures();
                System.out.println("Creatures have been reset");
                System.out.println(player1.toString());
                System.out.println(player2.toString());
                System.out.println("returning to main menu");
                menu();
                break;
                // quit program
            case 5:
                System.out.println("Exiting game");
                System.exit(0);
                break;
            default:
                System.out.println("invalid selection back please choose again");
                menu();
        }
    }
    public static Creature createCreatures(){
        Scanner input = new Scanner(System.in);
        System.out.println("After every entry please press the enter button");
        System.out.println("please enter a enter name for the creature:");
        var name = input.nextLine();
        System.out.println("Please enter the type of creature for example: Elf");
        var type = input.nextLine();
        Creature player = new Creature(0, 0, name, type);
        return player;
    }

    public static void seeDetails(Creature player1, Creature player2){
        //%starts a format specifier ,- left-justifies the text within the column
        // #s allocates #= the number of characters for the string
        // %n inserts a platform-independent newline.
        String headerFormat = "| %-5s | %-5s | %-5s | %-5s | %-5s |%n";
        String rowFormat = "| %-5s | %-7s | %-10s | %-7s | %-5s |%n";
        System.out.println(String.format(headerFormat, "Name: ", "Type: ", "Strength: ", "Damage: ", "Health: "));
        System.out.println(String.format(rowFormat, player1.getName(), player1.getType(), player1.getStrength()
            , player1.getDamage(), player1.getHealth()));
        System.out.println(String.format(rowFormat, player2.getName(), player2.getType(), player2.getStrength()
            ,player2.getDamage(), player2.getHealth()));
    }

    public static void seeDetails(Creature player1, Creature player2, int round, int pick){
        if(pick == 0) {
            String headerFormat = "| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |%n";
            String rowFormat = "| %-5s | %-7s | %-10s | %-7s | %-5s | %-5s | %-5s |%n";
            System.out.println(String.format(headerFormat, "Round ", " Who is attacking", "Name: ", "Type: ", "Strength: ", "Damage: ", "Health:"));
            System.out.println(String.format(rowFormat, round, "attacking", player1.getName(), player1.getType(), player1.getStrength()
                    , player1.getDamage(), player1.getHealth()));
            System.out.println(String.format(rowFormat, round, "not attacking", player2.getName(), player2.getType(), player2.getStrength()
                    , player2.getDamage(), player2.getHealth()));
        } else if (pick == 1) {
            String headerFormat = "| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |%n";
            String rowFormat = "| %-5s | %-7s | %-10s | %-7s | %-5s | %-5s | %-5s |%n";
            System.out.println(String.format(headerFormat, "Round ", " Who is attacking", "Name: ", "Type: ", "Strength: ", "Damage: ", "Health:"));
            System.out.println(String.format(rowFormat, round, "not attacking", player1.getName(), player1.getType(), player1.getStrength()
                    , player1.getDamage(), player1.getHealth()));
            System.out.println(String.format(rowFormat, round, "attacking", player2.getName(), player2.getType(), player2.getStrength()
                    , player2.getDamage(), player2.getHealth()));

        }
    }

    public static void resetCreatures(){
        player1.setCreature(player1.getName(), player1.getType());
        player2.setCreature(player2.getName(), player2.getType());
        System.out.println("Creatures have been reset");
    }

    public static int chooseWhoAttacksFirst(){
        int one = 0;
        int two = 1;
        // this expression is true approximately 50% of the time.
        int pick = Math.random() < 0.5 ? one: two;

        return pick;
    }// static method to play the game
    public static void playGame(Creature player1, Creature player2){
        System.out.println("Lets see who attacks first:");
        // to see who will attack first call to static method choose WhoAttacksFirst() returns an int value
        // randomly select which Creature attacks first
        int pick  = chooseWhoAttacksFirst();
        // if pick == 0 then player1 goes first
        if (pick == 0){
            System.out.println(player1.getName() +"Goes first");
            pick = 0;
            // else pick == 1 then player 2 goes first
        } else{
            System.out.println(player2.getName() + "Goes first");
            pick = 1;
        }
        int round = 1;
        // use a do while loop to run the game... games ends when one of
        // the players health becomes less then 0
        // increment after each round

        do{
            System.out.println("Round: " + round );
            //Print the details of both creatures before the battle in a table format
            seeDetails(player1, player2);
            // if else conditionals to switch between players
            // turn-based battle -creatureA attacks creature B,
            // in the next round creature B attacks creature A.
            if(pick == 1){
                System.out.println(player2.getName()+" attacks "+ player1.getName());
                //the health of a defender is reduced by the amount of damage,
                // an attacker has inflicted.
                int health1 = player1.getHealth() - player2.getDamage();
                player1.setHealth(health1);
                //overloaded method call to seeDetails
                // passing to extra arguments round and pick
                // print details of both creatures after the battle in a table format
                seeDetails(player1, player2, round, pick);
                pick = 0;
            } else if (pick == 0) {
                System.out.println(player1.getName()+" attacks "+ player2.getName());
                int health2 = player2.getHealth() - player1.getDamage();
                player2.setHealth(health2);
                seeDetails(player1, player2, round, pick);
                pick = 1;
            }

            round++;
            // The battle continues until one of the creatures health becomes equal to zero
            //if one of the players health reaches 0 then exit while loop
        }while(player1.getHealth() > 0 && player2.getHealth() > 0);
        // if else to see which players health went to 0
        // Display winner defeats loser
        if(player1.getHealth() <= 0){
            System.out.println(player2.getName()+ " the " + player2.getType()+ " defeats " + player1.getName() );
        } else if (player2.getHealth() <= 0) {
            System.out.println(player1.getName()+ " the " + player1.getType()+ " defeats " + player2.getName() );
        }
        resetCreatures();
        System.out.println("if you would like to play again select 3 in the menu," +
                " no need to create creatures again");
        menu();

    }

    // when you use a static field or method you do not need to use an object

}
class Creature {
    // NonStatic fields in a class are called instance variables
    //
    private int strength;
    // instance variables
    private int health;
    // instance variables
    private String name;
    private int damage;
    private String type;
    // A no parameter constructor
    // default constructor
    public Creature(){
    }
    // A 4 parameter constructor to set all member variables; call setCreature() function to avoid redundancy
    public Creature(int strength, int health, String name, String type){
        this.setStrength(strength);
        this.setHealth(health);
        this.setName(name);
        this.setDamage(damage);
        this.setType(type);
        setCreature(name, type);

    }
    // Mutator function
    // setCreature function to set all member variables; health and strength cannot below zero
    public void setCreature(String name, String type){
        this.strength = 100;
        this.health = 100;
        this.name = name;
        this.type = type;
        this.damage = getDamage();
        
    }

    //Accessor Method
    public int getStrength() {
        return strength;
    }
    //Mutator Method or setters
    public void setStrength(int strength) {
        this.strength = strength;
    }
    //Accessor Method or getters
    public int getHealth() {
        return health;
    }
    //Mutator Method
    public void setHealth(int health) {
        this.health = health;
    }
    //Accessor Method
    public String getName() {
        return name;
    }
    //Mutator Method
    public void setName(String name) {
        this.name = name;
    }

    //Accessor method
    public int getDamage() {
        damage = (int)(Math.random()%getStrength() + 1);
        return damage;
    }
    //Mutator Method
    public void setDamage(int damage) {
        this.damage = damage;
    }
    //Mutator Method
    public void setType(String type){
        this.type= type;
    }
    // Accessor methods
    public String getType(){
        return type;
    }
    @Override
    // Non-static methods in a class are called instance methods
    // instance method
    public String toString(){
            System.out.println(getName()+" "+ getType() + " " +getStrength()+ " " + getDamage() + " " + getHealth());

        return getName()+" "+ getType() + " " +getStrength()+ " " + getDamage() + " " + getHealth();
    }

    //when you use a nonstatic field or method you must use an object

}
