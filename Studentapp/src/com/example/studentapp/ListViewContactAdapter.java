package com.example.studentapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewContactAdapter extends BaseAdapter{
	
private static ArrayList<ListviewContactItem> listContact;

private LayoutInflater mInflater;

public ListViewContactAdapter(Context photosFragment, ArrayList<ListviewContactItem> results){
    listContact = results;
    mInflater = LayoutInflater.from(photosFragment);
}

@Override
public int getCount() {
    // TODO Auto-generated method stub
    return listContact.size();
}

@Override
public Object getItem(int arg0) {
    // TODO Auto-generated method stub
    return listContact.get(arg0);
}

@Override
public long getItemId(int arg0) {
    // TODO Auto-generated method stub
    return arg0;
}


public View getView(int position, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub
    ViewHolder holder;
    if(convertView == null){
        convertView = mInflater.inflate(R.layout.row, null);
        holder = new ViewHolder();
        holder.txtname = (TextView) convertView.findViewById(R.id.label);          
        holder.txtphone = (TextView) convertView.findViewById(R.id.value);

        convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }

    holder.txtname.setText(listContact.get(position).getName());
    holder.txtphone.setText(listContact.get(position).getPhone());

    return convertView;
}

static class ViewHolder{
    TextView txtname, txtphone;
}


}