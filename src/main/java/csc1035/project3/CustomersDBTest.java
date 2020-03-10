package csc1035.project3;

import java.util.ArrayList;
import java.util.List;

public class CustomersDBTest {

    public static void main(String[] args) {

        CustomerDB.deleteAllCustomers(); // Will ask for confirmation before deleting all

        // --- Test Create ---
        Customers c1 = new Customers("Smith", "b9038224@ncl.ac.uk", "12345");
        Customers c2 = new Customers("Test", "abcd@email.com", "01201");

        List<Customers> testCustomers = new ArrayList<>();
        testCustomers.add(c1);
        testCustomers.add(c2);
        CustomerDB.insertCustomerList(testCustomers);

        Customers c3 = new Customers("Doe", "empty", "789");
        CustomerDB.insertCustomer(c3);

        // --- Test Read ---
        for (Customers customer: CustomerDB.getCustomers()){
            System.out.println(customer);
        }
        System.out.println(CustomerDB.getCIDByEmail("b9038224@ncl.ac.uk").get(0)); // As get methods return a list of objects, .get(0) is required to get first result.
        System.out.println(CustomerDB.getCIDByPhoneExt("01201").get(0));
        System.out.println(CustomerDB.getCIDBySurname("Test").get(0));

        Customers c4 = new Customers("Test", "empty", "00000");
        CustomerDB.insertCustomer(c4);
        // If we have duplicate customers (we now have 2 customers with surname 'Test') we an do this:
        System.out.println(CustomerDB.getCIDBySurname("Test").get(1)); // To get CID of second customer 'Test'

        // --- Test Update ---
        // Integer typecasting is necessary as statement getCIDby(Property).get(x) returns vague Object, and update methods require int cid.
        CustomerDB.updateEmail((Integer) CustomerDB.getCIDBySurname("Doe").get(0), "doe@test.com");
        CustomerDB.updatePhoneExt((Integer) CustomerDB.getCIDByPhoneExt("01201").get(0), "45823");
        Customers c5 = new Customers("ReplaceC4", "empty", "33322");
        CustomerDB.replaceCustomer((Integer) CustomerDB.getCIDByPhoneExt("789").get(0), c5);

        // --- Test Delete ---
        CustomerDB.deleteCustomerUsingCID((Integer) CustomerDB.getCIDByPhoneExt("33322").get(0));
        CustomerDB.deleteCustomer(c2);
        CustomerDB.deleteAllCustomers();


    }
}
