package au.com.telecom.service;

import au.com.telecom.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerService {
    private final Set<Customer> customers;

    public CustomerService(Set<Customer> customers) {
        this.customers = customers;
    }

}
