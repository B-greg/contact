/****************************************************************************************************************************************/
/*-Autor: Gregoire BARRET																												*/
/*-Date: 19/03/2013																														*/
/*-Project: Social NetWort																												*/
/*-Class: Repository, abstract class																									*/
/*-Content: abstract class for the database managment for open and close the database													*/
/****************************************************************************************************************************************/
package com.bflavien.contact.dao;

import java.sql.Connection;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public abstract class Repository<T> implements IRepository<T> {
    // Base de donn�es
    protected SQLiteDatabase myDB;

    protected SQLiteOpenHelper sqLiteOpenHelper;

    /**
     * constructor
     */
    public Repository() {
    }

    /**
     * Open {@link java.sql.Connection}
     */
    public void Open() {
        myDB = sqLiteOpenHelper.getWritableDatabase();
    }

    /**
     * close connection
     */
    public void Close() {
        myDB.close();
    }

}
