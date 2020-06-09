package com.amine.hrimech.uv.es;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
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

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class BackgroundTask extends AsyncTask<String, String, String> {
    Context context;
    BackgroundTask(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String typee=strings[0];
        String updateURL="http://10.0.2.2/valenbisi/modifyreport.php";
        String regURL="http://10.0.2.2/valenbisi/insertreport.php";
        String deleteURL="http://10.0.2.2/valenbisi/deletereport.php";
        String LoginURL="http://10.0.2.2/valenbisi/login.php";
        String registerURL="http://10.0.2.2/valenbisi/register.php";
        String existuserURL="http://10.0.2.2/valenbisi/userexist.php";
        if(typee.equals("reg")){
            String station_id= strings[1];
            String name=strings[2];
            String description= strings[3];
            String status=strings[4];
            String type=strings[5];
            try{
                URL url= new URL(regURL);
                try{
                    HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream= httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String insert_data = URLEncoder.encode("station_id", "UTF-8")+"="+URLEncoder.encode(station_id, "UTF-8")+
                            "&"+URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+
                            "&"+URLEncoder.encode("description", "UTF-8")+"="+URLEncoder.encode(description, "UTF-8")+
                            "&"+URLEncoder.encode("status", "UTF-8")+"="+URLEncoder.encode(status, "UTF-8")+
                            "&"+URLEncoder.encode("type", "UTF-8")+"="+URLEncoder.encode(type, "UTF-8");
                    bufferedWriter.write(insert_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream= httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result="";
                    String line="";
                    StringBuilder stringBuilder= new StringBuilder();
                    while ((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line).append("\n");

                    }
                    result=stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else if(typee.equals("logine")) {
            String user_name = strings[1];
            String pass_word = strings[2];
            try {
                URL url = new URL(LoginURL);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String login_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") +
                            "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass_word, "UTF-8");
                    bufferedWriter.write(login_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("");

                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else if(typee.equals("existuser")) {
            String user_name = strings[1];
            String email = strings[2];
            try {
                URL url = new URL(existuserURL);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String login_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") +
                            "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    bufferedWriter.write(login_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("");

                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(typee.equals("update")) {
            String station_id= strings[1];
            String name=strings[2];
            String description= strings[3];
            String status=strings[4];
            String type=strings[5];
            try {
                URL url = new URL(updateURL);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    String login_data = URLEncoder.encode("station_id", "UTF-8")+"="+URLEncoder.encode(station_id, "UTF-8")+
                            "&"+URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+
                            "&"+URLEncoder.encode("description", "UTF-8")+"="+URLEncoder.encode(description, "UTF-8")+
                            "&"+URLEncoder.encode("status", "UTF-8")+"="+URLEncoder.encode(status, "UTF-8")+
                            "&"+URLEncoder.encode("type", "UTF-8")+"="+URLEncoder.encode(type, "UTF-8");
                    bufferedWriter.write(login_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");

                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                   return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(typee.equals("register")) {
            String username= strings[1];
            String email=strings[2];
            String password= strings[3];
            String account_type="user";

            try {
                URL url = new URL(registerURL);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    String login_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+
                            "&"+URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+
                            "&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+
                            "&"+URLEncoder.encode("account_type", "UTF-8")+"="+URLEncoder.encode(account_type, "UTF-8");
                    bufferedWriter.write(login_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");

                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(typee.equals("remove")) {
            String station_id= strings[1];

            try {
                URL url = new URL(deleteURL);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    String login_data = URLEncoder.encode("station_id", "UTF-8")+"="+URLEncoder.encode(station_id, "UTF-8");
                    bufferedWriter.write(login_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");

                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        //Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        super.onPostExecute(s);
    }

}