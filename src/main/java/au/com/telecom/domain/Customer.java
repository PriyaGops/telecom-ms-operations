package au.com.telecom.domain;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private Set<PhoneNumber> phoneNumberSet;

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumberSet = new HashSet<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumberSet;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumberSet.add(phoneNumber);
    }

    public void removePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumberSet.remove(phoneNumber);
    }
}
