package com.cs320;

/**
 * Simple structured logger for consistent output.
 */
public final class Logger {

    private Logger() {
        // Prevent instantiation
    }

    public static void info(String context, String message) {
        System.out.println("[INFO] [" + context + "] " + message);
    }

    public static void error(String context, String message) {
        System.err.println("[ERROR] [" + context + "] " + message);
    }
}
