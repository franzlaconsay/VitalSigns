package com.f11.vitalsigns;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddPerson extends AppCompatActivity {

    Button add;
    Button cancel;
    EditText firstName;
    EditText lastName;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = findViewById(R.id.buttonAddPerson);
        cancel = findViewById(R.id.buttonCancel);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        db = new DBHandler(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPerson.this.finish();
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

    private void addPerson(){
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        if(!fname.equals("") && !lname.equals("")){
            db.addUser(fname, lname);
            Toast toast = Toast.makeText(this, "User successfully added.", Toast.LENGTH_LONG);
            toast.show();
            Intent i = new Intent(AddPerson.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        else{
            Toast toast = Toast.makeText(this, "Fields can't be empty.", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}