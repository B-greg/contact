package com.bflavien.contact.dao;

import java.util.ArrayList;
import java.util.List;

import com.bflavien.contact.model.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class DataBaseRepository extends Repository<Contact> {

    public DataBaseRepository(Context context) {
        sqLiteOpenHelper = new DataBaseOpenHelper(context, null);
    }

    @Override
    public List<Contact> GetAll() {
        //
        Cursor cursor = myDB.query(DataBaseOpenHelper.TABLE_NAME,
                new String[]{DataBaseOpenHelper.ID,
                        DataBaseOpenHelper.FIRSTNAME,
                        DataBaseOpenHelper.LASTNAME,
                        DataBaseOpenHelper.PHONE_NUMBER,
                        DataBaseOpenHelper.EMAIL,
                        DataBaseOpenHelper.ADDRESS,
                        }, null, null, null,
                null, null);

        return ConvertCursorToListObject(cursor);
    }

    @Override
    public Contact GetById(Long id) {
        //
        Cursor cursor = myDB.query(DataBaseOpenHelper.TABLE_NAME,
                new String[]{DataBaseOpenHelper.ID,
                        DataBaseOpenHelper.FIRSTNAME,
                        DataBaseOpenHelper.LASTNAME,
                        DataBaseOpenHelper.PHONE_NUMBER,
                        DataBaseOpenHelper.EMAIL,
                        DataBaseOpenHelper.ADDRESS,},
                DataBaseOpenHelper.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);


        return ConvertCursorToOneObject(cursor);
    }

    @Override
    public long Save(Contact entite) {
        //
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseOpenHelper.ID, entite.id);
        contentValues.put(DataBaseOpenHelper.FIRSTNAME, entite.firstname);
        contentValues.put(DataBaseOpenHelper.LASTNAME, entite.lastname);
        contentValues.put(DataBaseOpenHelper.PHONE_NUMBER, entite.phoneNumber);
        contentValues.put(DataBaseOpenHelper.EMAIL, entite.email);
        contentValues.put(DataBaseOpenHelper.ADDRESS, entite.address);



        return myDB.insert(DataBaseOpenHelper.TABLE_NAME, null, contentValues);
    }

    @Override
    public void Update(Contact entite) {
        //
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseOpenHelper.ID, entite.id);
        contentValues.put(DataBaseOpenHelper.FIRSTNAME, entite.firstname);
        contentValues.put(DataBaseOpenHelper.LASTNAME, entite.lastname);
        contentValues.put(DataBaseOpenHelper.PHONE_NUMBER, entite.phoneNumber);
        contentValues.put(DataBaseOpenHelper.EMAIL, entite.email);
        contentValues.put(DataBaseOpenHelper.ADDRESS, entite.address);

        myDB.update(DataBaseOpenHelper.TABLE_NAME, contentValues, DataBaseOpenHelper.ID + "=?", new String[]{String.valueOf(entite.id)});
    }

    @Override
    public void Delete(Long id) {
        //
        myDB.delete(DataBaseOpenHelper.TABLE_NAME, DataBaseOpenHelper.ID + "=?", new String[]{String.valueOf(id)});

    }

    @Override
    public List<Contact> ConvertCursorToListObject(Cursor c) {
        //
        List<Contact> items = new ArrayList<Contact>();

        if (c.getCount() == 0)
            return items;

        c.moveToFirst();

        do {
            Contact item = ConvertCursorToObject(c);
            items.add(item);
        } while (c.moveToNext());
        c.close();

        return items;
    }

    @Override
    public Contact ConvertCursorToObject(Cursor c) {
        //
        Contact item = new Contact(c.getLong(DataBaseOpenHelper.NUM_COLUMN_ID),
                c.getString(DataBaseOpenHelper.NUM_COLUMN_FIRSTNAME), c.getString(DataBaseOpenHelper.NUM_COLUMN_LASTNAME),
                c.getString(DataBaseOpenHelper.NUM_COLUMN_PHONE_NUMBER), c.getString(DataBaseOpenHelper.NUM_COLUMN_EMAIL),
                c.getString(DataBaseOpenHelper.NUM_COLUMN_ADDRESS));
        return item;
    }

    @Override
    public Contact ConvertCursorToOneObject(Cursor c) {
        //
        Contact item;
        if (c.moveToFirst()) {
            item = ConvertCursorToObject(c);
        } else
            item = null;
        c.close();
        return item;
    }

}
