package csc1035.project3;

import java.util.HashMap;

public class Transaction {


    public static void transaction(HashMap<Items, Integer> items){

        for( Items item: items.keySet()){
            item.setStock(item.getStock() - items.get(item));
            ItemsDB.update(item);
        }

    }
}
