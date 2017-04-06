package com.lol2kpe.h4u.data.model;

public class Employee {
    private String name = "";
    private String employeeType = "";
    private Hospital hospital = new Hospital();
    private String phoneNumber = "";

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public Employee setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
        return this;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Employee setHospital(Hospital hospital) {
        this.hospital = hospital;
        return this;
    }
}