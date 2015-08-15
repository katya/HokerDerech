package com.example.katya.hokerderech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Logout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      Toast.makeText(Logout.this, "Here must be logout - TBD", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_logout);
    }

    public void logoutUser(View v){
        finish();
    }


}
