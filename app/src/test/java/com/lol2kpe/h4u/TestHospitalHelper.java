package com.lol2kpe.h4u;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class TestHospitalHelper {


    @Test
    public void testGetName() throws Exception {
        HospitalHelper hospitalHelper = new HospitalHelper("Sahlgrenska","Akuten", "1234500", "0786944", 10,"Riktigt bra bemötande","6:00 - 23:00","Göteborg 4367", "075-8833865");
        assertEquals(hospitalHelper.getName(), "Sahlgrenska");
    }
    @Test
    public void testSetName() throws Exception {
        HospitalHelper hospitalHelper = new HospitalHelper("Sahlgrenska","Akuten", "1234500", "0786944", 10,"Riktigt bra bemötande","6:00 - 23:00","Göteborg 4367", "075-8833865");
        hospitalHelper.setName("Lundby");
        assertEquals(hospitalHelper.getName(), "Lundby");
    }

}
