package com.example.katya.hokerderech;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katya on 8/14/15.
 */
public class DetailActivity extends Activity{

    Button send;
    private HttpURLConnection conn;
    HttpClient client;
    HttpPost post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_layoyut);
    }

    public void sendResultsToServer(View vew) {
//        //openConnection();
//        client = new DefaultHttpClient();
//        String url="http://172.22.64.156:8000/";
//        post = new HttpPost(url);
//        new LabResults().execute();

        Toast.makeText(DetailActivity.this, "Sending results to Server", Toast.LENGTH_SHORT).show();
        Log.v("DetailActivity", "sendResultToServer()");
    }

    private void openConnection(){
//        try {
//            URL url = new URL("http://httpbin.org/");
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
    }


    private class LabResults extends AsyncTask<String, Void, JSONObject> {

        ProgressDialog dialog = ProgressDialog.show(DetailActivity.this, "", "Sending data, Please wait...");

        @Override
        protected JSONObject doInBackground(String... params) {
            Log.i("Thread", "Sending Result to Server");

            try{
                String key = "axis";
                String value = "100";
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                pairs.add(new BasicNameValuePair(key, value));
                post.setEntity(new UrlEncodedFormEntity(pairs));
                HttpResponse response = client.execute(post);
                int status=response.getStatusLine().getStatusCode();

                Log.v("DetailActivity", "Status from HTTP:" + status);

                if(status == 200)
                {
                    HttpEntity e=response.getEntity();
                    String data=EntityUtils.toString(e);
                    JSONObject last=new JSONObject(data);
                    return last;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPreExecute(){
            //dialog.dismiss();
            Log.i("Thread", "Started...");
            dialog.show();
        }
        protected void onPostExecute(JSONObject result){
            Log.i("Thread", "Done...");
            String status;
            try {
                status= result.getString("status");
                Toast.makeText(DetailActivity.this, "Sent Laboratory Form", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
//
//            try{
//                conn.setDoOutput(true);
//                conn.setChunkedStreamingMode(0);
//
//                OutputStream out = new BufferedOutputStream(conn.getOutputStream());
//                writeStream(out);
//
//            }
//            catch(IOException e)
//            {
//                e.printStackTrace();
//
//            }
//            finally {
//                conn.disconnect();
//            }
//
//            return null;
//        }
//
//
//
//        protected void onPreExecute(){
//
//            Log.i("Thread", "Started...");
//            dialog.show();
//        }
//
//        protected void onPostExecute(JSONObject result){
//
//            Log.i("Thread", "Done...");
//        }
//
//    }
//    private void writeStream(OutputStream out) {
//
//
//    }
//
//    Gson.toJson();
}
