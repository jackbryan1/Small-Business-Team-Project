package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ItemsDB {
    public static void create(Item[] itemArray) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (Item item : itemArray) {
            session.save(item);
        }
        session.getTransaction().commit();
        session.close();
    }
}
