package csc1035.project3;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        System.out.println("Program started.");
        Scanner scanner = new Scanner(System.in);
        String s;

        while(true){
            System.out.println("Next Command");
            System.out.println("[0]:End \n" +
                            "[1]:Print Receipt \n" +
                            "[2]:Add new item \n" +
                            "[3]:Check stock \n" +
                            "[4]:Update stock \n" +
                            "[5]:Delete item \n" +
                            "[6]:Transaction \n"
                    );
            s = scanner.nextLine();
            switch(s) {
                default:
                    System.out.println("No such command");
                    break;
                case ("0"):
                    System.out.println("Program closed");
                    return;
                case ("1"):
                    System.out.println("Prints receipt");
                    break;
                case ("2"):
                    System.out.println("Add new item");
                    break;
                case ("3"):
                    System.out.println("Check stock");
                    break;
                case ("4"):
                    System.out.println("Update stock");
                    break;
                case ("5"):
                    System.out.println("Delete item");
                    break;
                case ("6"):
                    System.out.println("Starts transaction");
            }
        }
    }
}
