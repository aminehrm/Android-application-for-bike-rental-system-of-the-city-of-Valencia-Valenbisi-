package com.amine.hrimech.uv.es;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import uk.me.jstott.jcoord.UTMRef;

public class newreport_adapter extends BaseAdapter {
    public static ArrayList<report> report;

    private Context context;

    private HTTPConnector httpconnector;
    public String methodorder;


    static class ViewHolder {

        TextView id;
        TextView title;
        ImageView image;


    }

    public newreport_adapter(Context c, ArrayList<report> report) throws JSONException {
        httpconnector = new HTTPConnector();
        context = c;
        this.report = report;

        Init();
    }


    public void Init() throws JSONException {
        try {



        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public int getCount() {
        return report.size();
    }

    @Override
    public Object getItem(int position) {
        return report.get(position);
    }

    @Override
    public long getItemId(int position) {
        return report.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        newreport_adapter.ViewHolder holder = null;
        if (v == null) {

            LayoutInflater li = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            v = li.inflate (R.layout.reportrow , null ) ;
            holder = new newreport_adapter.ViewHolder();


            holder.id = (TextView) v.findViewById(R.id.reportId);
            holder.image =(ImageView)  v.findViewById(R.id.reportDot);
            holder.title = (TextView) v.findViewById(R.id.reportTitle);

            v.setTag(holder);
        } else {
            // If is not null, we re-use it to update it.
            holder = (newreport_adapter.ViewHolder) v.getTag();
        }

        report item = report.get(position);

        holder.id.setText(Integer.toString(item.getId()));
        holder.title.setText(item.getName());
        switch(item.getStatus()) {
            case "Open":
                holder.image.setImageResource(R.drawable.red_dot);
                break;
            case "Processing":
                holder.image.setImageResource(R.drawable.yellow_dot);
                break;

        }



        return v;

    }







}
