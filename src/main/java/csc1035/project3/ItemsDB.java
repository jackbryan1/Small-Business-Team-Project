package csc1035.project3;

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

    public static void readSearch(String search) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List Items = session.createQuery("FROM Items where name like '%"+search+"%'").list();
            session.close();
            System.out.format("%-10s%-32s%-16s%-2s", "ID", "NAME", "CATEGORY", "PRICE");
            System.out.println();
            for (Object item : Items) {
                Items itemObj = Items.class.cast(item);
                System.out.format("%-10d%-32s%-16s%.2f", itemObj.getId(), itemObj.getName(), itemObj.getCategory(), itemObj.getSellPrice());
                System.out.println();
            }
            if (Items.size() == 0) {
                System.out.println("No items match the search term.");
            } else if (Items.size() == 1) {
                System.out.println("Only one item matches search term");
                Items select = Items.class.cast(Items.get(0));
                idSearch(select.getId());
            }
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static Items idSearch(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Items item = session.get(Items.class, id);
            session.close();
            System.out.println(item);
            return item;
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public static void update(Items item) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Items item) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        //Items item = new Items("test", "test", true, 13.00, 14, 14.00);
        //ArrayList<Items> test = ItemArray.itemArray();
        //test.add(item);
        //create(test);
        //readSearch("Homemade Str");
        //Items test = idSearch(10);
        //delete(test);
        //System.out.println(list);
    }
}
