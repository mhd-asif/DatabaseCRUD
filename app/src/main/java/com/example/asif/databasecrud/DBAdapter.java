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

   /* public ArrayList<NavDrawerItem> getDrawerImage() {

        open();
        ArrayList<NavDrawerItem> values = new ArrayList<>();
        Cursor result = mDb.query(false, "information",
                new String[]{"image_name"}, null, null, null, null, null, null);
        //int[] drawables = {R.drawable.family,R.drawable.reason,R.drawable.benefits,R.drawable.sexstudy,R.drawable.birthcontrol,R.drawable.pregnant,R.drawable.babycare};
        if (result.moveToFirst()) {
            do {
                NavDrawerItem navDrawerItem = new NavDrawerItem();
                navDrawerItem.setImage(result.getString(result.getColumnIndex("image_name")));
                values.add(navDrawerItem);
            } while (result.moveToNext());
        } else {
            return null;
        }
        close();
        return values;
    }*/




    // Retrive all the information from Databse
    public ArrayList<String> getAllNames() {

        open();
        ArrayList<String> values = new ArrayList<>();
        Cursor result = mDb.query(false, "patient",
                new String[]{"name"}, null, null, null, null, null, null);
        //int[] drawables = {R.drawable.family,R.drawable.reason,R.drawable.benefits,R.drawable.sexstudy,R.drawable.birthcontrol,R.drawable.pregnant,R.drawable.babycare};
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

    public ArrayList<String> getAllDescription() {
        open();
        ArrayList<String> values = new ArrayList<String>();
        Cursor result = mDb.query(false, "Information",
                new String[]{"details"}, null, null, null, null, null, null);

        if (result.moveToFirst()) {
            do {
                values.add(result.getString(result.getColumnIndex("details")));

                Log.e("DATABASE--textDetails", "" + result.getString(result.getColumnIndex("details")));

            } while (result.moveToNext());
        } else {
            return null;
        }
        close();
        return values;
    }

    public ArrayList<String> getAllImage() {
        open();
        ArrayList<String> values = new ArrayList<String>();
        Cursor result = mDb.query(false, "Information",
                new String[]{"image_name"}, null,null, null, null, null, null);

        if (result.moveToFirst()) {
            do {
                values.add(result.getString(result.getColumnIndex("image_name")));

                Log.e("DATABASE--Image", "" + result.getString(result.getColumnIndex("image_name")));

            } while (result.moveToNext());
        } else {
            return null;
        }
        close();
        return values;
    }

}
