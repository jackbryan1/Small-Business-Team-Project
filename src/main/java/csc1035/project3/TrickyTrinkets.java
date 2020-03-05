package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.management.Query;

public class TrickyTrinkets implements Business {

    public int checkStock(Items item) {
        return item.getStock();
    }

    public String printReceipt(Items item, Customers customer){return null;}

    public void updateStock(Items item){}

}
