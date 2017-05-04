package com.lol2kpe.h4u.data.generator;

import com.google.android.gms.maps.model.LatLng;
import com.lol2kpe.h4u.data.generator.config.HospitalConfig;
import com.lol2kpe.h4u.data.generator.config.PharmacyConfig;
import com.lol2kpe.h4u.data.generator.config.PlaceConfig;
import com.lol2kpe.h4u.data.model.Hospital;
import com.lol2kpe.h4u.data.model.Pharmacy;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.data.model.Symptom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import xyz.samhal.openinghours.OpeningHours;

/**
 * Created by sam on 2017-04-18.
 */

public class DataGenerator implements Iterable<Place>{
    private double userLongitude = 0;
    private double userLatitude = 0;
    private int numberOfElements = 20;
    private double radius = 1;
    private HospitalConfig hospitalConfig = new HospitalConfig();
    private PharmacyConfig pharmacyConfig = new PharmacyConfig();
    private Random randomGenerator = new Random();

    public DataGenerator() {
    }

    public DataGenerator setPosition(double latitude, double longitude) {
        this.userLatitude = latitude;
        this.userLongitude = longitude;
        return this;
    }

    public DataGenerator setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public DataGenerator setRadius(Double radius) {
        this.radius = radius;
        return this;
    }


    @Override
    public Iterator iterator() {
        return new DataGeneratorIterator();
    }

    private Place generateHospital(){
        Hospital hospital = new Hospital()
                .setAddress(generateAddress())
                .setHospitalType("General")
                .setName(generateHospitalName())
                .setSymptoms(generateHospitalSymptoms())
                .setOpeningHours(generateOpeningHours())
                .setRating(generateRating())
                .setPhoneNumber(generatePhoneNumber());
        LatLng pos = generatePosition();
        hospital.setLatitude(pos.latitude).setLongitude(pos.longitude);
        return hospital;
    }

    private String generatePhoneNumber() {
        String phoneNumber = "+467";
        for(int i = 0; i < 8; i++){
            phoneNumber += this.randomGenerator.nextInt(10);
        }
        return phoneNumber;
    }

    private String generateHospitalName() {
        return generateName(this.hospitalConfig);
    }

    private int generateRating() {
        return this.randomGenerator.nextInt(6);
    }

    private OpeningHours generateOpeningHours() {
        /**
         TODO
        int latest = 24;
        int openingHour = this.randomGenerator.nextInt(latest);
        int closingHour = this.randomGenerator.nextInt(latest - openingHour) + openingHour;
        **/
        return new OpeningHours();
    }

    private String generateAddress() {
        return "Hittepågatan " + this.randomGenerator.nextInt(this.numberOfElements);
    }

    private Place generatePharmacy(){
        Pharmacy pharmacy = new Pharmacy()
                .setAddress(generateAddress())
                .setName(generatePharmacyName())
                .setOpeningHours(generateOpeningHours())
                .setRating(generateRating())
                .setSymptoms(generatePharmacySymptoms())
                .setPhoneNumber(generatePhoneNumber());
        LatLng pos = generatePosition();
        pharmacy.setLatitude(pos.latitude).setLongitude(pos.longitude);
        return pharmacy;
    }

    private Set<Symptom> generatePharmacySymptoms() {
        Set<Symptom> generatedSymptoms = new HashSet<>();
        Symptom[] symptoms = Symptom.PHARMACY_SYMPTOMS;
        for(int i = 0; i < 3; i++) {
            generatedSymptoms.add(symptoms[this.randomGenerator.nextInt(symptoms.length)]);
        }
        return generatedSymptoms;
    }

    private Set<Symptom> generateHospitalSymptoms() {
        Set<Symptom> generatedSymptoms = new HashSet<>();
        Symptom[] symptoms = Symptom.HOSPITAL_SYMPTOMS;
        for(int i = 0; i < 3; i++) {
            generatedSymptoms.add(symptoms[this.randomGenerator.nextInt(symptoms.length)]);
        }
        return generatedSymptoms;
    }

    private String generatePharmacyName() {
        return generateName(this.pharmacyConfig);
    }

    private String generateName(PlaceConfig config) {
        return config.getName(this.randomGenerator.nextInt(config.getNumberOfNames()));
    }

    private LatLng generatePosition(){
        double radiusInDegrees = (this.radius * 1000) / 111320f;

        // Get a random distance and a random angle.
        double u = this.randomGenerator.nextDouble();
        double v = this.randomGenerator.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        // Get the x and y delta values.
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Compensate the x value.
        double new_x = x / Math.cos(Math.toRadians(this.userLatitude));

        double foundLatitude;
        double foundLongitude;

        foundLatitude = this.userLatitude + y;
        foundLongitude = this.userLongitude + new_x;


        return new LatLng(foundLatitude, foundLongitude);
    }

    private class DataGeneratorIterator implements Iterator<Place>{
        private int generatedElements = 0;
        private boolean chooseHospital = true;
        @Override
        public boolean hasNext() {
            return this.generatedElements < numberOfElements;
        }

        @Override
        public Place next() {
            if(hasNext()){
                if(this.chooseHospital){
                    this.chooseHospital = false;
                    this.generatedElements++;
                    return generateHospital();
                } else {
                    this.chooseHospital = true;
                    this.generatedElements++;
                    return generatePharmacy();
                }
            } else {
                return null;
            }
        }
    }
}
