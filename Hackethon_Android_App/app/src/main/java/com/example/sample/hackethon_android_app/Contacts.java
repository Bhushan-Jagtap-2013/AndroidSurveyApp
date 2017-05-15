package com.example.sample.hackethon_android_app;

/**
 * Created by Bhushan.Jagtap on 7/22/2016.
 */
public class Contacts {

    public Contacts(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
