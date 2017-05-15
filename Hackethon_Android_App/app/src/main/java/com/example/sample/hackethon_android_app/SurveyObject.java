package com.example.sample.hackethon_android_app;

/**
 * Created by Bhushan.Jagtap on 7/21/2016.
 */
public class SurveyObject {
    private String title, key;

    public SurveyObject(String k, String t) {
        this.setKey(k);
        this.setTitle(t);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
