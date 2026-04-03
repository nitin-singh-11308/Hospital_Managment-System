package com.myorganization.HMS.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException() {
        super ("Patient Not Found");
    }
    public PatientNotFoundException(String message) {
        super(message);
    }
}
