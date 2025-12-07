package com.cs320;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

public class AppointmentServiceTest {

    @Test
    public void testAddNullAppointmentFails() {
        AppointmentService s = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> s.addAppointment(null));
    }

    private AppointmentService service;
    private Date futureDate;

    @BeforeEach
    public void setup() {
        service = new AppointmentService();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        futureDate = cal.getTime();
    }

    @Test
    public void testAddAppointment() {
        Appointment appt = new Appointment("001", futureDate, "Doctor visit");
        service.addAppointment(appt);
        assertEquals(appt, service.getAppointment("001"));
    }

    @Test
    public void testAddDuplicateAppointmentId() {
        Appointment appt1 = new Appointment("001", futureDate, "Doctor visit");
        Appointment appt2 = new Appointment("001", futureDate, "Dentist visit");
        service.addAppointment(appt1);
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appt2));
    }

    @Test
    public void testDeleteAppointment() {
        Appointment appt = new Appointment("001", futureDate, "Doctor visit");
        service.addAppointment(appt);
        service.deleteAppointment("001");
        assertNull(service.getAppointment("001"));
    }

    @Test
    public void testDeleteNonexistentAppointment() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("999"));
    }
}
