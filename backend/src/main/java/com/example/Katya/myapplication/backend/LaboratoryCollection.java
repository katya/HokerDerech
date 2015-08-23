package com.example.Katya.myapplication.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Katya on 8/18/15.
 */
public class LaboratoryCollection {

    HashMap<String, Laboratory> laboratories = new HashMap<>();

    public LaboratoryCollection(){

    }

    public void addLaboratoryForm (Laboratory labTemplate) {
        laboratories.put(labTemplate.getName(), labTemplate);
    }

    public Laboratory getLaboratoryByName(String name) {
        return laboratories.get(name);
    }


}
