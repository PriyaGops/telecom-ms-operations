package au.com.telecom.repo;

import au.com.telecom.domain.Customer;
import au.com.telecom.domain.PhoneNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class SeedAppDataConfiguration {

    @Bean
    public Set<Customer> customers() {
        return IntStream.range(0, 20)
                .mapToObj(i -> new Customer(Long.valueOf(i), "Customer" + i, "customer"+i+".xyz@gmail.com"))
                .collect(Collectors.toSet());
    }

    @Bean
    public Set<PhoneNumber> phoneNumbers() {
        Random random = new Random();
        return IntStream.range(0, 3)
                .mapToObj(j -> {
                    int prefix = j % 2 == 0 ? 2 : 4;
                    return new PhoneNumber(Long.valueOf(j), "0" + prefix + random.nextInt(100) + " " + random.nextInt(100)+ " "+random.nextInt(1000), false);
                })
                .collect(Collectors.toSet());
    }

}
