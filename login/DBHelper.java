package com.example.macstudent.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "ParkingDB";
    public static final String TBName_UserInfo = "UserInfo";
    //public static final String TBName_Parking = "ParkingInfo";

    public DBHelper(Context context) {
        super(context, DBName,  null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = "CREATE TABLE " + TBName_UserInfo +
                    "(Name varchar(100), Phone varchar(20)," +
                    "Email varchar(50) PRIMARY KEY, Password varchar(20)," +
                    "DOB varchar(20))";

            Log.v("DBHelper", CREATE_TABLE);

            db.execSQL(CREATE_TABLE);

        }catch (Exception e){
            Log.e("DBHelper", e.getMessage());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       try {
    db.execSQL("DROP TABLE IF EXISTS " + TBName_UserInfo);

    onCreate(db);

       }catch (Exception e){
           Log.e("DBHelper", e.getMessage());
       }
    }
}
