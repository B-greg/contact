package com.bflavien.contact.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gregoire on 1/19/15.
 */
public class Contact implements Parcelable {

    public Long id;
    public String firstname;
    public String lastname;
    public String phoneNumber;
    public String email;
    public String address;


    public Contact() {
    }

    public Contact(Long id, String firstname, String lastname, String phoneNumber, String email, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    
    
    
    public String toString(){
        return this.firstname + " " + this.lastname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.firstname);
        dest.writeString(this.lastname);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeString(this.address);
    }

    private Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.phoneNumber = in.readString();
        this.email = in.readString();
        this.address = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}