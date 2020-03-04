package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class ItemsDB {

    static Session session;

    /**
     * Takes an arrayList of items and inputs each one into the database. Designed to work over an ArrayList to minimise
     * separate connections to the database when adding items - allowing for bulk uploads.
     *
     * @param itemArray : An ArrayList of Items class objects should be constructed prior to being input here.
     */
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

    /**
     *  Searches the database for items which contain the search term in their name and returns the available choices in
     *  the form of an ArrayList. If only one option matches the search term then idSearch is called immediately and the
     *  Items class object is ultimately returned.
     *
     * @param search : The search term that wants to be found in the Items database.
     * @return : Returns an ArrayList of integers corresponding to the Items ids that match the search term.
     */
    public static ArrayList<Integer> readSearch(String search) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List Items = session.createQuery("FROM Items where name like '%"+search+"%'").list();
            session.close();
            ArrayList options = new ArrayList<Integer>();
            System.out.format("%-10s%-32s%-16s%-2s", "ID", "NAME", "CATEGORY", "PRICE");
            System.out.println();
            for (Object item : Items) {
                Items itemObj = Items.class.cast(item);
                options.add(itemObj.getId());
                System.out.format("%-10d%-32s%-16s%.2f", itemObj.getId(), itemObj.getName(), itemObj.getCategory(),
                        itemObj.getSellPrice());
                System.out.println();
            }
            if (Items.size() == 0) {
                System.out.println("No items match the search term.");
            } else if (Items.size() == 1) {
                System.out.println("Only one item matches search term");
                Items select = Items.class.cast(Items.get(0));
                idSearch(select.getId());
            }
            return options;
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the Items class object from the database matching the id specified.
     *
     * @param id : Primary key of the Items object in the database.
     * @return : Returns the Items object from the database.
     */
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

    /**
     * Takes an Items object and updates it in the database.
     *
     * @param item : A (hopefully) modified Items class object from what is in the database.
     */
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

    /**
     * Allows for deletion of an entry in the database.
     *
     * @param item : Takes an Items class object and removes it, designed to work with idSearch.
     */
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

    // To remove once methods integrated with rest of the code.
    public static void main(String[] args) {
        //Items item = new Items("test", "test", true, 13.00, 14, 14.00);
        //ArrayList<Items> test = ItemArray.itemArray();
        //test.add(item);
        //create(test);
        //Items test = idSearch(10);
        //test.setName("Large Animal Sculpture");
        //update(test);
        //readSearch("");
        //delete(test);
    }
}
