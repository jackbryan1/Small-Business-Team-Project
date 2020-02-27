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
                    System.out.println("Item to add:(name,isPerishable,makeCost,stock,sellPrice)");
                    s = scanner.nextLine();
                    if (s.matches("(.*),(.*),(.*),(.*),(.*)")) {
                        String[] fields = s.split(",");
                        String name = fields[0];
                        boolean isPerishable = Boolean.parseBoolean(fields[1]);
                        float makeCost = Float.parseFloat(fields[2]);
                        int stock =  Integer.parseInt(fields[3]);
                        float sellPrice = Float.parseFloat(fields[4]);

                        Item item = new Item(name, isPerishable, makeCost, stock, sellPrice);

                        System.out.println("Item added");
                    } else {
                        System.out.println("Wrong format.");
                    }
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
