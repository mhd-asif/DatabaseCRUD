package com.example.asif.databasecrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> menulist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.mainListView);

        DBAdapter dbAdapter = new DBAdapter(this);
        menulist=dbAdapter.getAllNames();
//
//        Log.e("Check",""+menulist);

        ArrayAdapter<String> arrAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menulist);
        listview.setAdapter(arrAdapter);

    }
}
