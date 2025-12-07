
package com.cs320;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
@SuppressWarnings({"ResultOfMethodCallIgnored", "ResultOfObjectAllocationIgnored"})
public class AppointmentTest {

    private Date futureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return cal.getTime();
    }

    @Test
    public void testValidAppointmentCreation() {
        Appointment appt = new Appointment("123", futureDate(), "Doctor visit");
        assertEquals("123", appt.getAppointmentId());
        assertEquals("Doctor visit", appt.getDescription());
    }

    @Test
    public void testInvalidAppointmentId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, futureDate(), "Checkup");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate(), "Checkup");
        });
    }

    @Test
    public void testInvalidAppointmentDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", null, "Dentist");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", new Date(System.currentTimeMillis() - 86400000), "Dentist");
        });
    }

    @Test
    public void testInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", futureDate(), null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", futureDate(), "A".repeat(51));
        });
    }

    @Test
    public void testSetters() {
        Appointment appt = new Appointment("123", futureDate(), "Checkup");
        appt.setDescription("Updated Description");
        assertEquals("Updated Description", appt.getDescription());
    }
}
