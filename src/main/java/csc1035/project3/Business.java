package csc1035.project3;

public interface Business {

    int availableStock(Item item);
    /**
     * Check the available stock of an item.
     *
     * @param : Item the item to check stock.
     * @return : Returns the stock available.
     */

    String createReceipt(Item item);
    /**
     * When a customer purchases a product, a receipt for the purchase will be made.
     *
     * @param : Item the items that have been purchased.
     * @return: Returns a string receipt.
     */

    void updateStock(Item item);
    /**
     * Update stock levels in the database.
     *
     * @param : Item the items that will need updating.
     */
}
