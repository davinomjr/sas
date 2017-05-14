package com.example.davino.sas.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.davino.sas.R;

/**
 * Created by Davino on 18/05/2015.
 */
public class ArrayAdapterList extends ArrayAdapter<ObjectList> {

    Context mContext;
    int layoutResourceId;
    ObjectList data[] = null;

    public ArrayAdapterList(Context mContext, int layoutResourceId, ObjectList[] data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        ObjectList objectItem = data[position];

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewtel);
        textViewItem.setText(objectItem.itemName);
        textViewItem.setTag(objectItem.itemId);
        return convertView;
    }

}
