package com.example.asif.databasecrud;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;


public class DBAdapter {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DBAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
        createDatabase();
    }


    public DBAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DBAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public ModelPatients getPatientInfo(String patientID) {
        open();
        ArrayList<ModelPatients> values = new ArrayList<>();
        ModelPatients patientInfo = new ModelPatients();

        Cursor result = mDb.query(false, "patient",
                new String[]{"name", "age", "disease"}, "patient_id=?", new String[]{patientID}, null, null, null, null);

        if (result.moveToFirst()) {
            patientInfo.setName(result.getString(result.getColumnIndex("name")));
            patientInfo.setAge(result.getString(result.getColumnIndex("age")));
            patientInfo.setDisease(result.getString(result.getColumnIndex("disease")));
        }

        close();
        return patientInfo;
    }

    public ArrayList<ModelPatients> getAllPatients() {

        open();
        ArrayList<ModelPatients> values = new ArrayList<>();
        Cursor result = mDb.query(false, "patient",
                new String[]{"patient_id", "name", "age", "disease"}, null, null, null, null, null, null);

        if (result.moveToFirst()) {
            do {
                ModelPatients patientRow = new ModelPatients();
                patientRow.setPatient_id(result.getString(result.getColumnIndex("patient_id")));
                patientRow.setName(result.getString(result.getColumnIndex("name")));
                patientRow.setAge(result.getString(result.getColumnIndex("age")));
                patientRow.setDisease(result.getString(result.getColumnIndex("disease")));
                values.add(patientRow);
            } while (result.moveToNext());
        } else {
            return null;
        }
        close();
        return values;
    }




    // Retrive all the information from Databse
    public ArrayList<String> getAllNames() {

        open();
        ArrayList<String> values = new ArrayList<>();
        Cursor result = mDb.query(false, "patient",
                new String[]{"name"}, null, null, null, null, null, null);

        if (result.moveToFirst()) {
            do {
                values.add(result.getString(result.getColumnIndex("name")));
            } while (result.moveToNext());
        } else {
            return null;
        }
        close();
        return values;
    }



}
