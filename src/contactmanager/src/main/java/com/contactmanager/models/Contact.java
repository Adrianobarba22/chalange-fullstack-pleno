package com.contactmanager.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();

    public void addPhoneNumber(String number) {
        if (phoneNumbers.size() >= 4) {
            throw new IllegalStateException("A contact cannot have more than 4 phone numbers");
        }
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(number);
        phoneNumber.setContact(this);
        phoneNumbers.add(phoneNumber);
    }

    public void setPhoneNumbersFromList(Set<String> numbers) {
        if (numbers.size() > 4) {
            throw new IllegalArgumentException("A contact cannot have more than 4 phone numbers");
        }
        this.phoneNumbers.clear();
        for (String number : numbers) {
            addPhoneNumber(number);
        }
    }
}
