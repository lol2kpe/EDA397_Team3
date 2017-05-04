package com.lol2kpe.h4u.data.model;

/**
 * Created by sam on 2017-05-02.
 */

public enum Symptom {
    FEVER,
    BROKEN_BONE,
    SORE_THROAT,
    COUGH,
    URINARY_DISORDER,
    EAR_DISORDER,
    HEADACHE,
    COLD;
    public final static Symptom[] HOSPITAL_SYMPTOMS = {
            FEVER,
            BROKEN_BONE,
            SORE_THROAT,
            COUGH,
            URINARY_DISORDER,
            EAR_DISORDER
    };
    public final static Symptom[] PHARMACY_SYMPTOMS = {
            FEVER,
            HEADACHE,
            COLD,
            SORE_THROAT
    };
}
