package com.contactmanager.controllers;

import com.contactmanager.models.PhoneNumber;
import com.contactmanager.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/phone-numbers")
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    // List all phone numbers
    @GetMapping
    public ResponseEntity<Iterable<PhoneNumber>> listPhoneNumbers() {
        Iterable<PhoneNumber> phoneNumbers = phoneNumberService.listPhoneNumbers();
        return ResponseEntity.ok(phoneNumbers);
    }

    // Get a phone number by ID
    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumber> getPhoneNumberById(@PathVariable Long id) {
        Optional<PhoneNumber> phoneNumberOpt = phoneNumberService.getPhoneNumberById(id);
        if (phoneNumberOpt.isPresent()) {
            return ResponseEntity.ok(phoneNumberOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
}
