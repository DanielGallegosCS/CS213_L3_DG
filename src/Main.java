import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.*;

//Daniel Gallegos
// Main class: Entry point of the program with menu logic
public class Main {
    // Main method: Starts the program and displays the main menu
    public static void main(String[] args) {
        try {

            DisplayMainMenu();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

    }

    // Displays the main menu and handles user input for menu selection
    public static void DisplayMainMenu() {
        System.out.println("Lab#3 Armies Battle File IO Su25");
        System.out.println("Welcome To Army Battle:");
        System.out.println("Please select one of the following options:");
        System.out.println("Press 1 and enter to play:");
        System.out.println("Press 0 and enter to exit:");
        Scanner input = new Scanner(System.in);
        if (input.hasNextInt()) {
            int selection = input.nextInt();
            input.nextLine();

            Choice choice = Choice.fromNum(selection);
            Game play = new Game();
            switch (choice) {
                case Play:
                    System.out.println("play something");
                    play.playGame();
                    break;
                case Quit:
                    System.out.println("quiting");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid entry try again");
                    DisplayMainMenu();
            }
        } else {
            System.out.println("Invalid input. Please enter a number");
            input.nextLine();
            DisplayMainMenu();
        }
    }
}
// Creature class: Represents a creature with attributes like strength, health, name, and type
class Creature {
    // NonStatic fields in a class are called instance variables
    //
    private int strength;
    private int health;
    private String name;
     private int damage;
    private String type;
    public Creature(){
        setStrength(100);
        setHealth(100);
        setType("type");
        setName("name");
        setCreature(getName(),getType());
    }
    public Creature(int strength, int health, String name, String type){
        this.setStrength(strength);
        this.setHealth(health);
        this.setName(name);
        this.setType(type);
        setCreature(name, type);

    }

    public Creature(int strength, int health){
        this.setStrength(strength);
        this.setHealth(health);
        this.setName("name");
        this.setType("type");
        setCreature(this.getName(), this.getType());
    }

    // Mutator function
    public void setCreature(String name, String type){
        this.strength = getStrength();
        this.health = getHealth();
        this.name = name;
        this.type = type;
        int damage = getDamage();

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

    public int getDamage() {
        int damage = (int)(Math.random()*10)%getStrength() + 1;
        return damage;
    }
    public int getDamage(int inflickDamage) {
        int damage = ((int)(Math.random()*10)%getStrength() + 1) + inflickDamage;
        return damage;
    }
//        Random rand = new Random();
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
    public String toString(){
//        System.out.println(getName()+" "+ getType() + " " +getStrength()+ " " + getDamage() + " " + getHealth());

        return getName()+" "+ getType() + " " +getStrength()+ " " + getDamage() + " " + getHealth();
    }


}

// Game class: Manages the gameplay logic including battles and game flow
class Game{


    public Creature[] getArmy1() {
        return army1;
    }

    public void setArmy1(Creature[] army1) {
        this.army1 = army1;
    }

    public Creature[] getArmy2() {
        return army2;
    }

