package com.example.asif.databasecrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nasir on 1/9/2017.
 */

public class PatientListAdapter extends BaseAdapter{

    Context context;
    ArrayList<ModelPatients> patientList;

    public PatientListAdapter(Context ctx, ArrayList<ModelPatients> list)
    {
        this.context = ctx;
        patientList = list;
    }

    @Override
    public int getCount() {
        return patientList.size();
    }

    @Override
    public Object getItem(int position) {
        return patientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelPatients patientListRows = patientList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.patient_list_row, null);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_Name);
        tvName.setText(patientListRows.getName());
        TextView tvAge = (TextView) convertView.findViewById(R.id.tv_Age);
        tvAge.setText(patientListRows.getAge());
        TextView tvDisease = (TextView) convertView.findViewById(R.id.tv_Disease);
        tvDisease.setText(patientListRows.getDisease());

        return convertView;
    }
}
