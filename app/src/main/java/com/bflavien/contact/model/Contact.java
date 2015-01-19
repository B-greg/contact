package com.bflavien.contact.model;

/**
 * Created by gregoire on 1/19/15.
 */
public class Contact {

    public Long id;
    public String firstname;
    public String lastname;
    public String phoneNumber;
    public String email;
    public String address;


    public Contact(Long id, String firstname, String lastname, String phoneNumber, String email, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}