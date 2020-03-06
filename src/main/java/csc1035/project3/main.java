package csc1035.project3;

import java.util.ArrayList;
import java.util.HashMap;
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
                            "[2]:Add New Item \n" +
                            "[3]:Check Stock \n" +
                            "[4]:Update Stock \n" +
                            "[5]:Delete Item \n" +
                            "[6]:Transaction \n" +
                            "[7]:List of Customers"
                    );
            s = scanner.nextLine();
            boolean running;
            ArrayList<Integer> options;
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
                    ArrayList<Items> itemArray = new ArrayList<>();
                    running = true;
                    while (running) {
                        System.out.println("List of items:");
                        for (Items item : itemArray) {
                            System.out.println(item.getName());
                        }
                        if (itemArray.size() == 0) {
                            System.out.println("No items");
                        }
                        System.out.println("Options");
                        System.out.println("[0]:Add new item");
                        System.out.println("[1]:Push to database");
                        s = scanner.nextLine();
                        switch (s) {
                            default:
                                System.out.println("No such option.");
                            case ("0"):
                                System.out.println("Item to add:(name,category,isPerishable,makeCost,stock,sellPrice)");
                                s = scanner.nextLine();
                                if (s.matches("(.+),(.+),(true|false),([0-9]*\\.?[0-9]+),([0-9]{1,10}),([0-9]*\\.?[0-9]+)")) {
                                    itemArray.add(ItemArray.stringLoader(s));
                                    System.out.println("Item added.");
                                    break;
                                } else {
                                    System.out.println("Wrong format.");
                                    break;
                                }
                            case ("1"):
                                ItemsDB.create(itemArray);
                                System.out.println("Item added to database.");
                                running = false;
                                break;
                        }
                    }
                    break;

                case ("3"):
                    System.out.println("Name of Item:");
                    s = scanner.nextLine();
                    options = ItemsDB.readSearch(s);
                    if (!options.isEmpty()) {
                        System.out.println("ID Options:");
                        for (Integer option : options) {
                            System.out.println(option);
                        }
                        s = scanner.nextLine();
                        int id = Integer.parseInt(s);
                        if (options.contains(id)) {
                            System.out.printf("Stock: %s", ItemsDB.idSearch(id).getStock());
                            System.out.println();
                        } else {
                            System.out.println("This ID is not an option.");
                        }
                    }
                    break;

                case ("4"):
                    System.out.println("Item to update:(name,stock)");
                    s = scanner.nextLine();
                    if (s.matches("(.+),([0-9]{1,10})")) {
                        String[] array = s.split(",");
                        String name = array[0];
                        int newStock = Integer.parseInt(array[1]);

                        options = ItemsDB.readSearch(name);
                        if (!options.isEmpty()) {
                            System.out.println("ID Options:");
                            for (Integer option : options) {
                                System.out.println(option);
                            }
                            s = scanner.nextLine();
                            int id = Integer.parseInt(s);
                            if (options.contains(id)) {
                                Items i = ItemsDB.idSearch(id);
                                i.setStock(newStock);
                                ItemsDB.update(i);
                            } else {
                                System.out.println("This ID is not an option.");
                            }
                        }
                        System.out.println("Stock updated.");
                    } else {
                        System.out.println("Wrong format.");
                    }
                    break;

                case ("5"):
                    System.out.println("Name of item:");
                    s = scanner.nextLine();
                    options = ItemsDB.readSearch(s);
                    if (!options.isEmpty()) {
                        System.out.println("ID Options:");
                        for (Integer option : options) {
                            System.out.println(option);
                        }
                        s = scanner.nextLine();
                        int id = Integer.parseInt(s);
                        if (options.contains(id)) {
                            ItemsDB.delete(ItemsDB.idSearch(id));
                            System.out.println("Item deleted");
                        } else {
                            System.out.println("This ID is not an option.");
                        }
                    }
                    break;

                case ("6"):
                    System.out.println("Transaction started");
                    running = true;
                    HashMap<Items, Integer> scannedItems = new HashMap<>();
                    while (running) {
                        System.out.println("Options:");
                        System.out.println("[0]Scan new item.");
                        System.out.println("[1]Complete transaction.");
                        s = scanner.nextLine();
                        switch (s) {
                            default:
                                System.out.println("No such option.");
                                break;
                            case("0"):
                                System.out.println("Name of item:");
                                s = scanner.nextLine();
                                options = ItemsDB.readSearch(s);
                                if (!options.isEmpty()) {
                                    System.out.println("ID Options:");
                                    for (Integer id : options) {
                                        System.out.println(id);
                                    }
                                    s = scanner.nextLine();
                                    int id = Integer.parseInt(s);
                                    if (options.contains(id)) {
                                        System.out.println("Quantity:");
                                        s = scanner.nextLine();
                                        int quantity = Integer.parseInt(s);
                                        if (s.matches("([0-9]{1,10})") && quantity > 0) {
                                            Items foundItem = ItemsDB.idSearch(id);
                                            scannedItems.put(foundItem, quantity);
                                            System.out.println("Item scanned.");
                                            System.out.println("Scanned Items:");
                                            for(Items item :scannedItems.keySet()) {
                                                System.out.printf("%s: %s", item.getName(), scannedItems.get(item));
                                                System.out.println();
                                            }
                                        } else {
                                            System.out.println("Quantity must be an integer and larger than 0.");
                                        }
                                    } else {
                                        System.out.println("This ID is not an option.");
                                    }
                                }
                                break;
                            case("1"):
                                //Transaction.transaction(scannedItems);
                                System.out.println("Transaction completed.");
                                running = false;
                                break;
                        }
                    }
                    break;

                case ("7"):
                    System.out.println("List of Customers");
                    for (Customers customer: CustomerDB.getCustomers()) {
                        System.out.println(customer);
                    }
                    break;
            }
        }
    }
}