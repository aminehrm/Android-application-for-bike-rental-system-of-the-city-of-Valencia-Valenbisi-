package com.amine.hrimech.uv.es;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import uk.me.jstott.jcoord.UTMRef;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdate;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MapPart extends AppCompatActivity {
    private MapView mapView;
    ArrayList<Station> items=new ArrayList<Station>();
    ArrayList<Station> items2=new ArrayList<Station>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_map_part);

        mapView = (MapView) findViewById(R.id.map1);
        mapView.onCreate(savedInstanceState);






        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                GPSTracker gps = new GPSTracker (getApplicationContext());
                double latitude = gps.getLatitude();
                double longitude= gps.getLongitude();
                mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),12.0));

                MarkerOptions optionss=new MarkerOptions();
                optionss.title("Current position");

                IconFactory iconFactory = IconFactory.getInstance(MapPart.this);
                Icon icon = iconFactory.fromResource(R.drawable.ic_mylocationn);
                optionss.icon(icon);
                optionss.position(new LatLng(latitude,longitude));
                mapboxMap.addMarker(optionss);


                items2= (ArrayList<Station>) getIntent().getSerializableExtra("objet2");

                items= (ArrayList<Station>) getIntent().getSerializableExtra("objet");

                if(items2!=null){
                    items=items2;
                }

                for(int i=0;i<items.size();i++){
                    MarkerOptions options=new MarkerOptions();
                    UTMRef utm = new UTMRef(items.get(i).getCoordinate1(),items.get(i).getCoordinate2(), 'N', 30);
                    Location ParadaPoint = new Location("locationA");
                    ParadaPoint.setLatitude(utm.toLatLng().getLat());
                    ParadaPoint.setLongitude(utm.toLatLng().getLng());
                    double latti2=utm.toLatLng().getLat();
                    double longi2=utm.toLatLng().getLng();

                    IconFactory iconFactoryy = IconFactory.getInstance(MapPart.this);
                    Icon icone;
                    if(items.get(i).getAVNumber()>10){
                        icone = iconFactoryy.fromResource(R.drawable.ic_locationgreen);
                    }else if(items.get(i).getAVNumber() >= 5) {
                        icone = iconFactoryy.fromResource(R.drawable.ic_locationyellow);
                    }else{
                        icone = iconFactoryy.fromResource(R.drawable.ic_locationred);
                    }
                    options.icon(icone);

                    String title=items.get(i).getAdress()+"\n"+"Available bikes : "+items.get(i).getAVNumber()+"\n"+"Available spots : "+items.get(i).getFree();

                    options.title(title);
                    options.position(new LatLng(latti2,longi2));
                    mapboxMap.addMarker(options);

                }



                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {



                    }

                });




            }


        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
        mapView.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all:
                if(getIntent().getStringExtra("bool") != null) {
                    ArrayList<Station> iitemss=new ArrayList<Station>();
                    iitemss= (ArrayList<Station>) getIntent().getSerializableExtra("objet");
                    ArrayList<Station> iitemss2=new ArrayList<Station>();
                    iitemss2=filteritems(iitemss,"all");
                    Intent refresh = new Intent(this, MapPart.class);
                    refresh.putExtra("objet2",iitemss2);
                    refresh.putExtra("objet",iitemss);
                    startActivity(refresh);
                    this.finish();
                }


                return true;

            case R.id.green:
                ArrayList<Station> iitemsss=new ArrayList<Station>();
                iitemsss= (ArrayList<Station>) getIntent().getSerializableExtra("objet");
                ArrayList<Station> iitemss22=new ArrayList<Station>();
                iitemss22=filteritems(iitemsss,"green");
                Intent refresh2 = new Intent(this, MapPart.class);
                refresh2.putExtra("objet2",iitemss22);
                refresh2.putExtra("objet",iitemsss);
                refresh2.putExtra("bool","all");
                startActivity(refresh2);
                this.finish();


                return true;
            case R.id.red:
                ArrayList<Station> iitemssss=new ArrayList<Station>();
                iitemssss= (ArrayList<Station>) getIntent().getSerializableExtra("objet");
                ArrayList<Station> iitemss222=new ArrayList<Station>();
                iitemss222=filteritems(iitemssss,"red");
                Intent refresh22 = new Intent(this, MapPart.class);
                refresh22.putExtra("objet2",iitemss222);
                refresh22.putExtra("objet",iitemssss);
                refresh22.putExtra("bool","all");
                startActivity(refresh22);
                this.finish();

                return true;
            case R.id.yellow:
                ArrayList<Station> iitemsssss=new ArrayList<Station>();
                iitemsssss= (ArrayList<Station>) getIntent().getSerializableExtra("objet");
                ArrayList<Station> iitemss2222=new ArrayList<Station>();
                iitemss2222=filteritems(iitemsssss,"yellow");
                Intent refresh222 = new Intent(this, MapPart.class);
                refresh222.putExtra("objet2",iitemss2222);
                refresh222.putExtra("objet",iitemsssss);
                refresh222.putExtra("bool","all");
                startActivity(refresh222);
                this.finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public ArrayList<Station> filteritems(ArrayList<Station> items,String method){
        ArrayList<Station> newitems= new ArrayList<Station>() ;
        if(method.equals("green")){
            for(int i=0;i<items.size();i++){
                if(items.get(i).getAVNumber()>10){
                    newitems.add(items.get(i));
                }
            }
        }
        else if(method.equals("yellow")){
            for(int i=0;i<items.size();i++){
                if(items.get(i).getAVNumber()>=5 && items.get(i).getAVNumber()<=10){
                    newitems.add(items.get(i));
                }
            }
        }
        else if(method.equals("red")){
            for(int i=0;i<items.size();i++){
                if(items.get(i).getAVNumber()<5){
                    newitems.add(items.get(i));
                }
            }
        }else {
            for(int i=0;i<items.size();i++){

                    newitems.add(items.get(i));

            }
        }


        return newitems;

    }

}



