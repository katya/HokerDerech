/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Katya.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.myapplication.Katya.example.com", ownerName = "backend.myapplication.Katya.example.com", packagePath = ""))
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name="setForm")
    public MyBean setForm(@Named("formName") String formName){
        MyBean response = new MyBean();
        if(formName.equals("Popcorn")) {
            response.setData("fromName:" + formName +
                    ",group:choose,resultTableTitle:[Time;Temperature],numberOfTabColumns:5");
        }
        else if(formName.equals("Milk & Tea")) {
            response.setData("fromName:" + formName +
                    ",group:choose,resultTableTitle:[Time;Temperature],numberOfTabColumns:5");
        }
        return response;
    }

}
