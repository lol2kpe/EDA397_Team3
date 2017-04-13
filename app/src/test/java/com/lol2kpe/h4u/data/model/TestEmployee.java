package com.lol2kpe.h4u.data.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class TestEmployee {


    @Test
    public void testGetName() throws Exception {
        Employee employee = new Employee()
                .setName("David");
        assertEquals(employee.getName(), "David");
    }
    @Test
    public void testSetName() throws Exception {
        Employee employee = new Employee()
                .setName("David");
        employee.setName("Erik");
        assertEquals(employee.getName(), "Erik");
    }
    @Test
    public void testProvidesService() throws Exception{
        Hospital hospital = new Hospital()
                .addService(Service.SURGEON);
        hospital.providesService(Service.SURGEON);
    }

}