package com.lol2kpe.h4u.data.generator.config;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by sam on 2017-04-18.
 */

public abstract class PlaceConfig {
    protected List<String> names;

    public PlaceConfig(List<String> names) {
        this.names = names;
    }
    public String getName(int i){
        return this.names.get(i);
    }

    public int getNumberOfNames(){
        return this.names.size();
    }
}
