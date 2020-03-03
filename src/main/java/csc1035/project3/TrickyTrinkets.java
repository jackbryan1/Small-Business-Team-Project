package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.management.Query;
import java.util.List;


public class TrickyTrinkets implements Business {

    static Session session;

    public int availableStock(Items item){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String available = session.createQuery("SELECT stock FROM Items i WHERE i.name = item").toString();
            session.close();
            return Integer.parseInt(available);
        } catch(HibernateException e){
            if(session != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
        return 0;
    }

    public String printReceipt(Items item, Customers customer, Transaction transaction){return null;}

    public void updateStock(Items item){}

}
