package com.cs320;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ContactServiceTest {

    @Test
    public void testAddNullContactFails() {
        ContactService s = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> s.addContact(null));
    }

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    @Test
    public void testAddContactSuccessfully() {
        Contact contact = new Contact("001", "Alice", "Smith", "1112223333", "100 Park Ave");
        service.addContact(contact);
        // Test indirectly by trying to update a field, proving it exists
        service.updateFirstName("001", "Alicia");
        assertEquals("Alicia", contact.getFirstName());
    }

    @Test
    public void testAddDuplicateContactThrowsError() {
        Contact contact1 = new Contact("001", "Alice", "Smith", "1112223333", "100 Park Ave");
        Contact contact2 = new Contact("001", "Bob", "Jones", "4445556666", "200 Lake Rd");
        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact2);
        });
    }

    @Test
    public void testDeleteContactSuccessfully() {
        Contact contact = new Contact("002", "Charlie", "Brown", "9998887777", "300 River Rd");
        service.addContact(contact);
        service.deleteContact("002");
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("002", "Black");
        });
    }

    @Test
    public void testUpdatePhoneSuccessfully() {
        Contact contact = new Contact("003", "David", "White", "1234567890", "400 Hill St");
        service.addContact(contact);
        service.updatePhone("003", "0987654321");
        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    public void testUpdateWithInvalidIdThrowsError() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("999", "New Address");
        });
    }
}