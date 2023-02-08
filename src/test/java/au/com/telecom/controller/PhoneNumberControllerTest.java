package au.com.telecom.controller;

import au.com.telecom.builder.DataProvider;
import au.com.telecom.domain.PhoneNumber;
import au.com.telecom.service.PhoneNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneNumberControllerTest {

    private PhoneNumberController phoneNumberController;

    private PhoneNumberService phoneNumberService;

    @Test
    public void returnsSuccessResponse() {
        this.phoneNumberService = new PhoneNumberService(DataProvider.phoneNumbers());
        this.phoneNumberController = new PhoneNumberController(phoneNumberService);

        ResponseEntity<List<PhoneNumber>> allPhoneNumbersResponseEntity = phoneNumberController.getAllPhoneNumbers();

        assertThat(allPhoneNumbersResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allPhoneNumbersResponseEntity.getBody().size()).isEqualTo(3);
    }

    @Test
    public void returnsNoContentResponse() {
        this.phoneNumberService = new PhoneNumberService(null);
        this.phoneNumberController = new PhoneNumberController(phoneNumberService);

        ResponseEntity<List<PhoneNumber>> allPhoneNumbersResponseEntity = phoneNumberController.getAllPhoneNumbers();
        assertThat(allPhoneNumbersResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}
