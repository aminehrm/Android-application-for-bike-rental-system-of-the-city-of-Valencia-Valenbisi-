package com.amine.hrimech.uv.es;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

public class Reports_info extends AppCompatActivity {

    Intent intent;
    Spinner status, type;
    EditText title, description;

    public int stationId, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        intent = getIntent();
        stationId = intent.getIntExtra("stationId", 1);
        String name = intent.getStringExtra("name");
        String desc= intent.getStringExtra("description");
        String typ= intent.getStringExtra("type");
        String stat= intent.getStringExtra("status");


        id = intent.getIntExtra("id", -1);

        title = findViewById(R.id.titleET);
        description = findViewById(R.id.descriptionET);
        status = findViewById(R.id.statusSp);
        type = findViewById(R.id.typeSp);


        String[] statuses;
        if(id>0){
            statuses = getResources().getStringArray(R.array.report_statuses);
        }else{
            statuses = getResources().getStringArray(R.array.report_statuses_user);
        }

        String[] types = getResources().getStringArray(R.array.report_types);

        ArrayAdapter<String> statusSAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        statusSAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusSAA);

        ArrayAdapter<String> typeSAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        typeSAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeSAA);

        if (id == -1)
            setTitle("New report");
        else {
            setTitle("Edit " + name);
            title.setText(name);
            description.setText(desc);
            status.setSelection(getIndex(status, stat));
            type.setSelection(getIndex(type, typ));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.report_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_report:
                saveReport();

                return true;
            case R.id.delete_report:
                deleteReport();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteReport() {
        if (id == -1)
            setResult(RESULT_CANCELED);
        else {

            String typee="remove";
            BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
            backgroundTask.execute(typee,Integer.toString(id));
            setResult(RESULT_OK);
            Toast.makeText(this, "successfully removed", Toast.LENGTH_SHORT).show();
        }

        this.finish();
    }

    public void saveReport() {
        if (id == -1) {

            String typee="reg";
            BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
            backgroundTask.execute(typee,Integer.toString(stationId) ,title.getText().toString(),
                    description.getText().toString() , status.getSelectedItem().toString(),type.getSelectedItem().toString());


            Toast.makeText(this, "successfully added", Toast.LENGTH_SHORT).show();

        } else {

            String typee="update";
            BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
            backgroundTask.execute(typee,Integer.toString(id) ,title.getText().toString(),
                    description.getText().toString() , status.getSelectedItem().toString(),type.getSelectedItem().toString());

            Toast.makeText(this, "successfully updated", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK);
        this.finish();
    }


    private int getIndex(Spinner spinner, String str) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(str)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onBackPressed() {
        finish();
    }




}

