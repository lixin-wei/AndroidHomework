package com.example.wlx65.androidhomework;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;

import java.io.*;

import static android.content.ContentValues.TAG;
class AssetDataBaseHelper extends SQLiteOpenHelper {

    private String DB_PATH;
    private String DB_NAME;
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    AssetDataBaseHelper (Context context, String dbName) {
        super(context, dbName, null, 1);
        this.DB_NAME = dbName;
        this.DB_PATH = context.getFilesDir().getParent() + "/databases/";
        Log.i(TAG, "AssetDataBaseHelper: " + this.DB_PATH);
        this.myContext = context;
    }

    void crateDatabase() throws IOException {
        boolean vtVarMi = isDatabaseExist();

        if (!vtVarMi) {
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open("db/" + DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private boolean isDatabaseExist() {
        SQLiteDatabase kontrol = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            kontrol = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            kontrol = null;
        }

        if (kontrol != null) {
            kontrol.close();
        }
        return kontrol != null;
    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }
    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}