package csc1035.project3;

import java.util.HashMap;

public class Transaction {


    public static float transaction(HashMap<Items, Integer> items){
        float price = 0;
        for( Items item: items.keySet()){
            price += items.get(item) * item.getSellPrice();
            item.setStock(item.getStock() - items.get(item));
            ItemsDB.update(item);
        }
        return price;
    }
}
