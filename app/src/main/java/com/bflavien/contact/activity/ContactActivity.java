package com.bflavien.contact.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bflavien.contact.R;
import com.bflavien.contact.model.Contact;

/**
 * Created by gregoire on 1/21/15.
 */
public class ContactActivity extends Activity {

    protected Contact contact;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        if(actionBar!=null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("contact")) contact = extras.getParcelable("contact");
        else contact=new Contact();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
