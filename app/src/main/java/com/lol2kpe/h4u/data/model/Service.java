package com.lol2kpe.h4u.data.model;

/**
 * Created by sam on 4/6/17.
 */

public enum Service {
    //TODO add more services
    EMERGENCY_CARE("Emergency Care"),
    HEALTH_CARE("Health Care"),
    SURGEON("Surgeon"),
    NURSE("Nurse"),
    DOCTOR("Doctor"),
    SPECIALIST("specialist"),
    PHARMACY("Pharmacy");




    private final String displayName;

    Service(String s) {
        this.displayName = s;
    }
    public String toString(){
        return this.displayName;
    }
}

