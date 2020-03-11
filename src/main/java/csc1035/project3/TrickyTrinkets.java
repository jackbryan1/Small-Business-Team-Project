package csc1035.project3;

import java.util.ArrayList;

public class TrickyTrinkets implements Business{
    @Override
    public void addItem(ArrayList<Items> Items) {
        ItemsDB.create(Items);
        System.out.println("Items added to database.");
    }

    @Override
    public int checkStock(Integer id, ArrayList options) {
        if (options.contains(id)) {
            return ItemsDB.idSearch(id).getStock();
        } else {
            System.out.println("This ID is not an option.");
        }
        return 0;
    }

    @Override
    public void updateStock(Integer id, Integer stock, ArrayList options) {
        if (options.contains(id)) {
            Items i = ItemsDB.idSearch(id);
            i.setStock(stock);
            ItemsDB.update(i);
            System.out.println("Stock updated.");
        } else {
            System.out.println("This ID is not an option.");
        }
    }

    @Override
    public void deleteItem(Integer id) {
        ItemsDB.delete(ItemsDB.idSearch(id));
        System.out.println("Item deleted.");
    }

    @Override
    public void customerDB() {
        CustomerCLI custCLI = new CustomerCLI();
    }
}