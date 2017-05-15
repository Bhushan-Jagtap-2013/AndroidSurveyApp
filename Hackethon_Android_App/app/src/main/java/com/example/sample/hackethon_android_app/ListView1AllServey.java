package com.example.sample.hackethon_android_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ListView1AllServey extends AppCompatActivity {

    String json_string;
    String json_string2;
    String cmd;
    JSONObject jsonObject;
    JSONArray jsonArray;
    SurveyObjectAdapter surveyObjectAdapter;
    ListView listView;
    String id;
    String key, title = "", servey_txt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1_all_servey);
        surveyObjectAdapter = new SurveyObjectAdapter(this, R.layout.row_layout);
        listView = (ListView) findViewById(R.id.listviewservey);

        json_string = getIntent().getExtras().getString("json_data");
        cmd = getIntent().getExtras().getString("cmds");
        listView.setAdapter(surveyObjectAdapter);


        // add listener here
        // -------------------- listenr start -------- /
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                SurveyObject o = (SurveyObject) listView.getItemAtPosition(position);
                //String str= o.toString();//As you are using Default String Adapter
                //SurveyObjectAdapter s = (SurveyObjectAdapter) o;
                //SurveyObject surveyObject = (SurveyObject) s.getItem(position);
                //surveyObject.getKey()


                if (cmd.equals("search")) {


                    //  Toast.makeText(getApplicationContext(),cmd, Toast.LENGTH_LONG).show();

                    int count = 0;
                    JSONObject jo = null;
                    id = o.getKey().toString();
                    while (count < jsonArray.length()) {

                        try {
                            jo = jsonArray.getJSONObject(count);
                            key = jo.getString("id");
                            title = jo.getString("title");
                            servey_txt = jo.getString("text");
                            if (id.equals(key)) {
                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        count++;
                    }

                   /* if(json_string2 == null) {
                        Toast.makeText(getApplicationContext(),"No data", Toast.LENGTH_LONG).show();
                    } else*/
                    //id = o.getKey().toString();
                    new BackgroundTaskCmt1().execute();

                }
                if (cmd.equals("take")) {


                    int count = 0;

                    id = o.getKey().toString();
                    while (count < jsonArray.length()) {
                        JSONObject jo = null;
                        try {
                            jo = jsonArray.getJSONObject(count);
                            key = jo.getString("id");
                            title = jo.getString("title");
                            servey_txt = jo.getString("text");
                            if (id.equals(key)) {
                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        count++;
                    }

                    Intent intent = new Intent(ListView1AllServey.this, InsertFeedback.class);

                    // insert data
                    intent.putExtra("id", o.getKey().toString());
                    intent.putExtra("title", title);
                    intent.putExtra("text", servey_txt);
                    startActivity(intent);


                } else if (cmd.equals("report")) {

                    int count = 0;
                    JSONObject jo = null;
                    id = o.getKey().toString();
                    while (count < jsonArray.length()) {

                        try {
                            jo = jsonArray.getJSONObject(count);
                            key = jo.getString("id");
                            title = jo.getString("title");
                            servey_txt = jo.getString("text");
                            if (id.equals(key)) {
                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        count++;
                    }
                    id = o.getKey().toString();
                    new BackgroundTaskCmt1().execute();
                }
                //Toast.makeText(getApplicationContext(),"BHUSHAN " + o.getKey().toString() + cmd,Toast.LENGTH_SHORT).show();
            }
        });


        // -------------------- listenr start -------- /

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            String key, title;

            int count = 0;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                key = jo.getString("id");
                title = jo.getString("title");
                SurveyObject sur = new SurveyObject(key, title);
                surveyObjectAdapter.add(sur);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    class BackgroundTaskCmt1 extends AsyncTask<Void, Void, String> {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = "http://10.0.3.2/hackathon/searchfromtag.php";
        }

        @Override
        protected String doInBackground(Void... params) {


            json_url = "http://10.0.3.2/hackathon/getcomments.php";
                /*try {

                    URL url = new URL(json_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    while ((JSON_STRING = br.readLine()) != null) {
                        sb.append(JSON_STRING + "\n");
                    }
                    br.close();
                    is.close();
                    httpURLConnection.disconnect();
                    return sb.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return json_url.toString();
           /* } else if (cmds.equals("search")) {
                json_url = "http://10.0.3.2/hackathon/searchfromtag.php"; */
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

                bf.write(data);
                bf.flush();
                bf.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                while ((JSON_STRING = br.readLine()) != null) {
                    sb.append(JSON_STRING + "\n");
                }
                br.close();
                is.close();
                httpURLConnection.disconnect();
                return sb.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            json_string2 = result;
            if (cmd.equals("report")) {
                // Toast.makeText(getApplicationContext(), cmd + json_string2, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ListView1AllServey.this, Report_Activity.class);
                intent.putExtra("json_data", json_string2);
                intent.putExtra("title", title);
                intent.putExtra("text", servey_txt);
                startActivity(intent);
            } else {
                // TextView tv = (TextView) findViewById(R.id.timepass);
                // tv.setText(json_string);
                // Toast.makeText(getApplicationContext(), cmd + json_string2, Toast.LENGTH_LONG).show();
                if (json_string2 == null) {
                    Toast.makeText(getApplicationContext(), "Unable to fetch ...", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(ListView1AllServey.this, DisplayComment.class);
                    intent.putExtra("json_data", json_string2);
                    intent.putExtra("title", title);
                    intent.putExtra("text", servey_txt);
                    //intent.putExtra("cmds", cmds);
                    startActivity(intent);
                }
            }
        }
    }
}
