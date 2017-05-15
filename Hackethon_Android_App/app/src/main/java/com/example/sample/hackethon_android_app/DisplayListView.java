package com.example.sample.hackethon_android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;

    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        json_string = getIntent().getExtras().getString("json_data");
        //Toast.makeText(getApplicationContext(),json_string, Toast.LENGTH_LONG).show();
        contactAdapter = new ContactAdapter(this, R.layout.row_layout_comment);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            String name;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("text");
                Contacts contacts = new Contacts(name);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
