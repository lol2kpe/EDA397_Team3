package com.lol2kpe.h4u.data.generator.config;

import com.lol2kpe.h4u.R;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Created by sam on 2017-04-18.
 */

public class PharmacyConfig extends PlaceConfig {
    // i know this is disgusting
    private static final String[] NAMES = {
            "Apoteket",
            "Apoteket Hjärtat",
            "Apoteksgruppen",
            "Doc Morris",
            "Kronans droghandel",
            "Vårdapoteket"
    };

    public PharmacyConfig() {
        super(Arrays.asList(NAMES));
    }
}
