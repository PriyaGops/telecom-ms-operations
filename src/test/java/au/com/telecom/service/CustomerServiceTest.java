package au.com.telecom.service;

import au.com.telecom.builder.DataProvider;
import au.com.telecom.domain.PhoneNumber;
import au.com.telecom.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerService(DataProvider.generateCustomer());
    }

    @Test
    public void givenCustomerIdReturnsOptionalListOfPhoneNumbers() throws Exception {

        Optional<List<PhoneNumber>> phoneNumbers = customerService.getPhoneNumbers(1L);
        assertThat(phoneNumbers.isPresent()).isTrue();
        assertThat(phoneNumbers.get()).isNotNull();
        assertThat(phoneNumbers.get().size()).isEqualTo(2);
        assertThat(phoneNumbers.get().get(0).getCustomerId()).isEqualTo(1L);
    }

    @Test
    public void givenCustomerIdThatHasNoPhoneNumberReturnsEEmptyList() throws Exception {
        Optional<List<PhoneNumber>> phoneNumbers = customerService.getPhoneNumbers(3L);
        assertThat(phoneNumbers.isPresent()).isTrue();
        assertThat(phoneNumbers.get()).isNotNull();
        assertThat(phoneNumbers.get().size()).isEqualTo(0);
    }

    @Test
    public void givenCustomerIdThatDoestExitThrowsException() throws Exception {
        assertThrows(ResourceNotFoundException.class, () -> customerService.getPhoneNumbers(5L));
    }

    @Test
    public void givenCustomerIdAndPhoneNumberActivateANumber() throws Exception {
        Optional<PhoneNumber> phoneNumber = customerService.activatePhoneNumber(1L, "0467 745 342");
        assertThat(phoneNumber.isPresent()).isTrue();
        assertThat(phoneNumber.get().isActivated()).isTrue();
    }

    @Test
    public void givenCustomerIdAndPhoneNumberWhichIsAlreadyActivatedReturnsSuccess() throws Exception {
        Optional<PhoneNumber> phoneNumber = customerService.activatePhoneNumber(1L, "0456 789 800");
        assertThat(phoneNumber.isPresent()).isTrue();
        assertThat(phoneNumber.get().isActivated()).isTrue();
    }

    @Test
    public void givenWrongCustomerIdAndRightPhoneNumberActivationFails() throws Exception {
        assertThrows(ResourceNotFoundException.class, () -> customerService.activatePhoneNumber(5L, "0456 789 800"));
    }

    @Test
    public void givenRightCustomerIdAndWrongPhoneNumberActivationFails() throws Exception {
        assertThrows(ResourceNotFoundException.class, () -> customerService.activatePhoneNumber(5L, "0456 789 801"));
    }
}
