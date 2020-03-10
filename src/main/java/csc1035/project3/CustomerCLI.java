package csc1035.project3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Allows interaction with all methods of CustomerDB via CLU
 * @author b9038224
 */
public class CustomerCLI {

    // Instance Variables
    Scanner input = new Scanner(System.in);
    boolean running = true;

    // Constructor
    public CustomerCLI(){
        ui();
    }

    // Methods

    /**
     * Gets and returns the user input
     * @return inp; User Input - String.
     */
    public String getInput(){
        System.out.print("Input: ");
        String inp = input.next();

        if (inp.equals("!exit")){
            exit();
        }
        return inp;
    }

    /**
     * Gets and returns the user input
     * @return inp; User Input - int.
     */
    public int getIntInput(){
        System.out.print("Input: ");
        int inp = 0;

        try{
            inp = input.nextInt(); // Get int Input
        }
        catch (InputMismatchException me){

            System.out.println("ERROR: Invalid input - Please enter a number from 0 to 11.");
        }

        return inp;
    }

    /**
     * Exit the program.
     */
    public void exit(){
        System.out.println("Exiting program. Goodbye!");
        running = false;
        System.exit(0);
    }

    /**
     * Generate a new customer by taking user input for each field (surname, email, and phoneExt)
     * @return a new Customer
     */
    public Customers generateNewCustomer(){
        String surname;
        String email;
        String phoneExt;

        System.out.println("> Please Enter Customer Surname");
        surname = getInput();
        System.out.println("> Please Enter Customer Email");
        email = getInput();
        System.out.println("> Please Enter Customer phoneExt");
        phoneExt = getInput();

        return new Customers(surname, email, phoneExt);

    }

    /**
     * Handles the CLI output and user input
     */
    public void ui(){
        while (running) {
            System.out.println("\nWelcome to Customer CLI. Please type !exit at any time to exit.");

            System.out.println("\nWhat would you like to do? Please enter a number from 0-11");

            System.out.println("\n o Create Operations:");
            System.out.println("   - [0]: Insert Single Customer" +
                               "\n   - [1]: Insert Customer List");

            System.out.println("\n o Read Operations:");
            System.out.println("   - [2]: Get all Customers" +
                    "\n   - [3]: Get Customer ID by Surname" +
                    "\n   - [4]: Get Customer ID by Email" +
                    "\n   - [5]: Get Customer ID by Phone Extension");

            System.out.println("\n o Update Operations:");
            System.out.println("   - [6]: Update Customer Email" +
                    "\n   - [7]: Update Customer Phone Extension" +
                    "\n   - [8]: Replace a Customer");

            System.out.println("\n o Delete Operations:");
            System.out.println("   - [9]: Delete a Customer (via entering Customer credentials) " +
                    "\n   - [10]: Delete a Customer using their ID" +
                    "\n   - [11]: Delete ALL customers\n");

            String actionInput = getInput();

            // These need to be defined as they are referenced multiple times in scope of switch/case
            String surname;
            String email;
            String phoneExt;
            int cid;

            switch (actionInput){
                case "0": // Insert Single Customer
                    CustomerDB.insertCustomer(generateNewCustomer());
                    break;

                case "1": // Insert Customer List

                    System.out.println("How many customers do you need to insert?");
                    int inp = input.nextInt(); // Get int Input

                    for (int i = 1; i <= inp; i++) {
                        System.out.println("> Entering Customer " + i);
                        CustomerDB.insertCustomer(generateNewCustomer());
                    }
                    break;

                case "2": // Get all Customers
                    for (Customers customer: CustomerDB.getCustomers()) {
                        System.out.println(customer);
                    }
                    break;

                case "3": // Get Customer ID by Surname
                    System.out.println("> Note: This will return a list of IDs of Customers with this Surname.");
                    System.out.println("> Please enter customer Surname:");
                    surname = getInput();
                    for (Object o: CustomerDB.getCIDBySurname(surname)) {
                        System.out.println((Integer) o);
                    }
                    break;

                case "4": // Get Customer ID by Email
                    System.out.println("> Note: This will return a list of IDs of Customers with this Email.");
                    System.out.println("> Please enter customer Email:");
                    email = getInput();
                    for (Object o: CustomerDB.getCIDByEmail(email)) {
                        System.out.println((Integer) o);
                    }
                    break;

                case "5": // Get Customer ID by Phone Extension
                    System.out.println("> Note: This will return a list of IDs of Customers with this Phone Extension.");
                    System.out.println("> Please enter customer Phone Extension:");
                    phoneExt = getInput();
                    for (Object o: CustomerDB.getCIDByPhoneExt(phoneExt)) {
                        System.out.println((Integer) o);
                    }
                    break;

                case "6": // Update Customer Email
                    System.out.println("> Hint: Use [2] to see the details of each customer and their ID.");

                    // Get details:
                    System.out.println("> Enter Customer ID:");
                    cid = getIntInput();
                    System.out.println("> Enter new Customer Email:");
                    email = getInput();

                    CustomerDB.updateEmail(cid, email);

                    break;

                case "7": // Update Customer Phone Extension
                    System.out.println("> Hint: Use [2] to see the details of each customer and their ID.");

                    // Get details:
                    System.out.println("> Enter Customer ID:");
                    cid = getIntInput();
                    System.out.println("> Enter new Customer Phone Extension:");
                    phoneExt = getInput();

                    CustomerDB.updatePhoneExt(cid, phoneExt);

                    break;

                case "8": // Replace a Customer
                    System.out.println("> Enter ID of Customer to replace:");
                    cid = getIntInput();

                    // Get new Customer
                    System.out.println("\n> Credentials of new Customer:");
                    Customers newCustomer = generateNewCustomer();

                    // Replace:
                    CustomerDB.replaceCustomer(cid, newCustomer);

                    break;

                case "9": // Delete a Customer (via entering Customer credentials)
                    System.out.println("> Credentials of Customer to delete:");
                    Customers customerToDelete = generateNewCustomer();
                    CustomerDB.deleteCustomer(customerToDelete);
                    break;

                case "10": // Delete a Customer using their ID
                    System.out.println("> ID of Customer to delete:");
                    cid = getIntInput();
                    CustomerDB.deleteCustomerUsingCID(cid);
                    break;

                case("11"): // Delete all customers
                    CustomerDB.deleteAllCustomers();
                    break;

                default: // If incorrect number entered
                    System.out.println("ERROR: Invalid input - Please enter a number from 0 to 11.");
                break;

            }

        }
    }

}
