package csc1035.project3;

public class ItemsDB {
    public static void create(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }
}
