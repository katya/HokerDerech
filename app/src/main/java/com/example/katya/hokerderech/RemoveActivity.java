package com.example.katya.hokerderech;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katya.hokerderech.LabModel.Laboratory;
import com.example.katya.hokerderech.LabModel.LaboratoryModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Katya on 8/14/15.
 */

public class RemoveActivity extends Activity {

    private LaboratoryModel model;
    private ListView listView;
    private RemoveLabListAdapter listAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = LaboratoryModel.getInstance();
        model.setActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.remove_title);

        listView = (ListView) findViewById(R.id.listViewRemove);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listAdapter = new RemoveLabListAdapter(model);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void removeLaboratories(View view) {
        model.removeLaboratories(this, listView, listAdapter);
        listAdapter.notifyDataSetChanged();
        Log.v("RemoveActivity:", "After Remove Lab N:" + model.getNumberOfLabs());
    }

}
