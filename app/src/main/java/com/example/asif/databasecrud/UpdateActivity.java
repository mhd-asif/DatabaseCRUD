package com.example.asif.databasecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    EditText etDisease;
    Button btnUpdate;
    HashMap<String, String> patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intentUpdate = getIntent();
        final String patient_id = intentUpdate.getStringExtra("patient_id");
//        Toast.makeText(UpdateActivity.this,"" + patient_id, Toast.LENGTH_LONG).show();
        patient = new HashMap<String, String>();

        DBAdapter dbAdapter = new DBAdapter(this);
        final ModelPatients patientInfo = dbAdapter.getPatientInfo(patient_id);

        etName = (EditText) findViewById(R.id.etUpdateName);
        etName.setText(patientInfo.getName());

        etAge = (EditText) findViewById(R.id.etUpdateAge);
        etAge.setText(patientInfo.getAge());

        etDisease = (EditText) findViewById(R.id.etUpdateDisease);
        etDisease.setText(patientInfo.getDisease());

        btnUpdate = (Button) findViewById(R.id.btnUpdateData);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dbHelper = new DataBaseHelper(UpdateActivity.this);
                patient.put("patient_id", patient_id);
                patient.put("name", etName.getText().toString());
                patient.put("age", etAge.getText().toString());
                patient.put("disease", etDisease.getText().toString());

                dbHelper.openDataBase();
                dbHelper.updatePatient(patient);

                Intent intentMain = new Intent(UpdateActivity.this, MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentMain);
                finish();

            }
        });
    }
}
