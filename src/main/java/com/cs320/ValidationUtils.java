package com.cs320;

/**
 * Utility class for reusable validation helpers.
 */

public final class ValidationUtils {

    private ValidationUtils() {
        // Prevent instantiation
    }

    public static void requireNonNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireNonEmpty(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
