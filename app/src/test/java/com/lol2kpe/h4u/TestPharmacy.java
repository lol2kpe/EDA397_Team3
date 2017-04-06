package com.lol2kpe.h4u;

import com.lol2kpe.h4u.data.model.Pharmacy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidfogelberg on 2017-04-03.
 */

public class TestPharmacy {

    @Test
    public void testGetName() throws Exception {
        Pharmacy pharmacy = new Pharmacy("Apoteket", 5, "medicin", 23.42534, 39.4565804, 10, "8:00-16:00","Nordstan 3323", "073545463" );
        assertEquals(pharmacy.getName(), "Apoteket");
    }
    @Test
    public void testSetName() throws Exception {
        Pharmacy pharmacy = new Pharmacy("Apoteket", 5, "medicin", 23.42534, 39.4565804, 10, "8:00-16:00","Nordstan 3323", "073545463" );
        pharmacy.setName("Hjärtat");
        assertEquals(pharmacy.getName(), "Hjärtat");
    }
    @Test
    public void testGetLatitude() throws Exception {
        Pharmacy pharmacy = new Pharmacy("Apoteket", 5, "medicin", 23.42534, 39.4565804, 10, "8:00-16:00","Nordstan 3323", "073545463" );
        assertEquals(pharmacy.getLatitude(), (Double)23.42534);
    }
    @Test
    public void testGetLongitude() throws Exception {
        Pharmacy pharmacy = new Pharmacy("Apoteket", 5, "medicin", 23.42534, 39.4565804, 10, "8:00-16:00","Nordstan 3323", "073545463" );
        assertEquals(pharmacy.getLongitude(), (Double)39.4565804);
    }
}
