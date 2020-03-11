package csc1035.project3;

import java.util.ArrayList;

public interface Business {
    void addItem(ArrayList<Items> Items);

    int checkStock(Integer id, ArrayList options);

    void updateStock(Integer id, Integer stock, ArrayList options);

    void deleteItem(Integer id);

    void customerDB();
}