package com.bflavien.contact.activity;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bflavien.contact.R;
import com.bflavien.contact.dao.DataBaseRepository;
import com.bflavien.contact.handler.ContactHandler;
import com.bflavien.contact.model.Contact;

import java.util.List;


public class ContactActivity extends ListActivity implements LoaderManager.LoaderCallbacks<ContactHandler>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        getLoaderManager().initLoader(0, null, this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLoaderManager().destroyLoader(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<ContactHandler> onCreateLoader(int id, Bundle args) {
        LoaderTask task = new LoaderTask(this);
        task.forceLoad();
        return task;
    }

    @Override
    public void onLoadFinished(Loader<ContactHandler> loader, ContactHandler data) {

    }

    @Override
    public void onLoaderReset(Loader<ContactHandler> loader) {

    }

    public static class LoaderTask extends AsyncTaskLoader<ContactHandler> {

        ContactHandler items;
        DataBaseRepository dataBaseRepository;


        public LoaderTask(Context context) {
            super(context);
            items = new ContactHandler();
            dataBaseRepository = new DataBaseRepository(getContext());
        }

        @Override
        public ContactHandler loadInBackground() {
            dataBaseRepository.Open();
            List<Contact> contacts = dataBaseRepository.GetAll();
            dataBaseRepository.Close();
            if (contacts != null) items.setItems(contacts);

            return items;
        }


        @Override
        public void deliverResult(ContactHandler data) {
            super.deliverResult(data);
        }
    }
}
