package com.myorganization.HMS.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException () {
        super ("Doctor Not Found");
    }
    public DoctorNotFoundException(String m) {
        super(m);
    }
}
