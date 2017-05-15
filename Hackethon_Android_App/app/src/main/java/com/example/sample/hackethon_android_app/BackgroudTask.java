package com.example.sample.hackethon_android_app;

import android.content.Context;
import android.os.AsyncTask;
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

/**
 * Created by Bhushan.Jagtap on 7/20/2016.
 */
public class BackgroudTask extends AsyncTask<String, Void, String> {
    Context ctx;
    String vote, like, priv, commnt, tag, id;

    BackgroudTask(Context ctx) {
        this.ctx = ctx;
    }


    @Override
    protected String doInBackground(String... params) {

        String insert_servey = "http://10.0.3.2/hackathon/insertsurvey.php";
        String insert_commnet = "http://10.0.3.2/hackathon/addcomment.php";
        String method = params[0];
        if (method.equals("insertsurvey")) {
            String tl = params[1];
            String tx = params[2];
            String u = params[3];
            try {
                URL url = new URL(insert_servey);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(tl, "UTF-8") + "&" +
                        URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(tx, "UTF-8") + "&" +
                        URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(u, "UTF-8");

                bf.write(data);
                bf.flush();
                bf.close();
                os.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("insertCommnet")) {

            vote = params[1];
            like = params[2];
            priv = params[3];
            commnt = params[4];
            tag = params[5];
            id = params[6];

            try {
                URL url = new URL(insert_commnet);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("vote", "UTF-8") + "=" + URLEncoder.encode(vote, "UTF-8") + "&" +
                        URLEncoder.encode("like", "UTF-8") + "=" + URLEncoder.encode(like, "UTF-8") + "&" +
                        URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("priv", "UTF-8") + "=" + URLEncoder.encode(priv, "UTF-8") + "&" +
                        URLEncoder.encode("commnt", "UTF-8") + "=" + URLEncoder.encode(commnt, "UTF-8") + "&" +
                        URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(tag, "UTF-8") + "&" +
                        URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(MainActivity.name, "UTF-8");

                //System.out.print(data);
                bf.write(data);
                bf.flush();
                bf.close();
                os.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Unsuccessful to send Request!!";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }
}
