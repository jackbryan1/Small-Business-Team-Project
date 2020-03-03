package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


public class CustomerDB {
	
	// make private and use static?
    static Session session;

    public static void insertCustomer(List<Customers> customers){
        // Take in an ArrayList of Customers and add to Database
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

    public static List<Customers> getCustomers(){
        List customers = new ArrayList();
        // Take in an ArrayList of Customers and add to Database
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



    public static void main(String[] args) {

        Customers c1 = new Customers("Smith", "b9038224@ncl.ac.uk", "12345");
        Customers c2 = new Customers("Test", "abcd@email.com", "01201");

        List<Customers> testCustomers = new ArrayList<>();
        testCustomers.add(c1);
        testCustomers.add(c2);
        
        System.out.println(getCustomers());
    }


}
