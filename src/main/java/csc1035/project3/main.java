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
                    System.out.println("Item to add:(name,category,isPerishable,makeCost,stock,sellPrice)");
                    s = scanner.nextLine();
                    if (s.matches("(.*),(.*),(.*),(.*),(.*),(.*)")) {
                        String[] fields = s.split(",");

                        String name = fields[0];
                        String category = fields[1];
                        boolean isPerishable = Boolean.parseBoolean(fields[2]);
                        double makeCost = Double.parseDouble(fields[3]);
                        int stock =  Integer.parseInt(fields[4]);
                        double sellPrice = Double.parseDouble(fields[5]);

                        Items item = new Items(name, category, isPerishable, makeCost, stock, sellPrice);

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
