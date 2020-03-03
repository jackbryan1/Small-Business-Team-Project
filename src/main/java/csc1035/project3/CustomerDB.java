package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class CustomerDB {
	
	// make private and use static?
    static Session session;

    // Create --------------------------------------------------------------------------------------------------------
    public static void insertCustomerList(List<Customers> customers){
        /**
         * Take in an ArrayList of Customers and add to Database
         * @param ArrayList of Customers
         *
         */

        try {
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

    public static void insertCustomer(Customers customer){
        /**
         * Inserts a single given customer to the database
         * @Param Customers object
         */
        try {
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
    public static List<Customers> getCustomers(){
        /** Return all customers in the database
         * @return List of Customers in toString format
         */
        List customers = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

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


    public static List getCIDBySurname(String surname){
        /**
         * Get cid using surname
         * @return an Integer list of all cid where surname is the given surname
         */
        List<Object> toReturn = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("select cid from Customers c where surname = :inputSurname");
            query.setParameter("inputSurname", surname);
            List results = query.getResultList();
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

    public static List getCIDByEmail(String email){
        /**
         * Get cid using email
         * @return an Integer list of all cid where email is the given email
         */
        List<Object> toReturn = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("select cid from Customers c where email = :inputEmail");
            query.setParameter("inputEmail", email);
            List results = query.getResultList();
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

    public static List getCIDByPhoneExt(String phoneExt){
        /**
         * Get cid using phoneExt
         * @return an Integer list of all cid where phoneExt is the given phoneExt
         */
        List<Object> toReturn = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("select cid from Customers c where phoneExt = :inputPhoneExt");
            query.setParameter("inputPhoneExt", phoneExt);
            List results = query.getResultList();
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
    public static void updateEmail(int cid, String newEmail){
        /**
         * Update a customer email using their cid
         * @param id of customer to update and the new email
         */
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("update Customers c set c.email = :newEmail where cid = :customerID");
            query.setParameter("newEmail", newEmail);
            query.setParameter("customerID", cid);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updatePhoneExt(int cid, String newPhoneExt){
        /**
         * Update a customer phoneExt using their cid
         * @param id of customer to update and the new phoneExt
         */
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("update Customers c set c.phoneExt = :newPhoneExt where cid = :customerID");
            query.setParameter("newPhoneExt", newPhoneExt);
            query.setParameter("customerID", cid);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void replaceCustomer(int oldCID, Customers newCustomer){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("update Customers c " +
                                                "set surname = :newCustomerSurname, email = :newCustomerEmail, phoneExt = :newCustomerPhoneExt " +
                                                "where cid = :cidToUpdate");
            query.setParameter("newCustomerSurname", newCustomer.getSurname());
            query.setParameter("newCustomerEmail", newCustomer.getEmail());
            query.setParameter("newCustomerPhoneExt", newCustomer.getPhoneExt());
            query.setParameter("cidToUpdate", oldCID);
            query.executeUpdate();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void main(String[] args) {

        Customers c1 = new Customers("Smith", "b9038224@ncl.ac.uk", "12345");
        Customers c2 = new Customers("Test", "abcd@email.com", "01201");

        List<Customers> testCustomers = new ArrayList<>();
        testCustomers.add(c1);
        testCustomers.add(c2);

        Customers c3 = new Customers("Smith", "empty", "01010");
        //insertCustomer(c3);

        //insertCustomerList(testCustomers);

        //System.out.println(getCIDBySurname("Smith"));
        //System.out.println(getCIDByPhoneExt("12345"));
        //System.out.println(getCIDByEmail("b9038224@ncl.ac.uk"));
        //System.out.println(objectToIntList(getCIDBySurname("Smith")).getClass());
        //updateEmail(getCIDBySurname("Smith").get(0), "newemail");
        //updatePhoneExt(9, "11111");


        replaceCustomer(9, c3);




    }


}
