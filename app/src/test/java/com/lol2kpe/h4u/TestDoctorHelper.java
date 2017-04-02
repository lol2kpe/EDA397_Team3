package com.lol2kpe.h4u;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class TestDoctorHelper {


    @Test
    public void testGetName() throws Exception {
        DoctorHelper doctorHelper = new DoctorHelper("David","surgeon", 10, "Göteborg 5999", "075-888865","Göteborg Sahlgrenska");
        assertEquals(doctorHelper.getName(), "David");
    }
    @Test
    public void testSetName() throws Exception {
        DoctorHelper doctorHelper = new DoctorHelper("David","surgeon", 10, "Göteborg 5999", "075-888865","Göteborg Sahlgrenska");
        doctorHelper.setName("Erik");
        assertEquals(doctorHelper.getName(), "Erik");
    }

}