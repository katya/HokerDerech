package com.example.katya.hokerderech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;


import com.example.katya.hokerderech.LabModel.Laboratory;

/**
 * Created by Katya on 8/23/15.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {

    private String[] results;
    protected static LayoutInflater inflater = null;


    public CustomArrayAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        results = objects;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.lab_table_row, null);
        CheckBox checked = (CheckBox) view.findViewById(R.id.row_check);
        checked.setEnabled(false);
        EditText x = (EditText) view.findViewById(R.id.row_x);
        EditText y = (EditText) view.findViewById(R.id.row_y);

    return view;
    }

}
