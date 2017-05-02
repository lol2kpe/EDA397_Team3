package com.lol2kpe.h4u.data.model;

import com.lol2kpe.h4u.data.model.Hospital;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-02.
 */

public class TestHospital {


    @Test
    public void testGetName() throws Exception {
        Hospital hospital = new Hospital()
                .setName("Sahlgrenska");
        assertEquals(hospital.getName(), "Sahlgrenska");
    }
    @Test
    public void testSetName() throws Exception {
        Hospital hospital = new Hospital()
                .setName("Sahlgrenska");
        hospital.setName("Lundby");
        assertEquals(hospital.getName(), "Lundby");
    }
    @Test
    public void testGetLatitude() throws Exception {
        Hospital hospital = new Hospital()
                .setLatitude(12.345000);
        assertEquals(hospital.getLatitude(), 12.345000, 0);
    }
    @Test
    public void testGetLongitude() throws Exception {
        Hospital hospital = new Hospital()
                .setLongitude(97.869440);
        assertEquals(hospital.getLongitude(), 97.869440, 0);
    }

}
