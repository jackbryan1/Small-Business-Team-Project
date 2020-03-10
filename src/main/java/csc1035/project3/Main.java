package csc1035.project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Contains the command line interface for running the EPOS program.
 */
public class Main {
    /**
     * Provides the command line interface to communicate with the EPOS program by taking in a user input of a command's
     * index to control which command to be executed.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        System.out.println("Program started.");
        Scanner scanner = new Scanner(System.in);
        String s;

        while(true){
            System.out.println("Next Command:");
            System.out.println("[0]:Add New Item");
            System.out.println("[1]:Check Stock");
            System.out.println("[2]:Update Stock");
            System.out.println("[3]:Delete Item");
            System.out.println("[4]:Transaction");
            System.out.println("[5]:List of Customers");
            System.out.println("[6]:Exit");
            s = scanner.nextLine();
            boolean running;
            ArrayList<Integer> idOptions;
            switch (s) {
                default:
                    System.out.println("No such command.");
                    break;

                case ("0")://Add New Item
                    ArrayList<Items> itemArray = new ArrayList<>();
                    running = true;
                    while (running) {
                        //Shows the list of items to be added to the database.
                        System.out.println("List of items:");
                        for (Items item : itemArray) {
                            System.out.println(item.getName());
                        }
                        if (itemArray.size() == 0) {
                            System.out.println("No items.");
                        }
                        System.out.println("Options");
                        System.out.println("[0]:Add new item");
                        System.out.println("[1]:Push to database");
                        System.out.println("[2]:Exit");
                        s = scanner.nextLine();
                        switch (s) {
                            default:
                                System.out.println("No such option.");
                            case ("0"):
                                System.out.println("Item to add:(name,category,isPerishable,makeCost,stock,sellPrice)");
                                s = scanner.nextLine();
                                if (s.matches(
                                        //Checks if each field of the item has the correct type.
                                        "(.+),(.+),(true|false),([0-9]*\\.?[0-9]+),([0-9]{1,10}),([0-9]*\\.?[0-9]+)"
                                )) {
                                    itemArray.add(ItemArray.stringLoader(s));
                                    System.out.println("Item added.");
                                    break;
                                } else {
                                    System.out.println("Wrong format.");
                                    break;
                                }
                            case ("1"):
                                ItemsDB.create(itemArray);
                                System.out.println("Items added to database.");
                                running = false;
                                break;
                            case ("2"):
                                running = false;
                                break;
                        }
                    }
                    break;

                case ("1")://Check stock
                    System.out.println("Name of Item:");
                    s = scanner.nextLine();
                    //Searches for a list of items that has the matching name.
                    idOptions = ItemsDB.readSearch(s);
                    if (!idOptions.isEmpty()) {
                        System.out.println("Choose an ID:");
                        s = scanner.nextLine();
                        //Catches exception if user did not enter a integer for id searching.
                        try {
                            int id = Integer.parseInt(s);
                            //Checks if the id input is one of the search results.
                            if (idOptions.contains(id)) {
                                System.out.printf("Stock: %s", ItemsDB.idSearch(id).getStock());
                                System.out.println();
                            } else {
                                System.out.println("This ID is not an option.");
                            }
                        } catch (Exception e) {
                            System.out.println("ID must be an integer.");
                        }
                    }
                    break;

                case ("2")://Update stock
                    System.out.println("Item to update:(name,stock)");
                    s = scanner.nextLine();
                    if (s.matches("(.+),([0-9]{1,10})")) {
                        String[] array = s.split(",");
                        String name = array[0];
                        int newStock = Integer.parseInt(array[1]);
                        //Searches for a list of items that has the matching name.
                        idOptions = ItemsDB.readSearch(name);
                        if (!idOptions.isEmpty()) {
                            System.out.println("Choose an ID:");
                            s = scanner.nextLine();
                            //Catches exception if user did not enter a integer for id searching.
                            try {
                                int id = Integer.parseInt(s);
                                //Checks if the id input is one of the search results.
                                if (idOptions.contains(id)) {
                                    Items i = ItemsDB.idSearch(id);
                                    i.setStock(newStock);
                                    ItemsDB.update(i);
                                    System.out.println("Stock updated.");
                                } else {
                                    System.out.println("This ID is not an option.");
                                }
                            } catch (Exception e) {
                                System.out.println("ID must be an integer.");
                            }
                        }
                    } else {
                        System.out.println("Wrong format.");
                    }
                    break;

                case ("3")://Delete Item
                    System.out.println("Name of item:");
                    s = scanner.nextLine();
                    //Searches for a list of items that has the matching name.
                    idOptions = ItemsDB.readSearch(s);
                    if (!idOptions.isEmpty()) {
                        System.out.println("Choose an ID:");
                        s = scanner.nextLine();
                        //Catches exception if user did not enter a integer for id searching.
                        try {
                            int id = Integer.parseInt(s);
                            //Checks if the id input is one of the search results.
                            if (idOptions.contains(id)) {
                                ItemsDB.delete(ItemsDB.idSearch(id));
                                System.out.println("Item deleted.");
                            } else {
                                System.out.println("This ID is not an option.");
                            }
                        } catch (Exception e) {
                            System.out.println("ID must be an integer.");
                        }
                    }
                    break;

                case ("4")://Transaction
                    running = true;
                    HashMap<Items, Integer> scannedItems = new HashMap<>();
                    while (running) {
                        System.out.println("Options:");
                        System.out.println("[0]Scan new item");
                        System.out.println("[1]Complete transaction");
                        System.out.println("[2]Exit");
                        s = scanner.nextLine();
                        switch (s) {
                            default:
                                System.out.println("No such option.");
                                break;
                            case ("0"):
                                System.out.println("Name of item:");
                                s = scanner.nextLine();
                                //Searches for a list of items that has the matching name.
                                idOptions = ItemsDB.readSearch(s);
                                if (!idOptions.isEmpty()) {
                                    System.out.println("Choose an ID:");
                                    s = scanner.nextLine();
                                    //Catches exception if user did not enter a integer for id searching.
                                    try {
                                        int id = Integer.parseInt(s);
                                        //Checks if the id input is one of the search results.
                                        if (idOptions.contains(id)) {
                                            System.out.println("Quantity:");
                                            s = scanner.nextLine();
                                            //Catches exception if user did not enter a integer for the quantity.
                                            try {
                                                int quantity = Integer.parseInt(s);
                                                if (quantity > 0) {
                                                    Items foundItem = ItemsDB.idSearch(id);
                                                    scannedItems.put(foundItem, quantity);
                                                    System.out.println("Item scanned.");
                                                    System.out.println("Scanned Items:");
                                                    for (Items item : scannedItems.keySet()) {
                                                        System.out.printf("%s: %s", item.getName(),
                                                                            scannedItems.get(item));
                                                        System.out.println();
                                                    }
                                                } else {
                                                    System.out.println("Quantity must be larger than 0.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Quantity must be an integer.");
                                            }
                                        } else {
                                            System.out.println("This ID is not an option.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("ID must be an integer.");
                                    }
                                }
                                break;
                            case ("1"):
                                System.out.println("Payment received:");
                                s = scanner.nextLine();
                                //Catches exception when user did not enter a number that can be casted into a float.
                                try {
                                    float moneyReceived = Float.parseFloat(s);
                                    float totalPrice = Transaction.transaction(scannedItems);
                                    //Checks if the money received is enough to pay for all the items.
                                    if (moneyReceived >= totalPrice) {
                                        System.out.println("Receipt?(Y/N)");
                                        s = scanner.nextLine();
                                        if (s.toUpperCase().equals("Y")) {
                                            float change = moneyReceived - totalPrice;
                                            Transaction.receipt(scannedItems, totalPrice, change);
                                        }
                                        //Does not print receipt if user input an option other than Y or N.
                                        if (!(s.toUpperCase().equals("N") || s.toUpperCase().equals("Y"))) {
                                            System.out.println("This option is invalid, receipt will not be printed.");
                                        }
                                        System.out.println("Transaction completed.");
                                        running = false;
                                        break;
                                    } else {
                                        System.out.println("Not enough payment.");
                                        break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Payment must be of numeric value.");
                                }
                            case ("2"):
                                running = false;
                                break;
                        }
                    }
                    break;

                case ("5"):
                    for (Customers customer: CustomerDB.getCustomers()) {
                        System.out.println(customer);
                    }
                    break;

                case ("6")://Exit
                    System.out.println("Program closed.");
                    return;
            }
        }
    }
}