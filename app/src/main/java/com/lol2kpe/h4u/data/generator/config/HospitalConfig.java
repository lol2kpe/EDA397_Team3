package com.lol2kpe.h4u.data.generator.config;

import com.lol2kpe.h4u.R;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created by sam on 2017-04-18.
 */

public class HospitalConfig extends PlaceConfig {
    // i know this is disgusting
    private static final String[] NAMES = {
            "Blekingesjukhuset",
            "Blekinge Hospital",
            "Avesta vårdcentral Lasarettet",
            "Borlänge sjukhus",
            "Falun lasarett",
            "Ludvika lasarett",
            "Mora lasarett",
            "Bollnäs sjukhus",
            "Gävle Hospital",
            "Sjukhuset i Hudiksvall",
            "Visby lasarett",
            "Halmstad Hospital",
            "Varberg Hospital",
            "Landstingsfastigheter",
            "Örebro University Hospital",
            "Lindesbergs lasarett",
            "köping hospital",
            "Sjukhuset i Arvika",
            "Länssjukhuset Sundsvall-Härnösand"
    };

    public HospitalConfig() {
        super(Arrays.asList(NAMES));
    }
}
