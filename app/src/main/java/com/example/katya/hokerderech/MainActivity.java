package com.example.katya.hokerderech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katya.hokerderech.LabModel.LaboratoryModel;
import com.google.gson.Gson;

/**
 * Created by Katya on 8/14/15.
 */

public class MainActivity extends ActionBarActivity {


    private Toolbar toolbar;
    private LaboratoryModel model;
    private ListView listView;
    private String intentUserName;
    private LabListAdapter listAdapter;

    private AdapterView.OnItemClickListener mDetailsListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent labForm = new Intent(view.getContext(), DetailActivity.class);
                labForm.putExtra("labIndex", position);
                startActivityForResult(labForm, 3);
                //Toast.makeText(MainActivity.this, "On this place will be Laboratory Form", Toast.LENGTH_SHORT).show();
                listAdapter.notifyDataSetChanged();
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = LaboratoryModel.getInstance();
        model.setActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new LabListAdapter(model);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(mDetailsListener);

    }
    @Override
    protected void onResume() {
        super.onResume();
        intentUserName = getIntent().getStringExtra("username");
        String title = intentUserName;
        toolbar.setSubtitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(!intentUserName.equals("")){
            menu.findItem(R.id.create_lab).setVisible(false);
            menu.findItem(R.id.remove_lab).setVisible(false);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
       switch (item.getItemId()) {
            case R.id.action_user:
                logoutUser();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
           case R.id.refresh_settings:
                refreshDB();
                return true;
           case R.id.create_lab:
                createNewLab();
                return true;
           case R.id.remove_lab:
                removeLab();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutUser(){
        Intent logout = new Intent(this, Logout.class);
        startActivity(logout);
        logout.putExtra("exit", true);
        this.setResult(Activity.RESULT_OK, logout);
        finish();
    }

    private void openSettings() {
        Toast.makeText(MainActivity.this, "Settings Activity - TBD", Toast.LENGTH_SHORT).show();
    }

    private void refreshDB(){
        Toast.makeText(MainActivity.this, "Refresh Activity - TBD", Toast.LENGTH_SHORT).show();

    }

    private void createNewLab(){
        Intent createLab = new Intent(this, CreateLabActivity.class);
        startActivityForResult(createLab, 1);
        Log.v("MainActivity:", "Create. Lab N:" + model.getNumberOfLabs());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK || resultCode==0) {
            if(requestCode == 1) {
                // create new lab activity:
                listAdapter.notifyDataSetChanged();
            }
            else if(requestCode == 2) {
                // remove activity:
                listAdapter.notifyDataSetChanged();
            }
        }
    }

    private void removeLab(){
        Intent removeLab = new Intent(this, RemoveActivity.class);
        startActivityForResult(removeLab, 2);
        Log.v("MainActivity:", "After Remove Lab N:" + model.getNumberOfLabs());
    }

}
