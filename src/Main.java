//Daniel Gallegos

import javax.swing.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Creature player1;
    static Creature player2;
    public static void main(String[] args) {
        menu();
    }
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
                System.out.println("Not ready yet return back to menu");
                playGame(player1, player2);
                menu();
                break;
                //to reset battle creatures
            case 4:
                System.out.println("Not ready yet back to main menu");
                resetCreatures();
                System.out.println("Creatures have been reset");
                System.out.println(player1.toString());
                System.out.println("returning to main menu");
                menu();
                break;
                // quit program
            case 5:
                System.out.println("Not ready yet back to main menu");
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
        player1 = new Creature();
        player2 = new Creature();
    }

    public static int chooseWhoAttacksFirst(){
        int one = 0;
        int two = 1;
        // this expression is true approximately 50% of the time.
        int pick = Math.random() < 0.5 ? one: two;

        return pick;
    }
    public static void playGame(Creature player1, Creature player2){
        System.out.println("Lets see who attacks first:");
        int pick  = chooseWhoAttacksFirst();
        if (pick == 0){
            System.out.println(player1.getName() +"Goes first");
            pick = 0;
        } else{
            System.out.println(player2.getName() + "Goes first");
            pick = 1;
        }
        int round = 1;
        do{
            System.out.println("Round: " + round );
            seeDetails(player1, player2);
            if(pick == 1){
                System.out.println(player2.getName()+" attacks "+ player1.getName());
                int health1 = player1.getHealth() - player2.getDamage();
                player1.setHealth(health1);
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
        }while(player1.getHealth() > 0 && player2.getHealth() > 0);
        if(player1.getHealth() <= 0){
            System.out.println(player2.getName()+ " the " + player2.getType()+ " defeats " + player1.getName() );
        } else if (player2.getHealth() <= 0) {
            System.out.println(player1.getName()+ " the " + player1.getType()+ " defeats " + player2.getName() );
        }

    }
    

}
class Creature {
    private int strength;
    private int health;
    private String name;
    private int damage;
    private String type;

    public Creature(){
    }

    public Creature(int strength, int health, String name, String type){
        this.setStrength(strength);
        this.setHealth(health);
        this.setName(name);
        this.setDamage(damage);
        this.setType(type);
        setCreature(name, type);

    }
    
    public void setCreature(String name, String type){
        this.strength = 100;
        this.health = 100;
        this.name = name;
        this.type = type;
        this.damage = 20;
        
    }


    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setType(String type){
        this.type= type;
    }

    public String getType(){
        return type;
    }
    @Override
    public String toString(){
            System.out.println(getName()+" "+ getType() + " " +getStrength()+ " " + getDamage() + " " + getHealth());

        return getName()+" "+ getType() + " " +getStrength()+ " " + getDamage() + " " + getHealth();
    }
}
