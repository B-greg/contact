package com.bflavien.contact.activity;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.bflavien.contact.ContactApplication;
import com.bflavien.contact.R;
import com.bflavien.contact.dao.DataBaseRepository;
import com.bflavien.contact.handler.ContactHandler;
import com.bflavien.contact.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class ContactsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<ContactHandler>,
        AdapterView.OnItemLongClickListener {
    
    private List<Contact> mContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //  setContentView(R.layout.activity_contact);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        mContacts = new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, mContacts);
        setListAdapter(arrayAdapter);
        getListView().setOnItemLongClickListener(this);

        getLoaderManager().initLoader(0, null, this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLoaderManager().destroyLoader(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_add_contact:
                Intent intent = new Intent(this, EditContactActivity.class);
                startActivity(intent);
                return true;
            default: 
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Loader<ContactHandler> onCreateLoader(int id, Bundle args) {
        LoaderTask task = new LoaderTask(this);
        task.forceLoad();
        return task;
    }

    @Override
    public void onLoadFinished(Loader<ContactHandler> loader, ContactHandler data) {
        mContacts.clear();
        if (data!=null) mContacts.addAll(data.getItems());
        onContentChanged();
                
    }

    @Override
    public void onLoaderReset(Loader<ContactHandler> loader) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Contact contact = (Contact) getListAdapter().getItem(position);
        Intent intent = new Intent(this, ShowContactActivity.class);
        intent.putExtra(ContactActivity.ARG_CONTACT, contact);
        startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position , long id) {
        Contact contact = (Contact) getListAdapter().getItem(position);
        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra(ContactActivity.ARG_CONTACT, contact);
        startActivity(intent);
        return false;
    }


    public static class LoaderTask extends AsyncTaskLoader<ContactHandler> {

        ContactHandler items;


        public LoaderTask(Context context) {
            super(context);
            items = new ContactHandler();
        }

        @Override
        public ContactHandler loadInBackground() {
            ContactApplication.sRepository.Open();
            //TODO: make query order by name/last name
            List<Contact> contacts = ContactApplication.sRepository.GetAll();
            ContactApplication.sRepository.Close();
            if (contacts != null) items.setItems(contacts);

            return items;
        }


        @Override
        public void deliverResult(ContactHandler data) {
            super.deliverResult(data);
        }
    }
}
