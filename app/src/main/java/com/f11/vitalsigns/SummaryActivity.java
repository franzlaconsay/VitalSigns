package com.f11.vitalsigns;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SummaryActivity extends AppCompatActivity {

    TextView tname;
    TextView tbp;
    TextView ttemperature;
    TextView tpulseRate;
    TextView tspo2;
    Intent i;
    Button done;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tname = findViewById(R.id.textViewName);
        tbp = findViewById(R.id.textViewBp);
        ttemperature = findViewById(R.id.textViewTemperature);
        tpulseRate = findViewById(R.id.textViewPulseRate);
        tspo2 = findViewById(R.id.textViewSpo2);
        i = getIntent();
        done = findViewById(R.id.buttonDone);
        edit = findViewById(R.id.buttonEdit);

        tname.setText(i.getStringExtra("name"));
        tbp.setText(i.getStringExtra("bp"));
        ttemperature.setText(i.getStringExtra("temperature")+"°C");
        tpulseRate.setText(i.getStringExtra("pulseRate")+" bpm");
        tspo2.setText(i.getStringExtra("spo2")+"%");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SummaryActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SummaryActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}