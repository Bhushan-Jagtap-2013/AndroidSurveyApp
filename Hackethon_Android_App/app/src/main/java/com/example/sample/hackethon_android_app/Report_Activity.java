package com.example.sample.hackethon_android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Report_Activity extends AppCompatActivity {

    JSONObject jsonObject;
    JSONArray jsonArray;
    SurveyObjectAdapter surveyObjectAdapter;
    BarChart lc;
    String json_string, like, v, t, p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_);

        int count = 0, likes = 0, dislike = 0, vote = 0;

        json_string = getIntent().getExtras().getString("json_data");
        t = getIntent().getExtras().getString("title");
        p = getIntent().getExtras().getString("text");

        //Toast.makeText(getApplicationContext(), "Here to create Report " + json_string, Toast.LENGTH_LONG).show();


        TextView t1 = (TextView) findViewById(R.id.report_title);
        TextView t2 = (TextView) findViewById(R.id.report_text);
        TextView t3 = (TextView) findViewById(R.id.textView3);
        TextView t4 = (TextView) findViewById(R.id.textView4);


        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            String key, title;


            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                like = jo.getString("like");
                v = jo.getString("rating");

                if (like.equals("like")) {
                    likes++;
                } else if (like.equals("dislike")) {
                    dislike++;
                }

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // chart code

        lc = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList<>();

        int count0;
        int i, temp;
        for (i = 0; i <= 5; i++) {
            count0 = 0;
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("server_response");
                String key, title;


                count = 0;
                while (count < jsonArray.length()) {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    v = jo.getString("rating");
                    temp = (int) Double.parseDouble(v);
                    if (temp == i) {
                        count0++;
                    }

                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            entries.add(new BarEntry(i, count0));
        }
/*
        entries.add(new BarEntry(0, 18));
        entries.add(new BarEntry(1, 10));
        entries.add(new BarEntry(2, 17));
        entries.add(new BarEntry(3, 20));
        entries.add(new BarEntry(4, 11));
        entries.add(new BarEntry(5, 15));
*/
        BarDataSet lds = new BarDataSet(entries, "No. of users");

        BarData data = new BarData(lds);
        lc.setData(data);
        lc.setDescription("X - Axis : Rating          Y - Axis : # of users ");
//        lc.setScaleEnabled(true);
        lc.setFitBars(true);
        lc.setBottom(0);
        lds.setBarBorderWidth(1);

        t1.setText(t.toString());
        t2.setText(p.toString());

        t3.setText("Likes : " + Integer.toString(likes));
        t4.setText("Dislikes : " + Integer.toString(dislike));
    }
}
