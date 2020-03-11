package csc1035.project3;

import java.util.ArrayList;

public interface Business {
    /**
     * Takes an arrayList of Items class objects and adds them to the Items database.
     * @param Items An arrayList of Items to be created in the database.
     */
    void addItem(ArrayList<Items> Items);

    /**
     * Checks stock of an item in the Items database.
     * @param id The id of the item to check the stock of.
     * @param options The available id's to choose from as specified by the main method.
     * @return Returns the stock of the item with matching id.
     */
    int checkStock(Integer id, ArrayList options);

    /**
     * Updates the stock of an item in the items database.
     * @param id The id of the item to update the stock of.
     * @param stock The new stock to set.
     * @param options The available id's to choose from as specified by the main method.
     */
    void updateStock(Integer id, Integer stock, ArrayList options);

    /**
     * Deletes an item from the items database.
     * @param id The id of the item to delete.
     * @param options The available id's to choose from as specified by the main method.
     */
    void deleteItem(Integer id, ArrayList options);

    /**
     * Allows for manipulation of the customer database.
     */
    void customerDB();
}