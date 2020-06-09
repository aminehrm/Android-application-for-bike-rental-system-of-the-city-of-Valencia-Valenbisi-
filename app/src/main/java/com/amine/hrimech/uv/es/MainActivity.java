package com.amine.hrimech.uv.es;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    public ArrayList<Station> items;
    public ListView list;
    StationAdapter customAdapter;





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listview);
        items = new ArrayList<Station>();

        Intent intent = getIntent();
        final String type = intent.getStringExtra("type");
        final String username = intent.getStringExtra("name");
        final String email = intent.getStringExtra("email");






        try {
            customAdapter = new StationAdapter(getApplicationContext(),items,"distance");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.setAdapter(customAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(MainActivity.this, item_info.class);
                try{
                        Station item = (Station) customAdapter.getItem(position);
                        intent.putExtra("objet",item);
                        intent.putExtra("bikeStationId",position);
                        intent.putExtra("type",type);
                        intent.putExtra("username",username);

                }catch (Exception e)
                    {
                        e.getStackTrace();
                    }

                startActivity(intent);

            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_account:
                if(getIntent().getStringExtra("name")!=null){
                    try {
                        if(getIntent().getStringExtra("type" ).equals("user")){
                            Intent intent1 = new Intent(MainActivity.this, UserPanel.class);
                            intent1.putExtra("username", getIntent().getStringExtra("name"));
                            intent1.putExtra("email", getIntent().getStringExtra("email"));
                            startActivity(intent1);
                        }else{

                            Intent intent = new Intent(MainActivity.this, AdminPanel.class);
                            intent.putExtra("username", getIntent().getStringExtra("name"));
                            intent.putExtra("type", getIntent().getStringExtra("type"));
                            intent.putExtra("email", getIntent().getStringExtra("email"));
                            startActivity(intent);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                list.setAdapter(customAdapter);
                return true;

            case R.id.new_map:
                try {
                    Intent intent = new Intent(MainActivity.this, MapPart.class);
                    customAdapter = new StationAdapter(getApplicationContext(),items,"distance");
                    intent.putExtra("objet",customAdapter.stations);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                list.setAdapter(customAdapter);
                return true;
            case R.id.new_report:
                try {
                    customAdapter = new StationAdapter(getApplicationContext(),items,"distance");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                list.setAdapter(customAdapter);
                return true;
            case R.id.subitem1:
                try {
                    customAdapter = new StationAdapter(getApplicationContext(),items,"distance");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                list.setAdapter(customAdapter);
                return true;
            case R.id.subitem2:
                try {
                    customAdapter = new StationAdapter(getApplicationContext(),items,"bikeavailable");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                list.setAdapter(customAdapter);
                return true;
            case R.id.subitem3:
                try {
                    customAdapter = new StationAdapter(getApplicationContext(),items,"spotavailable");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                list.setAdapter(customAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
