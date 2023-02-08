package au.com.telecom.controller;

import au.com.telecom.builder.DataProvider;
import au.com.telecom.domain.ActivateRequest;
import au.com.telecom.domain.PhoneNumber;
import au.com.telecom.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerControllerTest {

    private CustomerController customerController;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerService(DataProvider.generateCustomer());
        customerController = new CustomerController(customerService);
    }

    @Test
    public void givenCustomerIdReturnsSuccessResponse() {

        ResponseEntity<List<PhoneNumber>> phoneNumberOfACustomer = customerController.getPhoneNumberOfACustomer(1L);
        assertThat(phoneNumberOfACustomer.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(phoneNumberOfACustomer.getBody()).isNotNull();

    }

    @Test
    public void givenCustomerIdWithNoPhoneRecordsReturns200WithEmptyBody() {

        ResponseEntity<List<PhoneNumber>> phoneNumberOfACustomer = customerController.getPhoneNumberOfACustomer(3L);
        assertThat(phoneNumberOfACustomer.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(phoneNumberOfACustomer.getBody()).isEmpty();

    }

    @Test
    public void givenCustomerIdAndPhoneNumberActivateIsSuccess() {

        ResponseEntity<PhoneNumber> phoneNumberResponseEntity = customerController.activatePhoneNumber(1L, "0467745342", new ActivateRequest(true));
        assertThat(phoneNumberResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(phoneNumberResponseEntity.getBody().isActivated()).isTrue();
    }

    @Test
    public void givenCustomerIdAndPhoneNumberDeActivateIsSuccess() {

        ResponseEntity<PhoneNumber> phoneNumberResponseEntity = customerController.activatePhoneNumber(1L, "0467745342", new ActivateRequest(false));
        assertThat(phoneNumberResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(phoneNumberResponseEntity.getBody().isActivated()).isFalse();
    }

}
