
//Daniel Gallegos

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
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
    static Creature[] army1;
    static Creature[] army2;

    // create two class variables for the two armies
    public static void main(String[] args) {
        menu();
    }
    // Class Method
    public static void menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose one of the following:");
        //changing this from create creatures to how many creatures do you want in your army
        // need to automate the creature creation.
        //System.out.println("enter 1 to input the number of creatures in your army:");
        System.out.println("enter 1 to create two creatures that will battle");
        System.out.println("enter 2 to see detailed out of creatures");
        System.out.println("enter 3 to play battle Creatures:");
        System.out.println("enter 4 reset creatures to prepare for another round");
        System.out.println("enter 5 to quit the program:");
        System.out.println("enter 6 for testing area");
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
            case 6:
               //System.out.println("testing area");
                System.out.println("Please enter a number of creatures from 1-10");
                var num = input.nextInt();
                input.nextLine();
                Army test = new Army(num, army1, army2);
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
    // changed this on 7/13/2025
    public Creature(){
        setStrength(100);
        setHealth(100);
        setType("type");
        setName("name");
        setCreature(getName(),getType());
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

    // added this constructor on 7/13
    public Creature(int strength, int health){
        this.setStrength(strength);
        this.setHealth(health);
        this.setName("name");
        this.setType("type");
        this.setDamage(damage);
        setCreature(this.getName(), this.getType());
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

class Army{

//Manages a group of 10 creatures should be able to change the number of creatures
// members
    // an array of 10 creature class objects can be less than 10 **
    // the type of each creature should be randomly generated **
    // load names from an input file **
    // set the strength and health of each creature to random values between 40 -160 **
    // make sure that min and max values of health and strength can be easily changed and require an update,
    // in one place only.
    // write all necessary constructors
    // output each army's stats before and after each battle in a table format to the screen and a file
    //text entries must be left aligned and numerical entries should be right aligned ( e.g., below, dots and bars are not required)
    //output the total health of an army before and after the battle
    // Army #1 Stats before the Battle
    // Creature Type Strength | Health|
    // Max..........|bahamut.........|.................96|...........107|
    //add any member functions you see fit
    //do not overwrite the output file on each battle, but append
    // NonStatic fields in a class are called instance variables

    private int numberOfCreatures;
//        private Creature[] army1;
//        private Creature[] army2;
    //default constructor
    public Army(){
    }

    public Army(int numberOfCreatures, Creature[] army1, Creature[] army2){
        this.numberOfCreatures = numberOfCreatures;
        army1 = new Creature[numberOfCreatures];
        army2 = new Creature[numberOfCreatures];
        //assignNamesAndTypes(army1, army2);
        for(int i = 0; i < numberOfCreatures; i++){
            //use default values so Creature object values are not null
            army1[i] = new Creature();
            army2[i] = new Creature();
        }
        setArmy(army1, army2);
// tested functions and passed make sure to remove before submitting
//        for(int i = 0; i < numberOfCreatures; i++){
//            System.out.println("this the name");
//            System.out.println(army1[i].getName());
//            System.out.println("this is the type");
//            System.out.println(army1[i].getType());
//            System.out.println("this is the strength");
//            System.out.println(army1[i].getStrength());
//        }
        seeDetails(army1,army2,2,1);

    }

    public void setArmy(Creature[] army1, Creature[] army2){
        assignNamesAndTypes(army1, army2);
        randomSetStrength(army1);
        randomSetStrength(army2);
        randomSetHealth(army1);
        randomSetHealth(army2);
    }

    public void assignNamesAndTypes(Creature[] army1, Creature[] army2){
        assignNames(army1, 1);
        assignNames(army2, 2);
        pickCreatureType(army1);
        pickCreatureType(army2);
    }

    public void assignNames(Creature[] army, int team){
        try {
            if(team == 1) {
                BufferedReader reader = new BufferedReader(
                        new FileReader("/Users/dataisfun/IdeaProjects/CS213_L3_DG/src/army1names.txt"));
                for (int i = 0; i < army.length; i++) {
                    army[i].setName(reader.readLine());
                }
            } else if (team == 2) {
                BufferedReader reader = new BufferedReader(
                        new FileReader("/Users/dataisfun/IdeaProjects/CS213_L3_DG/src/army2names.txt"));
                for (int i = 0; i < army.length; i++) {
                    army[i].setName(reader.readLine());
                }

            }
        } catch(IOException e){
            e.printStackTrace();

        }

    }
    //The type of each creature should be randomly generated
    public void pickCreatureType(Creature[] army) {
        String[] types = {"Pegasus",
                "Siren",
                "Gargoyle",
                "Chimera",
                "Nymph",
                "Sylph",
                "Salamander",
                "Undine",
                "Imp",
                "Brownie",
                "Sprite",
                "Pixie",
                "Kelpie",
                "Thunderbird",
                "Kappa",
                "Niffler",
                "Bowtruckle",
                "Puffskein"};

        Random rand = new Random();
        for (int i = 0; i < army.length; i++) {
            int pickName = rand.nextInt(types.length);
            //set a different name for every creature in the army
            army[i].setType(types[pickName]);
        }
    }
    //set the strength of each creature to random values between 40-160
    public void randomSetStrength(Creature[] army){
        Random rand = new Random();
        for(int i = 0; i < army.length; i++) {
            int ranSetStrength = rand.nextInt(40,160);
            //set a different name for every creature in the army
            army[i].setStrength(ranSetStrength);
        }
    }
    //set the health of each creature to random values between 40-160
    public void randomSetHealth(Creature[] army){
        Random rand = new Random();
        for(int i = 0; i < army.length; i++) {
            int ranSetHealth = rand.nextInt(40,160);
            //set a different name for every creature in the army
            army[i].setHealth(ranSetHealth);
        }
    }
    public void seeDetails(Creature[] army1, Creature[] army2, int round, int pick){
        if(pick == 0) {

                String headerFormat = "%-5s|%-25s|%-20s|%-20s|%-20s|%-20s|%-20s%n";
                String rowFormat = "%5d|%-25s|%-20s|%-20s|%20d|%20d|%20d%n";
                System.out.println(String.format(headerFormat, "Round", "Who is attacking", "Name: ", "Type:", "Strength:", "Damage:", "Health:"));
            for(int i = 0; i < army1.length; i++) {
                System.out.println(String.format(rowFormat, round, "army1 is attacking", army1[i].getName(), army1[i].getType(), army1[i].getStrength()
                        , army1[i].getDamage(), army1[i].getHealth()));
                System.out.println(String.format(rowFormat, round, "army2 is not attacking", army2[i].getName(), army2[i].getType(), army2[i].getStrength()
                        , army2[i].getDamage(), army2[i].getHealth()));

            }
        } else if (pick == 1) {

                String headerFormat = "%-5s|%-25s|%-20s|%-20s|%-20s|%-20s|%-5s%n";
                String rowFormat = "%5d|%-25s|%-20s|%-20s|%20d|%20d|%20d%n";
                System.out.println(String.format(headerFormat, "Round", "Who is attacking", "Name:", "Type:", "Strength:", "Damage:", "Health:"));
            for(int i =0; i < army1.length; i++) {
                System.out.println(String.format(rowFormat, round, "army1 is not attacking", army1[i].getName(), army1[i].getType(), army1[i].getStrength()
                        , army1[i].getDamage(), army1[i].getHealth()));
                System.out.println(String.format(rowFormat, round, "army2 is attacking", army2[i].getName(), army2[i].getType(), army2[i].getStrength()
                        , army2[i].getDamage(), army2[i].getHealth()));
            }
        }
    }

}

