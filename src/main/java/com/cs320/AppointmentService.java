package com.cs320;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * AppointmentService manages Appointment objects using a HashMap, eliminating
 * O(n) list searches and providing O(1) average-time retrieval and update.
 */
public class AppointmentService {

    private static final String CONTEXT = "AppointmentService";

    /** O(1) operations through hashing */
    private final Map<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment) {
        ValidationUtils.requireNonNull(appointment, "Appointment cannot be null");
        ValidationUtils.requireNonEmpty(appointment.getAppointmentId(), "Appointment ID cannot be empty");

        if (appointments.containsKey(appointment.getAppointmentId())) {
            Logger.error(CONTEXT, "Duplicate appointment ID: " + appointment.getAppointmentId());
            throw new IllegalArgumentException("Appointment ID already exists");
        }

        appointments.put(appointment.getAppointmentId(), appointment);
        Logger.info(CONTEXT, "Added appointment ID: " + appointment.getAppointmentId());
    }

    public void deleteAppointment(String appointmentId) {
        Appointment existing = requireAppointmentExists(appointmentId);
        appointments.remove(existing.getAppointmentId());
        Logger.info(CONTEXT, "Deleted appointment ID: " + appointmentId);
    }

    public void updateAppointmentDate(String appointmentId, Date newDate) {
        requireAppointmentExists(appointmentId).setAppointmentDate(newDate);
    }

    public void updateAppointmentDescription(String appointmentId, String newDescription) {
        requireAppointmentExists(appointmentId).setDescription(newDescription);
    }

    public Appointment getAppointment(String appointmentId) {
        ValidationUtils.requireNonEmpty(appointmentId, "Appointment ID cannot be empty");
        return appointments.get(appointmentId);
    }

    private Appointment requireAppointmentExists(String appointmentId) {
        ValidationUtils.requireNonEmpty(appointmentId, "Appointment ID cannot be empty");
        Appointment appt = appointments.get(appointmentId);
        if (appt == null) {
            Logger.error(CONTEXT, "Appointment not found: " + appointmentId);
            throw new IllegalArgumentException("Appointment ID not found");
        }
        return appt;
    }
}

