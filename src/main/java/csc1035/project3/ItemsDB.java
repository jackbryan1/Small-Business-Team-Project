package csc1035.project3;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class ItemsDB {

    Session session;

    // Adds item objects to the item database from an arraylist.
    public void create(ArrayList<Items> itemArray) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Items item : itemArray) {
                session.save(item);
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
                e.printStackTrace();
            }
        }

    public void read() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List Items = session.createQuery("FROM ITEMS").list();
            session.close();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Items item = new Items("test", true, 13.00, 14, 14.00);

    }
}
