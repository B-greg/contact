package com.bflavien.contact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bflavien.contact.model.Contact;

import java.util.Collection;
import java.util.List;

/**
 * Created by gregoire on 1/19/15.
 */
public class ContactAdapter extends BaseAdapter {

    protected List<Contact> items;
    private Context mContext;
    protected LayoutInflater inflater;
    
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public Context getContext() {
        return mContext;
    }

    public void appendItems(Collection<Contact> items){
        this.items.addAll(items);
    }

    public void appendItem(Contact item){
        this.items.add(item);
    }
    
    public void deleteItem(int position){
        this.items.remove(position);
        
    }

    public void clear(){
        this.items.clear();
    }

    public static class ViewHolder {
        public TextView imageView;
        public TextView titleTextView;


        public ViewHolder(View view){

        }
    }
}
