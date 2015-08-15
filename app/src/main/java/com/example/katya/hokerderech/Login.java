package com.example.katya.hokerderech;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    Button Login;
    EditText prompt_username;
    EditText prompt_password;
    LabUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prompt_password = (EditText) findViewById(R.id.prompt_password);
        prompt_username = (EditText) findViewById(R.id.prompt_username);
        Login = (Button) findViewById(R.id.Login);
    }

    public void loginUser(View view){
        user = new LabUser(prompt_username.getText().toString(), prompt_password.getText().toString());
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("username", prompt_username.getText().toString());
        startActivityForResult(mainIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==1){
            if (data.getBooleanExtra("exit", true)){
                finish();
            }
        }
    }


}
