package com.example.katya.hokerderech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.katya.hokerderech.LabModel.LaboratoryModel;

/**
 * Created by Katya on 8/14/15.
 */
public class LabListAdapter extends BaseAdapter{

    Context context;
    LaboratoryModel model;
    protected static LayoutInflater inflater = null;


    public LabListAdapter(LaboratoryModel model) {
        this.context = model.getActivity();
        this.model = model;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return model.getNumberOfLabs();
    }

    @Override
    public Object getItem(int position) {
        return model.getLabByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.lab_item_layout, null);
        TextView labName = (TextView) view.findViewById(R.id.lab_item);
        labName.setText(model.getLabName(position));
        return view;
    }
}
