package com.example.katya.hokerderech;

import android.content.Context;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katya on 8/14/15.
 */
public class LaboratoryModel {

    private Context activity;
    private List<Laboratory> labList =  new ArrayList<>();

    public LaboratoryModel(Context activity) {
        this.activity = activity;

        labList.add(new Laboratory("Popcorn", "0", "0", "Red", "This is description", "14/08/2014"));
        labList.add(new Laboratory("Milk & Tea", "0", "0", "Red", "This is description", "14/08/2014"));
        labList.add(new Laboratory("Laboratory3", "0", "0", "Red", "This is description", "14/08/2014"));
        labList.add(new Laboratory("Laboratory4", "0", "0", "Red", "This is description", "14/08/2014"));
        labList.add(new Laboratory("Laboratory5", "0", "0", "Red", "This is description", "14/08/2014"));
        labList.add(new Laboratory("Laboratory6", "0", "0", "Red", "This is description", "14/08/2014"));
        labList.add(new Laboratory("Laboratory7", "0", "0", "Red", "This is description", "14/08/2014"));
        labList.add(new Laboratory("Laboratory8", "0", "0", "Red", "This is description", "14/08/2014"));
    }


    public int getNumberOfLabs() {
        return labList.size();
    }

    public Laboratory getLabByPosition(int index) {
        return labList.get(index);
    }

    public String getLabName(int position) {
        return labList.get(position).getName();
    }

    public Context getActivity(){
        return activity;
    }

}
