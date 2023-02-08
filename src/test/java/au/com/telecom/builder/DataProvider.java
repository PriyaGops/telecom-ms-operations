package au.com.telecom.builder;

import au.com.telecom.domain.Customer;
import au.com.telecom.domain.PhoneNumber;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DataProvider {

    public static Map<Long, Customer> generateCustomer() {

        Map<Long, Customer> customerMap = new HashMap<>();
        Customer customer1 = createCustomer(1L, "Adam", "adam@abc.com");
        Customer customer2 = createCustomer(2L, "Peter", "peter@abc.com");
        Customer customer3 = createCustomer(3L, "Sam", "sam@abc.com");
        Customer customer4 = createCustomer(4L, "Bob", "bob@abc.com");

        PhoneNumber phoneNumber11 = createPhoneNumber(11L, "0456 789 800", true);
        phoneNumber11.setCustomerId(1L);

        PhoneNumber phoneNumber12 = createPhoneNumber(12L, "0467 745 342", false);
        phoneNumber12.setCustomerId(1L);

        customer1.addPhoneNumber(phoneNumber11);
        customer1.addPhoneNumber(phoneNumber12);

        PhoneNumber phoneNumber21 = createPhoneNumber(21L, "0467 657 876", false);
        phoneNumber21.setCustomerId(2L);

        customer2.addPhoneNumber(phoneNumber21);

        customerMap.put(1L, customer1);
        customerMap.put(2L, customer2);
        customerMap.put(3L, customer3);
        customerMap.put(4L, customer4);

        return customerMap;

    }

    private static Customer createCustomer(Long id, String name, String email) {
        return new Customer(id, name, email);
    }

    private static PhoneNumber createPhoneNumber(Long id, String number, boolean activated) {
        return new PhoneNumber(id, number, activated);
    }

    public static Map<Long, Set<PhoneNumber>> phoneNumbers() {
        return generateCustomer().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getPhoneNumbers()));
    }
}
