package com.lol2kpe.h4u.data.model;

import com.lol2kpe.h4u.data.model.Pharmacy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-03.
 */

public class TestPharmacy {

    @Test
    public void testGetName() throws Exception {
        Pharmacy pharmacy = new Pharmacy()
                .setName("Apoteket");
        assertEquals(pharmacy.getName(), "Apoteket");
    }
    @Test
    public void testSetName() throws Exception {
        Pharmacy pharmacy = new Pharmacy()
                .setName("Apoteket");
        pharmacy.setName("Hjärtat");
        assertEquals(pharmacy.getName(), "Hjärtat");
    }
    @Test
    public void testGetLatitude() throws Exception {
        Pharmacy pharmacy = new Pharmacy()
                .setLatitude(23.42534);
        assertEquals(pharmacy.getLatitude(), 23.42534, 0);
    }
    @Test
    public void testGetLongitude() throws Exception {
        Pharmacy pharmacy = new Pharmacy()
                .setLongitude(39.4565804);
        assertEquals(pharmacy.getLongitude(), 39.4565804, 0);
    }
    @Test
    public void testProvidesService() throws Exception{
        Hospital hospital = new Hospital()
                .addService(Service.PHARMACY);
        hospital.providesService(Service.PHARMACY);
    }
}
