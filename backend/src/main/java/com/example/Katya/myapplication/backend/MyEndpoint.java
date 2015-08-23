/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Katya.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.repackaged.com.google.api.client.json.Json;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;


import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.myapplication.Katya.example.com", ownerName = "backend.myapplication.Katya.example.com", packagePath = ""))
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    LaboratoryCollection collection = new LaboratoryCollection();


    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name="getForm")
    public MyBean getForm(@Named("formName") String formName){
        MyBean response = new MyBean();

        Laboratory lab = collection.getLaboratoryByName(formName);
        Gson gson = new Gson();
        String templateForm = gson.toJson(lab, Laboratory.class);


        response.setData(templateForm);
        return response;
    }

    @ApiMethod(name="sendResults")
    public MyBean sendResults(@Named("formName") String formName, @Named("groupName") String groupName) {
        MyBean response = new MyBean();
        // add results into server
        response.setData("Results for " + formName + " of group " + groupName + "are received successfully");
        return response;
    }

    @ApiMethod(name="createForm")
    public MyBean createForm(@Named("labForm") String labForm) {
        JsonObject obj = new JsonParser().parse(labForm).getAsJsonObject();
        Gson gson = new Gson();
        Laboratory laboratory = gson.fromJson(obj, Laboratory.class);

        collection.addLaboratoryForm(laboratory);

        MyBean response = new MyBean();
        response.setData("Laboratory  " + laboratory.getName() + " is created");
        return response;
    }



}
