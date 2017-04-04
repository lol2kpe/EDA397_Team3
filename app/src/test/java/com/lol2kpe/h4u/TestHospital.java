package com.lol2kpe.h4u;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class TestHospital {


    @Test
    public void testGetName() throws Exception {
        Hospital hospital = new Hospital("Sahlgrenska",5,"Akuten", 12.345000, 97.869440, 10,"6:00 - 23:00","Göteborg 4367", "075-8833865");
        assertEquals(hospital.getName(), "Sahlgrenska");
    }
    @Test
    public void testSetName() throws Exception {
        Hospital hospital = new Hospital("Sahlgrenska",5,"Akuten", 12.345000, 77.869440, 10,"6:00 - 23:00","Göteborg 4367", "075-8833865");
        hospital.setName("Lundby");
        assertEquals(hospital.getName(), "Lundby");
    }
    @Test
    public void testGetLatitude()throws Exception {
        Hospital hospital = new Hospital("Sahlgrenska",5,"Akuten", 12.345000, 97.869440, 10,"6:00 - 23:00","Göteborg 4367", "075-8833865");
        assertEquals(hospital.getLatitude(), (Double)12.345000);
    }
    @Test
    public void testGetLongitude()throws Exception {
        Hospital hospital = new Hospital("Sahlgrenska",5,"Akuten", 12.345000, 97.869440, 10,"6:00 - 23:00","Göteborg 4367", "075-8833865");
        assertEquals(hospital.getLongitude(), (Double)97.869440);
    }

}
