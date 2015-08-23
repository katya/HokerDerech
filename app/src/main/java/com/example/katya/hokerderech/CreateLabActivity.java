package com.example.katya.hokerderech;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.katya.hokerderech.LabModel.Laboratory;
import com.example.katya.hokerderech.LabModel.LaboratoryModel;
import com.google.api.client.json.Json;
import com.google.gson.Gson;

/**
 * Created by Katya on 8/18/15.
 */
public class CreateLabActivity extends Activity {

    LaboratoryModel model = LaboratoryModel.getInstance();
    private EditText et_title;
    private EditText et_description;
    private EditText et_tableX;
    private EditText et_tableY;
    private EditText et_count;
    int color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model.setActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_lab_layoyut);

    }

    @Override
    protected void onResume() {
        super.onResume();

        et_title = (EditText) CreateLabActivity.this.findViewById(R.id.tmplab_title);
        et_description = (EditText) CreateLabActivity.this.findViewById(R.id.tmplab_desc);
        et_tableX = (EditText) CreateLabActivity.this.findViewById(R.id.tmptitle_x);
        et_tableY = (EditText) CreateLabActivity.this.findViewById(R.id.tmptitle_y);
        et_count = (EditText) CreateLabActivity.this.findViewById(R.id.tmptitle_count);

        color = et_title.getDrawingCacheBackgroundColor();
    }

    public void sendResultsToServer(View view) {
        if(createNewLaboratory()) {
            model.createNewLabForm(this);
        }
    }

    private boolean createNewLaboratory() {
        boolean status = false;

        if(validateParameter("title", et_title) &&
                validateParameter("column X", et_tableX) &&
                validateParameter("column Y", et_tableY) &&
                validateNumber("number of checks", et_count))
        {
            model.addLaboratory(et_title.getText().toString(),
                    et_description.getText().toString(),
                    et_tableX.getText().toString(),
                    et_tableY.getText().toString(),
                    et_count.getText().toString());
            status = true;
        }
        return status;
    }

    private  boolean validateParameter(String key, EditText value){
        if(value.getText().toString().length() == 0) {
            value.setBackgroundColor(Color.CYAN);
            Toast.makeText(CreateLabActivity.this, "Please fill: " + key, Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            value.setBackgroundColor(color);
        }
        return true;
    }

    private boolean validateNumber(String key, EditText value){
        if(!validateParameter(key, value)) {
            return false;
        }
        if(!isInteger(value.getText().toString(), 100)) {
                value.setBackgroundColor(Color.CYAN);
                Toast.makeText(CreateLabActivity.this, "Here must be number : " + key, Toast.LENGTH_SHORT).show();
                return false;
        }
        return true;
    }

    private boolean isInteger(String str, int maxNumber) {
        try {
            Integer.parseInt(str);
            int number = Integer.parseInt(str);
            if(number < 0 || number>100) { return false;}
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

}
