package csc1035.project3;

import java.util.List;

public interface Business {

    int availableStock(Items item);
    /**
     * Check the available stock of an item.
     *
     * @param : Item the item to check stock.
     * @return : Returns the stock available.
     */

    String printReceipt(Items item, Customers customer, Transaction transaction);
    /**
     * Prints a receipt depending on the items bought.
     *
     * @param : Item is the items that have been bought for specified customer.
     * @return : Returns the receipt that has been created.
     */

    void updateStock(Items item);
    /**
     * Updates the amount of stock for an item depending on purchases.
     *
     * @param : Item is the item that needs its stock updating.
     */
}
