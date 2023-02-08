package au.com.telecom.controller;

import au.com.telecom.domain.PhoneNumber;
import au.com.telecom.service.PhoneNumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phone-number")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbers() {
        Optional<List<PhoneNumber>> phoneNumbers = phoneNumberService.allPhoneNumbers();
        return phoneNumbers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
