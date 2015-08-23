package com.example.katya.hokerderech;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katya.hokerderech.LabModel.Laboratory;
import com.example.katya.hokerderech.LabModel.LaboratoryModel;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;

/**
 * Created by Katya on 8/14/15.
 */
public class DetailActivity extends Activity {

// create laboratory view:
    private TextView formName;
    private TextView titleX;
    private TextView titleY;
    private ListView listView;

    private CustomArrayAdapter rowResultAdapter;

    LaboratoryModel model;

    // for tests:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = LaboratoryModel.getInstance();
        model.setActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_layoyut);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int labIndex=0;
        labIndex = getIntent().getIntExtra("labIndex", labIndex);

        Laboratory laboratory = model.getFormByPosition(this, labIndex);

        formName = (TextView)findViewById(R.id.lab_title);
        formName.setText(laboratory.getName());

        titleX = (TextView) findViewById(R.id.lab_titleX);
        titleX.setText(laboratory.getTitleX());

        titleY = (TextView) findViewById(R.id.lab_titleY);
        titleY.setText(laboratory.getTitleY());

        listView = (ListView) findViewById(R.id.row_lab);
        rowResultAdapter = new CustomArrayAdapter(this, R.layout.lab_table_row, laboratory.getTable());
        listView.setAdapter(rowResultAdapter);
    }

    public void sendResultsToServer(View vew) {
        model.setResultToServer(this, formName.getText().toString());
    }
}
