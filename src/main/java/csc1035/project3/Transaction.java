package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
public class Transaction {

    private static Session session;

    public static void transaction(ArrayList<Items> items){

        for(int i = 0; i < items.size(); i++){

            items.get(i).setStock(items.get(i).getStock() -1);
            for(int j = i - 1; j >= 0; j--){
                if(items.get(i).getId() == items.get(j).getId()){
                    items.get(i).setStock(items.get(i).getStock() -1);
                }
            }
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("update Items i set i.stock = :newStock where i.id = :itemID");
                query.setParameter("newStock", items.get(i).getStock());
                query.setParameter("itemID", items.get(i).getId());

                query.executeUpdate();
                session.getTransaction().commit();

            } catch (HibernateException e) {
                if (session!=null) session.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }

        }
    }
}
