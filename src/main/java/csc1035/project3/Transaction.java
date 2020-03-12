package csc1035.project3;

import java.util.HashMap;

public class Transaction {

    /**
     *
     * @param items A hashmap of items that are involved in the transaction as well as the quantity
     * @return Returns the total price of the items in the transaction
     */
    public static float transaction(HashMap<Items, Integer> items){
        float price = 0; //Variable for price of the transaction.
        for( Items item: items.keySet()){ //Iterates through items in the database.
            if (items.get(item) <= item.getStock()){
                price += items.get(item) * item.getSellPrice(); //sets price to price of an item multiplied by how many are purchased.
                item.setStock(item.getStock() - items.get(item)); //Deducts amount purchased from stock number.
                ItemsDB.update(item); //Updates the item in the database.
            } else if(item.getStock() < items.get(item)){
                price += item.getSellPrice() * item.getStock();
                ItemsDB.update(item);
            } else {
                System.out.println("Item out of stock!!");
            }
        }
        return price; //Returns price of the transaction.
    }

    /**
     *
     * @param items A hashmap of items that are involved in the transaction as well as the quantity
     * @param price The total price of the items in the transaction
     * @param change The change that is to be returned to the customer
     */
    public static void receipt(HashMap<Items, Integer> items, float price, float change){
        System.out.format("+---------------------------------------------+" + System.lineSeparator());
        System.out.format("|Items Purchased:                             |" + System.lineSeparator());
        System.out.format("|                                             |" + System.lineSeparator());
        System.out.format("+------------------------+---------+----------+" + System.lineSeparator());
        System.out.format("|          Name          |  Price  | Quantity |" + System.lineSeparator());
        System.out.format("+------------------------+---------+----------+" + System.lineSeparator());
        for( Items item: items.keySet()){
            System.out.format("%1s%24s%1s%9s%1s%10s%1s%n","|", item.getName(),"|",item.getSellPrice(),"|",items.get(item),"|");
        }
        System.out.format("+------------------------+---------+----------+" + System.lineSeparator());
        System.out.format("%14s%9s%24s%n", "|Total Price: ","£" + price, "|");
        System.out.format("%13s%9s%25s%n", "|Total Paid: ", "£" +(price+change), "|");
        System.out.format("%15s%9s%23s%n", "|Total Change: ", "£" + change, "|");
        System.out.format("+---------------------------------------------+" + System.lineSeparator());
    }
}
