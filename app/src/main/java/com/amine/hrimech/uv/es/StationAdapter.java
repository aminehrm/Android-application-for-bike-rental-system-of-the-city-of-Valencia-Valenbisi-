package com.amine.hrimech.uv.es;

import uk.me.jstott.jcoord.UTMRef;

import org.json.JSONException;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

public class StationAdapter extends BaseAdapter {
    public static ArrayList<Station> stations;
    private Context context;
    private DBHelper dbHelper;
    private HTTPConnector httpconnector;
    public String methodorder;


    static class ViewHolder {

        TextView address;
        TextView avainum;
        TextView distance;


    }

    public StationAdapter(Context c, ArrayList<Station> stations, String methodorder) throws JSONException {
        httpconnector = new HTTPConnector();
        context = c;
        this.stations = stations;
        dbHelper = new DBHelper(c);
        this.methodorder=methodorder;
        Init();
    }



    public void Init() throws JSONException {
        try {
            stations = httpconnector.execute().get();
            GPSTracker gps = new GPSTracker (context);
            double latitude = gps.getLatitude();
            double longitude= gps.getLongitude();
            double distance;
            int dd;
            for (int i = 0; i < stations.size() ; i++) {
               if(gps.isGPSEnabled == true){

                    Location currentlocation=new Location("CurrentLocation");
                    currentlocation.setLatitude(latitude);
                    currentlocation.setLongitude(longitude);
                    UTMRef utm = new UTMRef(stations.get(i).getCoordinate1(), stations.get(i).getCoordinate2(), 'N', 30);
                    Location ParadaPoint = new Location("locationA");
                    ParadaPoint.setLatitude(utm.toLatLng().getLat());
                    ParadaPoint.setLongitude(utm.toLatLng().getLng());
                    double latti2=utm.toLatLng().getLat();
                    double longi2=utm.toLatLng().getLng();
                    distance=currentlocation.distanceTo(ParadaPoint);
                    dd=(int) distance;

               }else{

                    Toast.makeText(context,"Unble GPS to Trace your location",Toast.LENGTH_SHORT).show();
                    dd=0;
                }

                stations.get(i).setDistance(dd);

            }

           Collections.sort(stations, new StationsComparator());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    class StationsComparator implements Comparator<Station> {
        @Override
        public int compare(Station s1, Station s2) {
            int n1;
            int n2;
            if(methodorder=="distance"){
              n1 = (int) s1.getDistance();
              n2 = (int) s2.getDistance();

            if (n1 > n2) {
                return 1;
            } else if (n2 > n1) {
                return -1;
            }


            }
            else if(methodorder=="bikeavailable"){
                n1 = (int) s1.getAVNumber();
                n2 = (int) s2.getAVNumber();

                if (n1 < n2) {
                    return 1;
                } else if (n2 < n1) {
                    return -1;
                }
            }
            else if(methodorder=="spotavailable"){
                n1 = (int) s1.getFree();
                n2 = (int) s2.getFree();

                if (n1 < n2) {
                    return 1;
                } else if (n2 < n1) {
                    return -1;
                }
            }



            return 0;

        }



    }




    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Object getItem(int position) {
        return stations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return stations.get(position).getNumber();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = null;
        if (v == null) {

            LayoutInflater li = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            v = li.inflate (R.layout.item , null ) ;
            holder = new ViewHolder();

            holder.address = (TextView) v.findViewById(R.id.paradaviewaddress);
            holder.avainum = (TextView) v.findViewById(R.id.paraavai);
            holder.distance = (TextView) v.findViewById(R.id.paraavai2);

            v.setTag(holder);
        } else {

            holder = (ViewHolder) v.getTag();
        }

        Station item = stations.get(position);

       if(position%2==0){
           v.setBackgroundColor(Color.rgb(236, 240, 241));


       }else{
           v.setBackgroundColor(Color.rgb(255, 255, 255));
       }



         holder.address.setText(item.getAdress());
         holder.avainum.setText(Integer.toString(item.getAVNumber())+" Bikes");
         if(item.getDistance()>0) {
             holder.distance.setText(Double.toString(item.getDistance()) + " m");
             holder.distance.setTextColor(Color.BLACK);
         }
         else
             holder.distance.setText("");

         if(item.getAVNumber() >= 10 ){
             //v.setBackgroundColor(Color.GREEN);
             holder.avainum.setTextColor(Color.GREEN);

         }else if(item.getAVNumber() >= 5){
           //  v.setBackgroundColor(Color.rgb(255, 165, 0));
             holder.avainum.setTextColor(Color.rgb(255, 165, 0));
         }else{
             //v.setBackgroundColor(Color.RED);
             holder.avainum.setTextColor(Color.RED);
         }

         return v;

    }




}