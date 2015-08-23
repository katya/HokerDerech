package com.example.katya.hokerderech.LabModel;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katya.hokerderech.CreateFormTask;
import com.example.katya.hokerderech.GetFormTask;
import com.example.katya.hokerderech.RemoveActivity;
import com.example.katya.hokerderech.RemoveLabListAdapter;
import com.example.katya.hokerderech.SendLabResultsTask;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Katya on 8/14/15.
 */
public class LaboratoryModel {

    private Context activity;
    private List<Laboratory> labList =  new ArrayList<>();
    private static LaboratoryModel staticModel = null;


    public LaboratoryModel() {
        labList.add(new Laboratory("Popcorn", "This is description", "time", "temperature", new String[5], "Red", "14/08/2014"));
        labList.add(new Laboratory("Milk&Tea", "This is description", "time", "temperature", new String[10], "Red", "14/08/2014"));
        labList.add(new Laboratory("H2O", "This is description", "temperature", "state", new String[3], "Red", "14/08/2014"));
    }

    public static LaboratoryModel getInstance(){
        if(staticModel == null) {
            staticModel = new LaboratoryModel();
        }
        return staticModel;
    }

    public void setActivity(Context activity){
        this.activity = activity;
    }


    public int getNumberOfLabs() {
        return labList.size();
    }

    public Laboratory getFormByPosition(Context context, int index) {
        Laboratory laboratory = labList.get(index);
        String formName = laboratory.getName();
        //new GetFormTask().execute(new Pair<Context, String>(context, formName));
        return labList.get(index);
    }

    public Laboratory getLabByPosition(int position){
        return labList.get(position);
    }

    public String getLabName(int position) {
        return labList.get(position).getName();
    }

    public Context getActivity(){
        return activity;
    }

    public void addLaboratory(String name, String description, String titleX, String titleY, String count) {
        String time = getCurrentTime();
        Integer rows = new Integer(count);
        Laboratory newLab = new Laboratory(name, description, titleX, titleY, new String[rows], "choose", getCurrentTime());
        if(!labList.contains(newLab)) {
            labList.add(0, newLab);
        }
    }
    private String getCurrentTime(){
//        long date = System.currentTimeMillis();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
//        String dateString = sdf.format(date);
//        return dateString;
        return "somedate";
    }

    private void removeLaboratory(int position) {
        labList.remove(position);
    }

    public void createNewLabForm(Context context){
        Gson gson = new Gson();
        // new is first in model:
        String tempForm = gson.toJson(labList.get(0));
       // new CreateFormTask().execute(new Pair<Context, String>(context, tempForm));

    }

    public void setResultToServer(Context context, String formName){
        //new SendLabResultsTask().execute(new Pair<Context, String>(context, formName));
    }

    public void removeLaboratories(Context context, ListView listView, RemoveLabListAdapter listAdapter){

        HashMap<Integer,CheckBox> removeList = listAdapter.getToRemove();

        for(int index=(listView.getCount()-1); index>=0; index--)
        {
            if(removeList.containsKey(index)) {
                removeList.get(index).setChecked(false);
                removeLaboratory(index);
                //new RemoveFormTask().execute(new Pair<Context, String>(context, formName));
            }
        }
        listAdapter.clearToRemove();

    }

}
