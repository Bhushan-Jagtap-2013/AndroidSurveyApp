package com.example.sample.hackethon_android_app;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.concurrent.ExecutionException;

public class Menu extends Activity implements View.OnClickListener {


    String json_string;
    private String str_tag = "";
    String cmds = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1);
        Intent intent = getIntent();

        Button c = (Button) findViewById(R.id.create);
        c.setOnClickListener(this);

        Button t = (Button) findViewById(R.id.take);
        t.setOnClickListener(this);

        Button s = (Button) findViewById(R.id.search);
        s.setOnClickListener(this);

        Button r = (Button) findViewById(R.id.report);
        r.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent in;
        BackgroundTaskServey b1;

        switch (v.getId()) {
            case R.id.create:
                cmds = "create";
                in = new Intent(this, CreateSurvey.class);
                startActivity(in);
                break;

            case R.id.search:

                cmds = "search";

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Enter Tag :");


                final EditText input = new EditText(this);


                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);


                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        str_tag = input.getText().toString();
                        if (str_tag.equals("")) {
                            Toast.makeText(Menu.this, "Please enter valid tag", Toast.LENGTH_LONG).show();
                        } else {
                            new BackgroundTaskServey().execute();
                        }
                    }
                });
                //   builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                //     @Override
                //   public void onClick(DialogInterface dialog, int which) {
                //     dialog.cancel();
                //}
                //});

                builder.show();
                //Toast.makeText(getApplicationContext(),str_tag, Toast.LENGTH_LONG).show();
                //in= new Intent(this, Search.class);
                //startActivity(in);


                break;


            case R.id.take:

                cmds = "take";
                //in= new Intent(this, Sample.class);
                //startActivity(in);
                new BackgroundTaskServey().execute();

                break;
            case R.id.report:

                cmds = "report";
                //in= new Intent(this, Report.class);
                // startActivity(in);
                new BackgroundTaskServey().execute();
                break;

        }
    }

    class BackgroundTaskServey extends AsyncTask<Void, Void, String> {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = "http://10.0.3.2/hackathon/searchfromtag.php";
        }

        @Override
        protected String doInBackground(Void... params) {


            if (cmds.equals("take") || cmds.equals("report")) {
                json_url = "http://10.0.3.2/hackathon/search.php";
                try {

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
            } else if (cmds.equals("search")) {
                json_url = "http://10.0.3.2/hackathon/searchfromtag.php";
                try {
                    URL url = new URL(json_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(str_tag, "UTF-8");

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

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_string = result;
            // Toast.makeText(getApplicationContext(),json_string, Toast.LENGTH_LONG).show();
            if (json_string == null) {
                Toast.makeText(getApplicationContext(), "Unable to fetch ...", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Menu.this, ListView1AllServey.class);
                intent.putExtra("json_data", json_string);
                intent.putExtra("cmds", cmds);
                startActivity(intent);
            }
        }
    }
}
