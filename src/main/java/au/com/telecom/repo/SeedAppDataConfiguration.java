package au.com.telecom.repo;

import au.com.telecom.domain.Customer;
import au.com.telecom.domain.PhoneNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class SeedAppDataConfiguration {

    @Bean
    public Map<Long, Customer> customers() {
        Map<Long, Customer> customerMap = IntStream.range(0, 20)
                .mapToObj(i ->
                        new Customer((long) i, "Customer" + i, "customer" + i + ".xyz@gmail.com"))
                .collect(Collectors.toMap(Customer::getId, customer -> customer));
         customerMap.entrySet()
                 .forEach(entry ->
                     generatePhoneNumbers(entry.getKey()).forEach(phoneNumber -> entry.getValue().addPhoneNumber(phoneNumber)));

         return customerMap;
    }
    private Set<PhoneNumber> generatePhoneNumbers(Long customerId) {
        Random random = new Random();
        return IntStream.range(0, 3)
                .mapToObj(j -> {
                    PhoneNumber phoneNumber = new PhoneNumber((long) j, "045678764"+j, false);
                    phoneNumber.setCustomerId(customerId);
                    return phoneNumber;
                })
                .collect(Collectors.toSet());
    }

    @Bean
    public Map<Long, Set<PhoneNumber>> phoneNumbersMap() {
        return customers().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getPhoneNumbers()));
    }
}
