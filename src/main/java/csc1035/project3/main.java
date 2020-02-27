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
                            "[1]:Command 1 \n" +
                            "[2]:Command 2 \n");
            s = scanner.nextLine();
            switch(s) {
                default:
                    System.out.println("No such command");
                    break;
                case ("0"):
                    System.out.println("Program closed");
                    return;
                case ("1"):
                    System.out.println("Runs command 1");
                    break;
                case ("2"):
                    System.out.println("Runs command 2");
                    break;
            }
        }
    }
}
