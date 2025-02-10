package com.contactmanager.services;

import com.contactmanager.models.Contact;
import com.contactmanager.repositories.ContactRepository;
import com.contactmanager.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    // Create a new contact
    public Contact createContact(Contact contact) {
        // Validate uniqueness of phone numbers
        Set<String> newNumbers = contact.getPhoneNumbers().stream()
                .map(pn -> pn.getNumber())
                .collect(Collectors.toSet());
        for (String number : newNumbers) {
            if (phoneNumberRepository.existsByNumber(number)) {
                throw new IllegalArgumentException("Phone number " + number + " already exists for another contact.");
            }
        }
        return contactRepository.save(contact);
    }

    // List all contacts
    public Iterable<Contact> listContacts() {
        return contactRepository.findAll();
    }

    // Get a contact by ID
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    // Update a contact
    public Contact updateContact(Long id, Contact updatedContact) {
        Optional<Contact> contactOpt = contactRepository.findById(id);
        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            contact.setFirstName(updatedContact.getFirstName());
            contact.setLastName(updatedContact.getLastName());
            contact.setEmail(updatedContact.getEmail());
            // For phone numbers, validate uniqueness if they are changed
            Set<String> updatedNumbers = updatedContact.getPhoneNumbers().stream()
                    .map(pn -> pn.getNumber())
                    .collect(Collectors.toSet());

            // Remove the phone numbers from the current contact from uniqueness check
            Set<String> currentNumbers = contact.getPhoneNumbers().stream()
                    .map(pn -> pn.getNumber())
                    .collect(Collectors.toSet());

            for (String number : updatedNumbers) {
                if (!currentNumbers.contains(number) && phoneNumberRepository.existsByNumber(number)) {
                    throw new IllegalArgumentException("Phone number " + number + " already exists for another contact.");
                }
            }
            // Replace phone numbers completely (you might want to add more nuanced update logic)
            contact.getPhoneNumbers().clear();
            updatedNumbers.forEach(num -> contact.addPhoneNumber(num));
            return contactRepository.save(contact);
        }
        return null;
    }

    // Delete a contact
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