    public void setArmy2(Creature[] army2) {
        this.army2 = army2;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    Creature[] army1;
    Creature[] army2;
    Army army = new Army();

    public Game(){

    }

    // Starts a new game by initializing armies and triggering the battle
    public void playGame(){
    //        System.out.println("Please enter the size of the army up to 10:");
    //        Scanner input = new Scanner(System.in);
    //            System.out.println("invalid input");
    //
        int size = askArmySize();
        army1 = new Creature[size];
        army2 = new Creature[size];
        for(int i = 0; i < size; i++){
            army1[i] = new Creature();
            army2[i] = new Creature();
        }
        army.setArmy(army1, army2);

//            System.out.println("This is army1");
//            System.out.println("Soldier #"+i+" "+army1[i].toString());
//            System.out.println("This is army2");
//            System.out.println("Soldier #"+i+" "+army2[i].toString());

        battle(army1, army2);
    }
    // Starts a new game by initializing armies and triggering the battle
    public void playGame(Creature[] army1, Creature[] army2){
        //        System.out.println("Please enter the size of the army up to 10:");
        //        Scanner input = new Scanner(System.in);
        //            System.out.println("invalid input");
        //
//
//            System.out.println("This is army1");
//            System.out.println("Soldier #"+i+" "+army1[i].toString());
//            System.out.println("This is army2");
//            System.out.println("Soldier #"+i+" "+army2[i].toString());

        battle(army1, army2);
    }

    // Prompts the user to enter the size of the army and validates input
    public int askArmySize(){
        int size = 0;
        System.out.println("Please enter the size of the army up to 10:");
        Scanner input = new Scanner(System.in);
        if(input.hasNext()) {
            size = input.nextInt();
            input.nextLine();
        } else{
            System.out.println("invalid input");
            input.nextLine();
            playGame();

        }
        return size;
    }
    public void printStats(){

    }

    // Handles the battle logic between two armies, including turn-based attacks
    public void battle(Creature[] army1, Creature[] army2){
        System.out.println("Lets see who attacks first:");
        int pick  = chooseWhoAttacksFirst();
        if (pick == 0){
            System.out.println("army1" +"Goes first");
            pick = 0;
        } else{
            System.out.println("army2" + "Goes first");
            pick = 1;
        }
        int round = 1;
//            Path file = Paths.get("/Users/dataisfun/IdeaProjects/Lab3SandBox/src/GameLog.txt");
//            OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            do {
                System.out.println("Round: " + round);
                //Print the details of both creatures before the battle in a table format
                //System.out.println("Each army details before the battle:");
                System.out.println("The total health of army 1 is : " + army.totalHealth(army1));
                System.out.println("The total health of army 2 is : " + army.totalHealth(army2));
                System.out.println("Details before Battle round");
                army.seeDetails(army1, army2, pick, round);
                if (pick == 1) {
                    System.out.println("Lets see if we have any damage mulitpliers for army 2");
                    inflictsMoreDamage(army2);
                    System.out.println("Thats great");
                    System.out.println("Lets battle!!!");
                    for (int i = 0; i < army1.length; i++) {
                        System.out.println(army2[i].getName() + " attacks " + army1[i].getName());
                        int health1 = army1[i].getHealth() - army1[i].getDamage();
                        army1[i].setHealth(health1);
                    }
                    System.out.println("Details after battle round!!");
                    army.seeDetails(army1, army2, round, pick);
                    pick = 0;
                } else {
                    System.out.println("Lets see if we have any damage mulitpliers");
                    inflictsMoreDamage(army1);
                    System.out.println("awesome");
                    System.out.println("Lets Battle!!");
                    for (int i = 0; i < army2.length; i++) {
                        System.out.println(army1[i].getName() + " attacks " + army2[i].getName());
                        int health2 = army2[i].getHealth() - army1[i].getDamage();
                        army2[i].setHealth(health2);
                    }
                    System.out.println("Details after battle round");
                    army.seeDetails(army1, army2, round, pick);
                    pick = 1;
                }

                round++;
                // The battle continues until one of the creatures health becomes equal to zero
            } while (checkHealth(army1) && checkHealth(army2));
            // Display winner defeats loser
            if (army.totalHealth(army1) < army.totalHealth(army2)) {
                System.out.println("WINNER!!!!!!:");
                System.out.println("army 2 the defeats army 1");
            } else if (army.totalHealth(army2) < army.totalHealth(army1)) {
                System.out.println("WINNER!!!!!!:");
                System.out.println("army 1 the defeats army 2");
                ;
            }
            //System.out.println("Message: " + e);
            System.out.println("That really escalated quickly ");
            System.out.println("Would you like to play again: please type yes or no");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();

        if(answer.equals("yes")){
            army.resetArmy(army1, army2);
            System.out.println("Armys have been reset for next game:");
            playAgain(army1, army2);
        } else{
            System.out.println("Thank you for playing");
            System.exit(0);
        }

    }
    public void playAgain(Creature[] army1, Creature[] army2){
        playGame(army1, army2);
    }

    // Applies special damage rules based on creature types
    public void inflictsMoreDamage(Creature[] army){
        for(Creature soldier: army){
            if(soldier.getType().equals("Chimera")) {
                if ((Math.random() % 100) < 10) {
                    System.out.println("Bonus soldier type: "+soldier.getType()+" gets an extra 20 damage points");
                    soldier.getDamage(20);
                }
            }else if(soldier.getType().equals("Nymph")) {
                if(Math.random() % 100 < 10) {
                    System.out.println("Bonus soldier type: " + soldier.getType() + " gets an extra 10 damage points");
                    soldier.getDamage(10);
                }


            } else if (soldier.getType().equals("Sylph")) {
                if((Math.random()) % 15 == 0) {
                    System.out.println("Bonus soldier type: " + soldier.getType() + " gets an inflicts double the damage");
                    soldier.getDamage((soldier.getDamage()));
                }
            }
        }
    }

    // Randomly determines which army attacks first
    public int chooseWhoAttacksFirst(){
        int one = 0;
        int two = 1;
        int pick = Math.random() < 0.5 ? one: two;

        return pick;
    }

    // Checks if all creatures in an army are still alive (health > 0)
    public boolean checkHealth(Creature[] army){
        boolean zero = true;
        for(Creature soldier: army){
            if(soldier.getHealth() <= 0){
                zero = false;
            }
        }
        return zero;
    }


}

// Army class: Handles creation and management of armies and their creatures
class Army{

    public static final int MIN_STAT = 40;
    public static final int MAX_STAT = 160;

    public Creature[] getCreatures() {
        return creatures;
    }

    public void setCreatures(Creature[] creatures) {
        this.creatures = creatures;
    }

    private Creature[] creatures;

    public Army(){

    }

