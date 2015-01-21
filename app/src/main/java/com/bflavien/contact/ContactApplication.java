package com.bflavien.contact;

import android.app.Application;

import com.bflavien.contact.dao.DataBaseOpenHelper;
import com.bflavien.contact.dao.DataBaseRepository;

/**
 * Created by gregoire on 1/21/15.
 */
public class ContactApplication extends Application {

    public static DataBaseRepository sRepository;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        sRepository = new DataBaseRepository(getApplicationContext());
    }
}
