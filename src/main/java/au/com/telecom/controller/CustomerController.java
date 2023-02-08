package au.com.telecom.controller;

import au.com.telecom.domain.ActivateRequest;
import au.com.telecom.domain.PhoneNumber;
import au.com.telecom.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}/phone-numbers")
    public ResponseEntity<List<PhoneNumber>> getPhoneNumberOfACustomer(@PathVariable Long customerId) {
        Optional<List<PhoneNumber>> phoneNumbers = customerService.getPhoneNumbers(customerId);
        return phoneNumbers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping ("/{customerId}/{phoneNumber}/activate")
    public ResponseEntity<PhoneNumber> activatePhoneNumber(@PathVariable Long customerId, @PathVariable String phoneNumber, @RequestBody ActivateRequest activateRequest) {
        Optional<PhoneNumber> phoneNumberOptional = customerService.activatePhoneNumber(customerId, phoneNumber, activateRequest );
        return phoneNumberOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
