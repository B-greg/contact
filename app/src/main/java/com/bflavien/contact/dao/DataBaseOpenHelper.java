package com.bflavien.contact.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {


    // Nom de la table
    public static final String TABLE_NAME = "contacts";

    // Description des colonnes
    public static final String ID = "ID";
    public static final int NUM_COLUMN_ID = 0;
    public static final String FIRSTNAME = "firstname";
    public static final int NUM_COLUMN_FIRSTNAME = 1;
    public static final String LASTNAME = "lastname";
    public static final int NUM_COLUMN_LASTNAME = 2;
    public static final String PHONE_NUMBER = "phone_number";
    public static final int NUM_COLUMN_PHONE_NUMBER = 3;
    public static final String EMAIL = "email";
    public static final int NUM_COLUMN_EMAIL = 4;
    public static final String ADDRESS = "address";
    public static final int NUM_COLUMN_ADDRESS = 5;



    // Requ�te SQL pour la cr�ation da la base
    private static final String CREATE_DB = "CREATE TABLE "
            + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY, " + FIRSTNAME+ " TEXT , "
            + LASTNAME + " TEXT, " + PHONE_NUMBER + " TEXT, "
            + EMAIL + " TEXT, " + ADDRESS + " TEXT );";

    public DataBaseOpenHelper(Context context, CursorFactory factory) {
        super(context, DataBaseConstant.DATEBASE_NAME, factory, DataBaseConstant.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > DataBaseConstant.DATABASE_VERSION) {
            db.execSQL("DROP TABLE " + TABLE_NAME + ";");
            onCreate(db);
        }
    }

    public static String getCreateDb() {
        return CREATE_DB;
    }

}
