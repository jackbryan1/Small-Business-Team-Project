package csc1035.project3;

import java.util.Scanner;

public class CustomerCLI {

    // Instance Variables
    Scanner input = new Scanner(System.in);
    boolean running = true;

    // Constructor
    public CustomerCLI(){
        ui();
    }

    // Methods
    public String getInput(){
        String inp = input.next();

        if (inp.equals("!exit")){
            exit();
        }
        return inp;
    }

    public void exit(){
        System.out.println("Exiting program. Goodbye!");
        running = false;
        System.exit(0);
    }


    public void ui(){

        while (running) {
            System.out.println("\nWelcome to Customer CLI. Please type !exit at any time to exit.");

            System.out.println("\nWhat would you like to do? Please enter 0-10");

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
                    "\n   - [10]: Delete a Customer using their ID");

            String actionInput = getInput();

            switch (actionInput){
                case "0":
                    
                    break;

                case "1":
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;

                case "6":
                    break;

                case "7":
                    break;

                case "8":
                    break;

                case "9":
                    break;

                case "10":
                    break;

                default:
                    System.out.println("ERROR: Invalid input - Please enter a number from 1 to 9.");
                break;

            }

        }
    }

    public static void main(String[] args) {
        // remove this
        CustomerCLI c = new CustomerCLI();
    }





}
