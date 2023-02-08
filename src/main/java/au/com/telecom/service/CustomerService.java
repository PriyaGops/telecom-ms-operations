package au.com.telecom.service;

import au.com.telecom.domain.Customer;
import au.com.telecom.domain.PhoneNumber;
import au.com.telecom.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final Map<Long, Customer> customers;

    public CustomerService(Map<Long, Customer> customers) {
        this.customers = customers;
    }

    public Optional<List<PhoneNumber>> getPhoneNumbers(Long customerId) {

        return Optional.ofNullable(customers.entrySet().stream().filter(entry -> entry.getKey().equals(customerId))
                .findFirst()
                .map(entry -> entry.getValue().getPhoneNumbers().stream().collect(Collectors.toUnmodifiableList()))
                .orElseThrow(() -> new ResourceNotFoundException("Customer id " + customerId + " not found")));
    }

    public Optional<PhoneNumber> activatePhoneNumber(Long customerId, String phoneNumber) {

        Optional<Map.Entry<Long, Customer>> customerEntry = customers.entrySet().stream().filter(entry -> entry.getKey().equals(customerId))
                .findFirst();

        if(customerEntry.isEmpty()) {
            throw new ResourceNotFoundException("Customer id " + customerId + " not found");
        }
        return customerEntry.map(entry -> entry.getValue().getPhoneNumbers().stream()
                .filter(ph -> ph.getNumber().equals(phoneNumber))
                .peek(ph -> ph.setActivated(true))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Phone number " + phoneNumber + " not found")));
    }

}
