package com.example.nasir.registrationtesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDB extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    //Database Name
    private static final String DATABASE_NAME = "DemoDB";

    //DataBase Version
    private static final int DATABASE_VERSION = 7;

    //Table Name
    private static String REGISTRATION_TABLE = "Registration";

    //Column Name for student table
    private static final String COLUMN_ID = "Registration_id";
    private static final String COLUMN_USERNAME = "_name";
    private static final String COLUMN_PASSWORD = "_password";
    private static final String COLUMN_FNAME = "_firstname";
    private static final String COLUMN_LNAME = "_lastname";
    private static final String COLUMN_ADDRESS = "_address";
    private static final String COLUMN_CITY = "_city";
    private static final String COLUMN_PCODE = "_postal_code";


    //create table for student sql query
    private static final String CREATE_REG_TABLE = " CREATE TABLE IF NOT EXISTS " + REGISTRATION_TABLE
            + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_FNAME + " TEXT,"
            + COLUMN_LNAME + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_CITY + " TEXT,"
            + COLUMN_PCODE + " TEXT" + ")";


    //drop table sql query
    private String DROP_REG_TABLE = " DROP TABLE IF EXISTS " + REGISTRATION_TABLE;

    public SqliteDB (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL(CREATE_REG_TABLE);

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int i, int i1) {

        db.execSQL(DROP_REG_TABLE);
        onCreate(db);

    }

    public boolean InsertRegistrationData( String username, String password, String fName, String lName, String address, String city, String pocalCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_FNAME, fName);
        values.put(COLUMN_LNAME, lName);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_CITY, city);
        values.put(COLUMN_PCODE, pocalCode);

        long studentResult = db.insert(REGISTRATION_TABLE, null, values);

        db.close();

        //if Data insert incorrectly it will return -1
        if (studentResult == -1) {
            return false;
        }
        else {
            return true;
        }
    }


    public Cursor getStudentInformation(String loginUser, String loginPass){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + REGISTRATION_TABLE +
                " WHERE " + COLUMN_USERNAME + " = " + loginUser +
                " AND " + COLUMN_PASSWORD + " = " + loginPass;

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    //Login Authentication
    public String SearchExistingAccount(String user){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT "+ COLUMN_USERNAME + ", " + COLUMN_PASSWORD + " FROM " + REGISTRATION_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String userName, password;
        password = "Not Found";
        if (cursor.moveToFirst()){
            do {
                userName = cursor.getString(0);

                if (userName.equals(user)){
                    password = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return password;
    }
}
