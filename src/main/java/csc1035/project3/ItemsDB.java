package csc1035.project3;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class ItemsDB {

    static Session session;

    // Adds item objects to the item database from an arraylist.
    public static void create(ArrayList<Items> itemArray) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Items item : itemArray) {
                session.save(item);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List read() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List Items = session.createQuery("FROM Items").list();
            session.close();
            return Items;
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Items item = new Items("test", "test", true, 13.00, 14, 14.00);
        ArrayList<Items> test = new ArrayList<>();
        test.add(item);
        create(test);
        List list = read();
    }
}
