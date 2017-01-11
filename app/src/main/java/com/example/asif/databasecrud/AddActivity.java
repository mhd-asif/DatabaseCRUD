package com.example.asif.databasecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class AddActivity extends AppCompatActivity {

    Button btnAddData;
    EditText etName;
    EditText etAge;
    EditText etDisease;
    private static HashMap<String, String> patientInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final DataBaseHelper dbHelper = new DataBaseHelper(this);

        etName = (EditText) findViewById(R.id.etUpdateName);
        etAge = (EditText) findViewById(R.id.etUpdateAge);
        etDisease = (EditText) findViewById(R.id.etDisease);
        patientInfo = new HashMap<String, String>();

        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AddActivity.this, "Inserting new entry!", Toast.LENGTH_LONG).show();
                patientInfo.put("name", etName.getText().toString());
                patientInfo.put("age", etAge.getText().toString());
                patientInfo.put("disease", etDisease.getText().toString());

                dbHelper.openDataBase();
                dbHelper.insertPatient(patientInfo);

                Intent intentMain = new Intent(AddActivity.this, MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentMain);
                finish();
            }
        });
    }
}
