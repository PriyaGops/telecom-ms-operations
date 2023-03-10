package au.com.telecom.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneNumber {

    private Long id;
    private String number;
    private boolean activated;
    private Long customerId;

    public PhoneNumber(Long id, String number, boolean activated) {
        this.id = id;
        this.number = number;
        this.activated = activated;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("phoneNumber")
    public String getNumber() {
        return this.number;
    }

    public boolean isActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
