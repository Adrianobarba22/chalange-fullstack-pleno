package com.contactmanager.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contactmanager.models.PhoneNumber;
import com.contactmanager.repositories.PhoneNumberRepository;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    // List all phone numbers
    public Iterable<PhoneNumber> listPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    // Get a phone number by ID
    public Optional<PhoneNumber> getPhoneNumberById(Long id) {
        return phoneNumberRepository.findById(id);
    }
}