    public void resetArmy(Creature[] army1, Creature[] army2){

        setArmy(army1, army2);
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

    // Assigns names to creatures in an army from a file
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
    // Randomly assigns creature types to each creature in the army
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
            army[i].setType(types[pickName]);
        }
    }
    // Randomly sets the strength of each creature in the army
    public void randomSetStrength(Creature[] army){
        Random rand = new Random();

        for(int i = 0; i < army.length; i++) {
            int ranSetStrength = rand.nextInt(MIN_STAT,MAX_STAT);
            army[i].setStrength(ranSetStrength);
        }
    }
    // Randomly sets the health of each creature in the army
    public void randomSetHealth(Creature[] army){
        Random rand = new Random();
        for(int i = 0; i < army.length; i++) {
            int ranSetHealth = rand.nextInt(MIN_STAT,MAX_STAT);
            army[i].setHealth(ranSetHealth);
        }
    }

    // Calculates the total health of an army
    public int totalHealth(Creature[] army){
        int totalHealth = 0;
        for(Creature soldier: army){
            totalHealth += soldier.getHealth();
        }

      return totalHealth;
    }

    // Outputs detailed stats of both armies to the console and a log file
    public void seeDetails(Creature[] army1, Creature[] army2, int round, int pick){
        try {
            Path file = Paths.get("/Users/dataisfun/IdeaProjects/CS213_L3_DG/src/GameLog.txt");
            //OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE_NEW));
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            if (pick == 0) {
                String headerFormat = "%-5s|%-25s|%-20s|%-20s|%-20s|%-20s|%-20s%n";
                String rowFormat = "%5d|%-25s|%-20s|%-20s|%20d|%20d|%20d%n";
                System.out.println(String.format(headerFormat, "Round", "Who is attacking", "Name: ", "Type:", "Strength:", "Damage:", "Health:"));
                writer.write(String.format(headerFormat, "Round", "Who is attacking", "Name: ", "Type:", "Strength:", "Damage:", "Health:"));
                for (int i = 0; i < army1.length; i++) {
                    System.out.println(String.format(rowFormat, round, "army1 is attacking", army1[i].getName(), army1[i].getType(), army1[i].getStrength()
                            , army1[i].getDamage(), army1[i].getHealth()));
                    writer.write(String.format(rowFormat, round, "army1 is attacking", army1[i].getName(), army1[i].getType(), army1[i].getStrength()
                            , army1[i].getDamage(), army1[i].getHealth()));
                    System.out.println(String.format(rowFormat, round, "army2 is not attacking", army2[i].getName(), army2[i].getType(), army2[i].getStrength()
                            , army2[i].getDamage(), army2[i].getHealth()));
                    writer.write(String.format(rowFormat, round, "army2 is not attacking", army2[i].getName(), army2[i].getType(), army2[i].getStrength()
                            , army2[i].getDamage(), army2[i].getHealth()));

                }
            } else if (pick == 1) {

                String headerFormat = "%-5s|%-25s|%-20s|%-20s|%-20s|%-20s|%-5s%n";
                String rowFormat = "%5d|%-25s|%-20s|%-20s|%20d|%20d|%20d%n";
                System.out.println(String.format(headerFormat, "Round", "Who is attacking", "Name:", "Type:", "Strength:", "Damage:", "Health:"));
                writer.write(String.format(headerFormat, "Round", "Who is attacking", "Name:", "Type:", "Strength:", "Damage:", "Health:"));
                for (int i = 0; i < army1.length; i++) {
                    System.out.println(String.format(rowFormat, round, "army1 is not attacking", army1[i].getName(), army1[i].getType(), army1[i].getStrength()
                            , army1[i].getDamage(), army1[i].getHealth()));
                    writer.write(String.format(rowFormat, round, "army1 is not attacking", army1[i].getName(), army1[i].getType(), army1[i].getStrength()
                            , army1[i].getDamage(), army1[i].getHealth()));
                    System.out.println(String.format(rowFormat, round, "army2 is attacking", army2[i].getName(), army2[i].getType(), army2[i].getStrength()
                            , army2[i].getDamage(), army2[i].getHealth()));
                    writer.write(String.format(rowFormat, round, "army2 is attacking", army2[i].getName(), army2[i].getType(), army2[i].getStrength()
                            , army2[i].getDamage(), army2[i].getHealth()));
                }
            }
            writer.close();
        }catch(Exception e){
            System.out.println("Message: " + e);
        }
    }

}

// Choice enum: Represents menu options for the game
enum Choice {
Play(1),
Quit(0),
DISPLAY(2);


private final int num;

Choice(int num) {
    this.num = num;
}

public static Choice fromNum(int num) {
    //Use a enhanced for loop to traverse through the choices
    for (Choice c : Choice.values()) {
        if (c.num == num) {
            return c;
        }
    }
    // For type checking had to create a DISPLAY enum to return a int value
    return Choice.DISPLAY;
}

}

