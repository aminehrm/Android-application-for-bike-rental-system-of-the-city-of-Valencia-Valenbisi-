package com.amine.hrimech.uv.es;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Mydatabase";
    public static final String TABLE_NAME = "Reports";

    public static final String REPORT_ID = "_id";
    public static final String STATION_ID = "station_id";
    public static final String REPORT_NAME = "name";
    public static final String REPORT_DESCRIPTION = "description";
    public static final String REPORT_STATUS = "status";
    public static final String REPORT_TYPE = "type";

    public static final String TABLE_NAME2 = "users";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_USERNAME= "username";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TYPEACCOUNT = "account_type";
    private static final String TAG = "DBAdapter";
    public static final String USER_ID = "_id";

    private SQLiteDatabase dataBase;
    private ContentValues contentValues;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dataBase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists " + TABLE_NAME + "(" +
                        REPORT_ID + " integer primary key autoincrement," +
                        STATION_ID + " integer," +
                        REPORT_NAME + " text," +
                        REPORT_DESCRIPTION + " text," +
                        REPORT_STATUS + " text," +
                        REPORT_TYPE + " text" + ")"
        );
        db.execSQL(
                "create table if not exists " + TABLE_NAME2 + "(" +
                        USER_ID + " integer primary key autoincrement," +
                        KEY_USERNAME + " text," +
                        KEY_EMAIL + " text," +
                        KEY_PASSWORD + " text," +
                        KEY_TYPEACCOUNT + " text" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        db.execSQL("drop table if exists " + TABLE_NAME2);
        onCreate(db);
    }

    public void insertReport(Integer stationId, String name, String description, String status, String type) {
        contentValues = new ContentValues();

        contentValues.put(DBHelper.STATION_ID, stationId);
        contentValues.put(DBHelper.REPORT_NAME, name);
        contentValues.put(DBHelper.REPORT_DESCRIPTION, description);
        contentValues.put(DBHelper.REPORT_STATUS, status);
        contentValues.put(DBHelper.REPORT_TYPE, type);

        dataBase.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    public void updateReport(Integer stationId, Integer reportId, String name, String description, String status, String type) {
        contentValues = new ContentValues();

        contentValues.put(DBHelper.STATION_ID, stationId);
        contentValues.put(DBHelper.REPORT_NAME, name);
        contentValues.put(DBHelper.REPORT_DESCRIPTION, description);
        contentValues.put(DBHelper.REPORT_STATUS, status);
        contentValues.put(DBHelper.REPORT_TYPE, type);

        dataBase.update(DBHelper.TABLE_NAME, contentValues, DBHelper.REPORT_ID + "=" + reportId, null);
    }

    public void deleteReport(Integer reportId) {
        dataBase.delete(DBHelper.TABLE_NAME, DBHelper.REPORT_ID + "=" + reportId, null);
    }

    public Cursor findReportByBikeStation(Integer stationId) {
        return dataBase.query(DBHelper.TABLE_NAME,
                new String[]{DBHelper.REPORT_ID, DBHelper.REPORT_STATUS, DBHelper.REPORT_NAME},
                DBHelper.STATION_ID + "=" + stationId,
                null, null, null, null);
    }

    public Cursor findReportById(Integer reportId) {
        return dataBase.query(DBHelper.TABLE_NAME,
                new String[] {DBHelper.REPORT_NAME, DBHelper.REPORT_DESCRIPTION, DBHelper.REPORT_STATUS, DBHelper.REPORT_TYPE},
                DBHelper.REPORT_ID + "=" + reportId, null, null, null, null);
    }






    public void insertUser( String name, String email, String password, String type) {
        contentValues = new ContentValues();


        contentValues.put(DBHelper.KEY_USERNAME, name);
        contentValues.put(DBHelper.KEY_EMAIL, email);
        contentValues.put(DBHelper.KEY_PASSWORD, password);
        contentValues.put(DBHelper.KEY_TYPEACCOUNT, type);

        dataBase.insert(DBHelper.TABLE_NAME2, null, contentValues);
    }

    public void updateReport(int id,String name, String email, String password, String type) {
        contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_USERNAME, name);
        contentValues.put(DBHelper.KEY_EMAIL, email);
        contentValues.put(DBHelper.KEY_PASSWORD, password);
        contentValues.put(DBHelper.KEY_TYPEACCOUNT, type);

        dataBase.update(DBHelper.TABLE_NAME2, contentValues, DBHelper.USER_ID + "=" + id, null);
    }



    public boolean Login(String username, String password) throws SQLException
    {
        Cursor mCursor = dataBase.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE username=? AND password=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {

                return true;
            }
        }
        return false;
    }
    public String gettype(String username) throws SQLException
    {
        Cursor mCursor = dataBase.rawQuery("SELECT account_type FROM " + TABLE_NAME2 + " WHERE username=? ", new String[]{username});
        String returnString = "";
                if(mCursor.moveToFirst()) {
                    returnString = mCursor.getString(mCursor.getColumnIndex("account_type"));

                }




        return returnString;
    }
    public String getemail(String username) throws SQLException
    {
        Cursor mCursor = dataBase.rawQuery("SELECT email FROM " + TABLE_NAME2 + " WHERE username=? ", new String[]{username});
        String returnString = "";
               if(mCursor.moveToFirst()) {
                   returnString = mCursor.getString(mCursor.getColumnIndex("email"));

               }


        return returnString;
    }


    public boolean ExistUser(String email,String name) throws SQLException
    {
        Cursor mCursor = dataBase.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE email=? or username=?", new String[]{email,name});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }
}
