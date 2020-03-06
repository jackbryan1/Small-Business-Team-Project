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

    public static void receipt(HashMap<Items, Integer> items, float price, float change){
        System.out.format("+---------------------------------------------+" + System.lineSeparator());
        System.out.format("|Items Purchased:                             |" + System.lineSeparator());
        System.out.format("|                                             |" + System.lineSeparator());
        System.out.format("+------------------------+---------+----------+" + System.lineSeparator());
        System.out.format("|          Name          |  Price  | Quantity |" + System.lineSeparator());
        System.out.format("+------------------------+---------+----------+" + System.lineSeparator());
        for( Items item: items.keySet()){
            System.out.format("%1s%25s%1s%9s%1s%10s%1s%n","|", item.getName(),"|",item.getSellPrice(),"|",items.get(item),"|");
        }
        System.out.format("+------------------------+---------+----------+" + System.lineSeparator());
        System.out.format("%15s%5s%28s%n", "|Total Price: £", price, "|");
        System.out.format("%14s%5s%29s%n", "|Total Paid: £", (price+change), "|");
        System.out.format("%16s%5s%27s%n", "|Total Change: £", change, "|");
        System.out.format("+---------------------------------------------+" + System.lineSeparator());
    }
}
