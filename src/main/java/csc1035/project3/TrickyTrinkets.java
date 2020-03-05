package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.management.Query;

public class TrickyTrinkets implements Business {

    static Session session;

    public int availableStock(Items item){
        try{
            int id = item.getId();
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query available = (Query) session.createQuery("FROM Items SELECT stock WHERE id = "+id+"");
            session.getTransaction().commit();
            session.close();
            String availableString = available.toString();
            return Integer.parseInt(availableString);
        } catch(HibernateException e){
            if(session != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
        return 0;
    }

    public String printReceipt(Items item, Customers customer){
        Transaction t1 = new Transaction();
        return t1.toString();
    }

    public void updateStock(Items item){}

}
