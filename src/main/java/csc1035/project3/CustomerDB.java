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
 *  Delete - deleteCustomer, deleteCustomerUsingCID
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


    public static void main(String[] args) {
        // * Need to move this to seperate class

        deleteAllCustomers(); // Will ask for confirmation before deleting all

        // --- Test Create ---
        Customers c1 = new Customers("Smith", "b9038224@ncl.ac.uk", "12345");
        Customers c2 = new Customers("Test", "abcd@email.com", "01201");

        List<Customers> testCustomers = new ArrayList<>();
        testCustomers.add(c1);
        testCustomers.add(c2);
        insertCustomerList(testCustomers);

        Customers c3 = new Customers("Doe", "empty", "789");
        insertCustomer(c3);

        // --- Test Read ---
        for (Customers customer: getCustomers()){
            System.out.println(customer);
        }
        System.out.println(getCIDByEmail("b9038224@ncl.ac.uk").get(0)); // As get methods return a list of objects, .get(0) is required to get first result.
        System.out.println(getCIDByPhoneExt("01201").get(0));
        System.out.println(getCIDBySurname("Test").get(0));

        Customers c4 = new Customers("Test", "empty", "00000");
        insertCustomer(c4);
        // If we have duplicate customers (we now have 2 customers with surname 'Test') we an do this:
        System.out.println(getCIDBySurname("Test").get(1)); // To get CID of second customer 'Test'

        // --- Test Update ---
        // Integer typecasting is necessary as statement getCIDby(Property).get(x) returns vague Object, and update methods require int cid.
        updateEmail((Integer) getCIDBySurname("Doe").get(0), "doe@test.com");
        updatePhoneExt((Integer) getCIDByPhoneExt("01201").get(0), "45823");
        Customers c5 = new Customers("ReplaceC4", "empty", "33322");
        replaceCustomer((Integer) getCIDByPhoneExt("789").get(0), c5);

        // --- Test Delete ---
        deleteCustomerUsingCID((Integer) getCIDByPhoneExt("33322").get(0));
        deleteCustomer(c2);
        deleteAllCustomers();


    }


}
