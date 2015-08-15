package com.example.katya.hokerderech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
                startActivity(labForm);
               // Toast.makeText(MainActivity.this, "On this place will be Laboratory Form", Toast.LENGTH_SHORT).show();
                listAdapter.notifyDataSetChanged();
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = new LaboratoryModel(this);

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
        if(!intentUserName.equals("root")){
            menu.findItem(R.id.create_lab).setVisible(false);

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
        Toast.makeText(MainActivity.this, "Create new laboratory Activity - TBD", Toast.LENGTH_SHORT).show();

    }

}
