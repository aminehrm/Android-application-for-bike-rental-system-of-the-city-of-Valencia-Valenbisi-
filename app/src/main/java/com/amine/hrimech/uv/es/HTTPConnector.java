package com.amine.hrimech.uv.es;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HTTPConnector extends AsyncTask<String, Void, ArrayList> {



    @Override
    protected ArrayList<Station> doInBackground(String... params) {
        ArrayList<Station> stations = new ArrayList<>();
        String jsonString;
        JSONArray jsonArray = null;
        JSONObject jsonObject;


        //http://mapas.valencia.es/lanzadera/opendata/Valenbisi/JSON
        //http://www.json-generator.com/api/json/get/cqcAhxTdfm?indent=2
        String url = "http://mapas.valencia.es/lanzadera/opendata/Valenbisi/JSON";

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            con.setRequestProperty("accept", "application/json;");
            con.setRequestProperty("accept-language", "es");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(con.getInputStream(), "UTF-8"));
            int n;
            while ((n = in.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonString = writer.toString();

        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = new JSONArray(jsonObject.get("features").toString());
            jsonArray = new JSONArray(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsonArray != null) {


            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    stations.add(new Station(
                            obj.getJSONObject("properties").getString("name"),
                            obj.getJSONObject("properties").getString("address"),
                            obj.getJSONObject("properties").getInt("number"),
                            obj.getJSONObject("properties").getInt("available"),
                            obj.getJSONObject("properties").getInt("free"),
                            obj.getJSONObject("properties").getInt("total"),
                            obj.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0),
                            obj.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1),
                            obj.getJSONObject("properties").getString("updated_at")
                            ));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return stations;
    }

    @Override
    protected void onPostExecute(ArrayList stations) {

    }




}
/*
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HTTPConnector extends AsyncTask<String, Void, ArrayList> {



    @Override
    protected ArrayList<Station> doInBackground(String... params) {
        ArrayList<Station> stations = new ArrayList<>();
        String jsonString;
        JSONArray jsonArray = null;
        JSONObject jsonObject;


        //mapas.valencia.es/lanzadera/opendata/Valenbisi/JSON
        //http://www.json-generator.com/api/json/get/cqcAhxTdfm?indent=2
        String url = "https://api.jcdecaux.com/vls/v1/stations?contract=valence&apiKey=8afc5447b6fff3a63a2f44da266c23f6524558ce";

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            con.setRequestProperty("accept", "application/json;");
            con.setRequestProperty("accept-language", "es");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(con.getInputStream(), "UTF-8"));
            int n;
            while ((n = in.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonString = writer.toString();

        try {
            jsonObject = new JSONObject(jsonString);
            //jsonArray = new JSONArray(jsonObject.get(0).toString());
            jsonArray = new JSONArray(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsonArray != null) {


            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    stations.add(new Station(
                            obj.getString("name"),
                            obj.getString("address"),
                            obj.getInt("number"),
                            obj.getInt("available_bikes"),
                            obj.getInt("available_bike_stands"),
                            obj.getInt("bike_stands"),
                            55,
                            66));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return stations;
    }

    @Override
    protected void onPostExecute(ArrayList stations) {

    }




}*/
