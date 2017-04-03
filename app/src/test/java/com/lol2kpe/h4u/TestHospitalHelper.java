package com.lol2kpe.h4u;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class TestHospitalHelper {


    @Test
    public void testGetName() throws Exception {
        Hospital hospital = new Hospital("Sahlgrenska",5,"Akuten", "1234500", "0786944", 10,"6:00 - 23:00","Göteborg 4367", "075-8833865");
        assertEquals(hospital.getName(), "Sahlgrenska");
    }
    @Test
    public void testSetName() throws Exception {
        Hospital hospital = new Hospital("Sahlgrenska",5,"Akuten", "1234500", "0786944", 10,"6:00 - 23:00","Göteborg 4367", "075-8833865");
        hospital.setName("Lundby");
        assertEquals(hospital.getName(), "Lundby");
    }

}
