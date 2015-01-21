package com.bflavien.contact.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.bflavien.contact.ContactApplication;
import com.bflavien.contact.R;
import com.bflavien.contact.helper.Validator;

public class EditContactActivity extends ContactActivity {
    
    
    private EditText mFirstNameView;
    private EditText mLastnameView;
    private EditText mPhonenumberView;
    private EditText mEmailView;
    private EditText mAddressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        
        mFirstNameView = (EditText) findViewById(R.id.editText_editContactActivity_firstname);
        mLastnameView = (EditText) findViewById(R.id.editText_editContactActivity_lastname);
        mPhonenumberView = (EditText) findViewById(R.id.editText_editContactActivity_phone);
        mEmailView = (EditText) findViewById(R.id.editText_editContactActivity_email);
        mAddressView = (EditText) findViewById(R.id.editText_editContactActivity_address);

        
        populateView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit_cancel:
                this.finish();
                return true;
            case R.id.action_edit_done:
                if(attemptSaveContact()) this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Boolean attemptSaveContact() {
        
        

        // Reset errors.
        mFirstNameView.setError(null);
        mLastnameView.setError(null);
        mPhonenumberView.setError(null);

        
        // Store values at the time of the login attempt.
        String firstname = mFirstNameView.getText().toString();
        String lastname = mLastnameView.getText().toString();
        String phonenumber = mPhonenumberView.getText().toString();
        String email = mEmailView.getText().toString();
        String address = mAddressView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (!Validator.isValid(firstname)) {
            mFirstNameView.setError(getString(R.string.error_empty_field));
            focusView = mFirstNameView;
            cancel = true;
        }
        if (!Validator.isValid(lastname)) {
            mLastnameView.setError(getString(R.string.error_empty_field));
            focusView = mLastnameView;
            cancel = true;
        }
        if (!Validator.isValid(phonenumber)) {
            mPhonenumberView.setError(getString(R.string.error_empty_field));
            focusView = mPhonenumberView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return false;
        } else {
            contact.firstname = firstname;
            contact.lastname = lastname;
            contact.address = address;
            contact.email = email;
            contact.phoneNumber = Validator.changePhone(phonenumber);

            ContactApplication.sRepository.Open();
            if(contact.id==null)ContactApplication.sRepository.Save(contact);
            else ContactApplication.sRepository.Update(contact);
            ContactApplication.sRepository.Close();
            return true;
        }
    }
    
    private void populateView(){
        mFirstNameView.setText(contact.firstname);
        mLastnameView.setText(contact.lastname);
        mPhonenumberView.setText(contact.phoneNumber);
        mEmailView.setText(contact.email);
        mAddressView.setText(contact.address);
        
    }
    
}
