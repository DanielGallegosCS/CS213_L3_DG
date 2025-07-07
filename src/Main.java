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
        System.out.println(String.format(player1.toString(), player2.toString()));
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
        } else{
            System.out.println(player2.getName() + "Goes first");
        }
        //rounds how should i implement
        System.out.println("Round 1");
        seeDetails(player1, player2);

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
        this.damage = 0;
        
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
