package com.bflavien.contact.handler;

import com.bflavien.contact.model.Contact;

import java.util.List;

/**
 * Created by gregoire on 1/19/15.
 */
public class ContactHandler {
    private List<Contact> mItems;

    public List<Contact> getItems() {
        return mItems;
    }

    public void setItems(List<Contact> items) {
        this.mItems = items;
    }
}
