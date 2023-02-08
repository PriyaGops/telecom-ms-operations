package au.com.telecom.service;

import au.com.telecom.builder.DataProvider;
import au.com.telecom.domain.PhoneNumber;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneNumberServiceTest {

    private PhoneNumberService phoneNumberService;


    @Test
    public void givenPhoneNumbersArePresentInDbReturnsTheList() throws Exception {
        phoneNumberService = new PhoneNumberService(DataProvider.phoneNumbers());

        Optional<List<PhoneNumber>> phoneNumbers = phoneNumberService.allPhoneNumbers();
        assertThat(phoneNumbers.isPresent()).isTrue();
        assertThat(phoneNumbers.get()).isNotNull();
        assertThat(phoneNumbers.get().size()).isEqualTo(3);

    }

    @Test
    public void givenNoPhoneNumbersArePresentInDbReturnsEmptyList() throws Exception {
        phoneNumberService = new PhoneNumberService(null);
        Optional<List<PhoneNumber>> phoneNumbers = phoneNumberService.allPhoneNumbers();
        assertThat(phoneNumbers.isPresent()).isFalse();
    }
}
