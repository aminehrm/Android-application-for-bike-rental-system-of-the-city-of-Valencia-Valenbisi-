package com.amine.hrimech.uv.es;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import uk.me.jstott.jcoord.UTMRef;

public class item_info extends AppCompatActivity  {

    TextView TextView, TextView2, TextView3, TextView4, TextView5, TextView6, TextView7,TextView8,TextView9;
    public double latti,longi,latti2,longi2;
    public String NameofStation;
    public int station_number;
    private ListView reportsListView;
    public ArrayList<report> itemss;
    public Context context;
    newreport_adapter customAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_info);

        context=this;
        itemss = new ArrayList<report>();


        final Station pe = (Station) getIntent().getSerializableExtra("objet");
        final String type;
        if(getIntent().getStringExtra("type")==null){
            type="user";
        }else{
            type=getIntent().getStringExtra("type");
        }

        TextView = (TextView) findViewById(R.id.textView);
        TextView2 = (TextView) findViewById(R.id.textView2);
        TextView3 = (TextView) findViewById(R.id.textView3);
        TextView4 = (TextView) findViewById(R.id.textView4);
        TextView5 = (TextView) findViewById(R.id.textView5);
        TextView6 = (TextView) findViewById(R.id.textView6);
        TextView7 = (TextView) findViewById(R.id.textView7);
        TextView8 = (TextView) findViewById(R.id.textView8);
        TextView9 = (TextView) findViewById(R.id.reportsTV);

        reportsListView = findViewById(R.id.reportsLV);

        setTitle(pe.getAdress());
        TextView.setText(Integer.toString(pe.getNumber()));
        TextView2.setText(pe.getAdress());
        TextView3.setText(Integer.toString(pe.getTotal()));
        TextView4.setText(Integer.toString(pe.getAVNumber()));
        TextView5.setText(Integer.toString(pe.getFree()));
        TextView8.setText(pe.getLast_update());
        String coor = pe.getCoordinate1() + " , " + pe.getCoordinate2();
        TextView6.setText(coor);
        NameofStation=pe.getName();
        station_number=pe.getNumber();




        UTMRef utm = new UTMRef(pe.getCoordinate1(), pe.getCoordinate2(), 'N', 30);
        Location ParadaPoint = new Location("locationA");
        ParadaPoint.setLatitude(utm.toLatLng().getLat());
        ParadaPoint.setLongitude(utm.toLatLng().getLng());
        latti2=utm.toLatLng().getLat();
        longi2=utm.toLatLng().getLng();

        double distance=pe.getDistance();
        DecimalFormat df = new DecimalFormat("#.##");
        String dist=df.format(distance)+" m";
        TextView7.setText(dist);


            if(getIntent().getStringExtra("type")!=null){
                getData();
                if(type.equals("admin")){
                    reportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            Intent intent = new Intent(item_info.this, Reports_info.class);
                            intent.putExtra("stationId", pe.getNumber());
                            intent.putExtra("name", itemss.get(position).getName());
                            intent.putExtra("status", itemss.get(position).getStatus());
                            intent.putExtra("description", itemss.get(position).getDescription());
                            intent.putExtra("type", itemss.get(position).getType());
                            intent.putExtra("id", Integer.parseInt(((TextView) view.findViewById(R.id.reportId)).getText().toString()));

                            startActivityForResult(intent, 1);
                        }
                    });
                }
            }else{
                TextView9.setText("");
            }






    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_report:
                    if(getIntent().getStringExtra("username")== null){
                        Intent intent = new Intent(item_info.this, Login.class);
                        startActivityForResult(intent, 1);
                        return true;
                    }else{
                        Intent intent = new Intent(item_info.this, Reports_info.class);
                        intent.putExtra("stationId", station_number);
                        intent.putExtra("id", -1);

                        startActivityForResult(intent, 1);
                        return true;
                    }



            case R.id.location:
                showOnMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showOnMap() {
        //Uri gmmIntentUri = Uri.parse("geo:"+latti+","+longi);
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+latti2+","+longi2+"("+NameofStation+")");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            itemss.clear();
            getData();
        }
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    private void getData() {


        int value = station_number;

        String url = "http://10.0.2.2/valenbisi/getreports.php?station_id="+value;

        //itemss.clear();



        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");

                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jo = result.getJSONObject(i);
                        String id = jo.getString("_id");
                        String station_id = jo.getString("station_id");
                        String name = jo.getString("name");
                        String description = jo.getString("description");
                        String status = jo.getString("status");
                        String type = jo.getString("type");



                        itemss.add(new report(Integer. valueOf(id) ,Integer. valueOf(station_id),name,description,status,type));

                    }
                    try {
                        customAdapter = new newreport_adapter(context,itemss);
                        reportsListView.setAdapter(customAdapter);

                    } catch (JSONException e) {
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
