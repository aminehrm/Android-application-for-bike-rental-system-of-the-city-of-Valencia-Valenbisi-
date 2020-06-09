package com.amine.hrimech.uv.es;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity {
    public ArrayList<String> itemss;
    private ListView reportsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);


        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");

        itemss = new ArrayList<String>();
        reportsListView = findViewById(R.id.listview);
        getData();


        TextView txtUserName = (TextView) findViewById(R.id.textView9);

        txtUserName.setText(email);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPanel.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }

    private void getData() {


        String url = "http://10.0.2.2/valenbisi/getallreports.php";



        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");

                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jo = result.getJSONObject(i);
                        //String id = jo.getString("_id");
                        String station_id = jo.getString("station_id");
                        String name = jo.getString("name");
                       // String description = jo.getString("description");
                       // String status = jo.getString("status");
                        String type = jo.getString("type");


                        String text="\nReport name :"+name+"\nStation number :"+station_id+"\n Type :"+type+"\n";

                        itemss.add(text);

                    }
                    try {

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AdminPanel.this, R.layout.activity_admin_panel_item, R.id.textView, itemss);
                        reportsListView.setAdapter(arrayAdapter);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

}
