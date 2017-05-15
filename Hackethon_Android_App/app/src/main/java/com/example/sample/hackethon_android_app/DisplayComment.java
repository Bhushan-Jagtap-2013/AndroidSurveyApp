package com.example.sample.hackethon_android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayComment extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String tag, cmt, usr;
    CommentsAdapter commentsAdapter;
    ListView listView;
    String ttl, txt, priv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.displaycommentlayout);
        json_string = getIntent().getExtras().getString("json_data");
        ttl = getIntent().getExtras().getString("title");
        txt = getIntent().getExtras().getString("text");


        TextView t1 = (TextView) findViewById(R.id.title);
        TextView txt1 = (TextView) findViewById(R.id.cmt_txt);


        t1.setText(ttl.toString());
        txt1.setText(txt.toString());

        //Toast.makeText(getApplicationContext(),"IN new " + json_string, Toast.LENGTH_SHORT).show();
        try {
            jsonObject = new JSONObject(json_string);
            listView = (ListView) findViewById(R.id.listview_comment);

            commentsAdapter = new CommentsAdapter(this, R.layout.row_layout_comment);
            listView.setAdapter(commentsAdapter);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            while (count < jsonArray.length()) {

                try {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    tag = jo.getString("tag");
                    cmt = jo.getString("text");
                    usr = jo.getString("user");
                    priv = jo.getString("visibility");
                    if (priv.equals("yes")) {
                        usr = "Anonymous";
                    }
                    Comments comments = new Comments("@" + usr + "" + " " + "#" + tag + " " + cmt);
                    commentsAdapter.add(comments);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
