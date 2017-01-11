package com.example.asif.databasecrud;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> menulist = new ArrayList<>();
    Button btnAdd;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.mainListView);

        Log.e("Check","Opening the database to view records!");
        DBAdapter dbAdapter = new DBAdapter(this);

//        Log.e("Check",""+menulist);

        final PatientListAdapter pListAdapter = new PatientListAdapter(this, dbAdapter.getAllPatients());
        listview.setAdapter(pListAdapter);
        listview.setLongClickable(true);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog);

                btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ModelPatients patientInfo = pListAdapter.getItem(position);
//                        Toast.makeText(MainActivity.this, "Update button clicked", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent intentUpdate = new Intent(MainActivity.this, UpdateActivity.class);
                        intentUpdate.putExtra("patient_id", patientInfo.getPatient_id());
                        startActivity(intentUpdate);
                    }
                });

                btnDelete = (Button) dialog.findViewById(R.id.btnDelete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ModelPatients patientInfo = pListAdapter.getItem(position);
                        DataBaseHelper dbHelper = new DataBaseHelper(MainActivity.this);

                        dbHelper.deletePatient(patientInfo.getPatient_id());

                        DBAdapter dbNewAdapter = new DBAdapter(MainActivity.this);
                        PatientListAdapter pNewListAdapter = new PatientListAdapter(MainActivity.this, dbNewAdapter.getAllPatients());
                        listview.setAdapter(pNewListAdapter);
//                        Toast.makeText(MainActivity.this, "Update button clicked", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;
            }
        });

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Add button clicked", Toast.LENGTH_LONG).show();
                Intent intentAdd = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intentAdd);
//                finish();
            }
        });


    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//    }
}
