package au.com.telecom.service;

import au.com.telecom.domain.PhoneNumber;
import au.com.telecom.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhoneNumberService {

    private final Map<Long, Set<PhoneNumber>> phoneNumbersMap;

    public PhoneNumberService(Map<Long, Set<PhoneNumber>> phoneNumbersMap) {
        this.phoneNumbersMap = phoneNumbersMap;
    }

    public Optional<List<PhoneNumber>> allPhoneNumbers() {
        return Optional.ofNullable(phoneNumbersMap)
                .map(entry -> entry.values().stream().flatMap(Set::stream)
                .collect(Collectors.toUnmodifiableList()));
    }
}
