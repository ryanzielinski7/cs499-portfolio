package com.cs320;

import java.util.HashMap;
import java.util.Map;

/**
 * ContactService manages Contact objects using a HashMap for O(1) average-time
 * lookups, updates, and deletion.
 *
 * This enhancement replaces linear list searching with constant-time hashing.
 */
public class ContactService {

    private static final String CONTEXT = "ContactService";

    /** HashMap storage = O(1) average-time operations */
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        ValidationUtils.requireNonNull(contact, "Contact cannot be null");
        ValidationUtils.requireNonEmpty(contact.getContactId(), "Contact ID cannot be empty");

        if (contacts.containsKey(contact.getContactId())) {
            Logger.error(CONTEXT, "Duplicate contact ID: " + contact.getContactId());
            throw new IllegalArgumentException("Contact ID already exists");
        }

        contacts.put(contact.getContactId(), contact);
        Logger.info(CONTEXT, "Added contact ID: " + contact.getContactId());
    }

    public void deleteContact(String contactId) {
        Contact existing = getRequiredContact(contactId);
        contacts.remove(existing.getContactId());
        Logger.info(CONTEXT, "Deleted contact ID: " + contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        getRequiredContact(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getRequiredContact(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        getRequiredContact(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        getRequiredContact(contactId).setAddress(address);
    }

    /** Centralized existence check (O(1)) */
    private Contact getRequiredContact(String contactId) {
        ValidationUtils.requireNonEmpty(contactId, "Contact ID cannot be empty");
        Contact c = contacts.get(contactId);
        if (c == null) {
            Logger.error(CONTEXT, "Contact not found: " + contactId);
            throw new IllegalArgumentException("Contact ID not found");
        }
        return c;
    }
}

