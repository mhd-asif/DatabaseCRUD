package com.example.asif.databasecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    private static int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/com.example.asif.databaseconnect/databases/";

    private static String DB_NAME = "test.db";// Database name
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);// 1? its Database Version
        //destination path (location) of our database on device
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
         // DB_PATH="/data/data/"+context.getPackageName()+"/database/";

        this.mContext = context;

    }

    public void createDataBase() throws IOException {
        //If database not exists copy it from the assets

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        } else {
            Log.e(TAG, "createDatabase: Database already exists!");
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        Log.e("dbFile", dbFile + "   " + dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    /****************************************************************************************************/
    public void insertPatient(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", queryValues.get("name"));
        values.put("age", Integer.parseInt(queryValues.get("age")));
        values.put("disease", queryValues.get("disease"));

        long rowID = database.insert("patient", null, values);
        Log.e("Row_ID","" + rowID);
        database.close();
    }

    public int updatePatient(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", queryValues.get("name"));
        values.put("age", queryValues.get("age"));
        values.put("disease", queryValues.get("disease"));

        return database.update("patient", values, "patient_id" + " = ?", new String[] { queryValues.get("patient_id") });
        //String updateQuery = "Update  words set txtWord='"+word+"' where txtWord='"+ oldWord +"'";
        //Log.d(LOGCAT,updateQuery);
        //database.rawQuery(updateQuery, null);
        //return database.update("words", values, "txtWord  = ?", new String[] { word });
    }

    public void deletePatient(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM  patient where patient_id='"+ id +"'";
//        Log.d("query",deleteQuery);
        database.execSQL(deleteQuery);
    }
    /******************************************************************************************************/

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        Log.e("onUpgrade", "MM");


        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
