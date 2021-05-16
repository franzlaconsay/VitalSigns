package com.f11.vitalsigns;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button finish;
    Button reset;
    Button add;
    Spinner name;
    EditText bpHigh;
    EditText bpLow;
    EditText temperature;
    EditText pulseRate;
    EditText spo2;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finish = findViewById(R.id.buttonFinish);
        reset = findViewById(R.id.buttonReset);
        add = findViewById(R.id.buttonAdd);
        name = findViewById(R.id.spinnerName);
        bpHigh = findViewById(R.id.editTextBpHigh);
        bpLow = findViewById(R.id.editTextBpLow);
        temperature = findViewById(R.id.editTextTemperature);
        pulseRate = findViewById(R.id.editTextPulseRate);
        spo2 = findViewById(R.id.editTextSpo2);
        db = new DBHandler(this);

        List list = new ArrayList();
        list = db.getAllUsers();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        name.setAdapter(adapter);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummary();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddPerson();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to clear all vital signs?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            bpHigh.setText("");
                            bpLow.setText("");
                            temperature.setText("");
                            spo2.setText("");
                            pulseRate.setText("");
                            reset.requestFocus();
                            dialog.cancel();
                            bpHigh.clearFocus();
                            bpLow.clearFocus();
                            temperature.clearFocus();
                            spo2.clearFocus();
                            pulseRate.clearFocus();
                        }
                    });
                builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                AlertDialog alertReset = builder1.create();
                alertReset.show();
                Button buttonbackground = alertReset.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonbackground.setTextColor(Color.DKGRAY);
            }
        });
    }

    private void goToSummary(){
        String iname = name.getSelectedItem().toString();
        String ibpHigh = bpHigh.getText().toString();
        String ibpLow = bpLow.getText().toString();
        String itemperature = temperature.getText().toString();
        String ipulseRate = pulseRate.getText().toString();
        String ispo2 = spo2.getText().toString();
        Intent i = new Intent(MainActivity.this,SummaryActivity.class);
        if(iname.equals("")){
            iname = "Name";
        }
        i.putExtra("name", iname);
        i.putExtra("bp",ibpHigh+"/"+ibpLow);
        i.putExtra("temperature", itemperature);
        i.putExtra("pulseRate", ipulseRate);
        i.putExtra("spo2", ispo2);
        startActivity(i);
    }

    private void goToAddPerson(){
        Intent i = new Intent(MainActivity.this,AddPerson.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Do you want to exit app?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        System.exit(0);
                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertReset = builder1.create();
        alertReset.show();
        Button buttonbackground = alertReset.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonbackground.setTextColor(Color.DKGRAY);
    }
}