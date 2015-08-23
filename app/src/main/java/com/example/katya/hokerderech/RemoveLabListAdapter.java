package com.example.katya.hokerderech;

import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.katya.hokerderech.LabModel.LaboratoryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Katya on 8/14/15.
 */
public class RemoveLabListAdapter extends LabListAdapter{

    private HashMap<Integer, CheckBox> toRemove = new HashMap<>();

    public RemoveLabListAdapter(LaboratoryModel model) {
       super(model);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = super.inflater.inflate(R.layout.lab_item_remove, null);
        CheckBox item = (CheckBox)  view.findViewById(R.id.remove_check);
        item.setText(model.getLabName(position));
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check = (CheckBox) v;
                if (!toRemove.isEmpty() && toRemove.containsKey(position)) {
                    check.setChecked(false);
                    toRemove.remove(position);
                } else {
                    check.setChecked(true);
                    toRemove.put(position, check);
                }
            }
        });
        return view;
    }

    public HashMap<Integer,CheckBox> getToRemove() {
        return toRemove;
    }

    public void  clearToRemove() {
        toRemove.clear();
    }
}
