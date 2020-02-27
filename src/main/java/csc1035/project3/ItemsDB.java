package csc1035.project3;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class ItemsDB {

    Session session;

    // Adds item objects to the item database from an arraylist.
    public void create(ArrayList<Item> itemArray) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Item item : itemArray) {
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
            List Items = session.createQuery("FROM Item").list();
            session.close();
        } catch (HibernateException e) {
        if (session != null) session.getTransaction().rollback();
        e.printStackTrace();
    } 

}
