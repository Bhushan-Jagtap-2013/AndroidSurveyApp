package com.example.sample.hackethon_android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateSurvey extends AppCompatActivity {
    String v_title, v_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);
    }

    public void onClickSubmit(View v) {
        EditText ttl = (EditText) findViewById(R.id.titleTxt);
        EditText txt = (EditText) findViewById(R.id.Survey_txt);
        v_title = "";
        v_text = "";
        String method = "insertsurvey";
        v_title = ttl.getText().toString();
        v_text = txt.getText().toString();


        if (v_title.equals("") || v_text.equals("")) {
            Toast.makeText(CreateSurvey.this, "Please enter valid data", Toast.LENGTH_LONG).show();
        } else {
            BackgroudTask bt = new BackgroudTask(this);
            bt.execute(method, v_title, v_text, MainActivity.name);
            finish();
        }
    }

    public void parseJSON(View v) {


    }
}
