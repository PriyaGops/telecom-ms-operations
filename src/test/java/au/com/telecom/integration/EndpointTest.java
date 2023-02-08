package au.com.telecom.integration;

import au.com.telecom.App;
import au.com.telecom.domain.ActivateRequest;
import au.com.telecom.exception.ApiError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class EndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnAllPhoneNumbers() {

        ResponseEntity<String> response = restTemplate.getForEntity("/phone-number/all", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());

    }

    @Test
    public void shouldReturnPhoneNumbersOfACustomer() {
        ResponseEntity<String> response = restTemplate.getForEntity("/customer/1/phone-numbers", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturn404WhenCustomerIdIsNotFound() throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity("/customer/100/phone-numbers", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), "Customer id 100 not found");
        String expectedResponse = mapper.writeValueAsString(apiError);

        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void givenRightCustomerIdAndPhoneNumberShouldReturn200() throws JsonProcessingException {
        ActivateRequest activateRequest = new ActivateRequest(true);
        HttpEntity<String> stringHttpEntity = getStringHttpEntity(activateRequest);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/customer/1/0456787641/activate"), HttpMethod.PUT, stringHttpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String expectedResponseBody = "{\"activated\":true,\"customerId\":1,\"phoneNumber\":\"0456787641\"}";
        assertThat(response.getBody()).isEqualTo(expectedResponseBody);
    }

    @Test
    public void givenRightCustomerIdAndWrongPhoneNumberShouldReturn404() throws JsonProcessingException {
        ActivateRequest activateRequest = new ActivateRequest(true);
        HttpEntity<String> stringHttpEntity = getStringHttpEntity(activateRequest);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/customer/1/0456787646/activate"), HttpMethod.PUT, stringHttpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private HttpEntity<String> getStringHttpEntity(Object object) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonActivationRequest = mapper.writeValueAsString(object);
        return new HttpEntity<>(jsonActivationRequest, headers);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
