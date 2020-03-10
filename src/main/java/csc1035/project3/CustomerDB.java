package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Provides a number of CRUD operations for interacting with Customers table.
 * @author b9038224
 *  Create - insertCustomer, insertCustomerList
 *  Read - getCustomers, getCIDBySurname, getCIDByEmail, getCIDByPhoneExt
 *  Update - updateEmail, updatePhoneExt, replaceCustomer
 *  Delete - deleteCustomer, deleteCustomerUsingCID, deleteAllCustomers
 */
public class CustomerDB {

    private static Session session;

    // Create --------------------------------------------------------------------------------------------------------

    /**
     * Take in an ArrayList of Customers and add to Database.
     * @param customers - an array list of customers.
     *
     */
    public static void insertCustomerList(List<Customers> customers){

        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Add each customer to DB
            for (Customers customer : customers){
                session.save(customer);
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
     * Inserts a single given customer to the database
     * @param customer - an instance of Customers class
     */
    public static void insertCustomer(Customers customer){

        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Add customer to DB
            session.save(customer);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }


    // Read --------------------------------------------------------------------------------------------------------

    /** Return all customers in the database
     * @return List of Customers in toString format
     */
    public static List<Customers> getCustomers(){

        List customers = new ArrayList();
        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup and Execute Query
            customers = session.createQuery("FROM Customers").list();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
        return customers;
    }

    /**
     * Get cid using surname
     * @return an Integer list of all cid where surname is the given surname
     */
    public static List getCIDBySurname(String surname){

        List<Object> toReturn = null;
        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup and Execute Query
            Query query = session.createQuery("select cid from Customers c where surname = :inputSurname");
            query.setParameter("inputSurname", surname);
            List results = query.getResultList();

            // Commit and set return value
            session.getTransaction().commit();
            toReturn = results;

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return toReturn;
    }

    /**
     * Get cid using email
     * @return an Integer list of all cid where email is the given email
     */
    public static List getCIDByEmail(String email){

        List<Object> toReturn = null;
        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup and Execute Query
            Query query = session.createQuery("select cid from Customers c where email = :inputEmail");
            query.setParameter("inputEmail", email);
            List results = query.getResultList();

            // Commit and set return value
            session.getTransaction().commit();
            toReturn = results;

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return toReturn;
    }

    /**
     * Get cid using phoneExt
     * @return an Integer list of all cid where phoneExt is the given phoneExt
     */
    public static List getCIDByPhoneExt(String phoneExt){

        List<Object> toReturn = null;
        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup and Execute Query
            Query query = session.createQuery("select cid from Customers c where phoneExt = :inputPhoneExt");
            query.setParameter("inputPhoneExt", phoneExt);
            List results = query.getResultList();

            // Commit and set return value
            session.getTransaction().commit();
            toReturn = results;

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return toReturn;
    }


    // Update --------------------------------------------------------------------------------------------------------

    /**
     * Update a customer email using their cid
     * @param cid of customer to update and the new email
     */
    public static void updateEmail(int cid, String newEmail){

        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup Query
            Query query = session.createQuery("update Customers c set c.email = :newEmail where cid = :customerID");
            query.setParameter("newEmail", newEmail);
            query.setParameter("customerID", cid);

            // Execute Query and Commit
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Update a customer phoneExt using their cid
     * @param cid of customer to update and the new phoneExt
     */
    public static void updatePhoneExt(int cid, String newPhoneExt){

        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup Query
            Query query = session.createQuery("update Customers c set c.phoneExt = :newPhoneExt where cid = :customerID");
            query.setParameter("newPhoneExt", newPhoneExt);
            query.setParameter("customerID", cid);

            // Execute Query and Commit
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     *  Takes CID of old customer and replaces it with new Customer
     *
     * @param oldCID - cid of old Customer to replace
     * @param newCustomer - new Customers object
     */
    public static void replaceCustomer(int oldCID, Customers newCustomer){
        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup Query
            Query query = session.createQuery("update Customers c " +
                                                "set surname = :newCustomerSurname, email = :newCustomerEmail, phoneExt = :newCustomerPhoneExt " +
                                                "where cid = :cidToUpdate");
            query.setParameter("newCustomerSurname", newCustomer.getSurname());
            query.setParameter("newCustomerEmail", newCustomer.getEmail());
            query.setParameter("newCustomerPhoneExt", newCustomer.getPhoneExt());
            query.setParameter("cidToUpdate", oldCID);

            // Execute Query and Commit
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    // Delete --------------------------------------------------------------------------------------------------------

    /**
     * Delete a given Customer object from the Database if present.
     * @param customer - Customers object to delete
     */
    public static void deleteCustomer(Customers customer){
        try {


            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup Query
            Query query = session.createQuery("delete from Customers c " +
                    "where surname = :CustomerSurname and email = :CustomerEmail and phoneExt = :CustomerPhoneExt ");
            query.setParameter("CustomerSurname", customer.getSurname());
            query.setParameter("CustomerEmail", customer.getEmail());
            query.setParameter("CustomerPhoneExt", customer.getPhoneExt());

            // Execute Query and Commit
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Delete Customer from Database using their cid (enter manually or use getCIDby(Property).get(x))
     * @param cid - cid of customer to delete
     */
    public static void deleteCustomerUsingCID(int cid){
        try {
            // Setup Transaction
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Setup Query
            Query query = session.createQuery("delete from Customers c where cid = :cidToDelete");
            query.setParameter("cidToDelete", cid);

            // Execute Query and Commit
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Delete all Customers from customer table
     */
    public static void deleteAllCustomers(){

        // Ensure User wants to delete all Customers
        Scanner input = new Scanner(System.in);
        System.out.println("This operation will delete all customers and recovery isn't possible - are you sure you want to continue? y/n");
        String choice = input.next();

        if (choice.toLowerCase().equals("y")){
            try {
                // Setup Transaction
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();

                // Setup Query
                Query query = session.createQuery("delete from Customers c");

                // Execute Query and Commit
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
