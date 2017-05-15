package com.example.sample.hackethon_android_app;

/**
 * Created by Bhushan.Jagtap on 7/21/2016.
 */


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


public class Take_A_Survey extends Activity {
    EditText ET_NAME;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_a_survey);
        id = getIntent().getExtras().getString("id");

    }
}
