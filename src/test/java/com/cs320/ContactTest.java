package com.cs320;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testValidContactCreation() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("123", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testInvalidContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testInvalidFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", null, "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testSetInvalidPhoneTooShort() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("123");
        });
    }

    @Test
    public void testSetValidAddress() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        contact.setAddress("456 Elm St");
        assertEquals("456 Elm St", contact.getAddress());
    }
}