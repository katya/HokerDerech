package com.example.katya.hokerderech;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katya.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

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
    // for tests:
    private static MyApi myApiService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_layoyut);
    }

    public void sendResultsToServer(View vew) {
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Milk & Tea"));
//        openConnection();
//        new LabResults().execute();

        Log.v("DetailActivity", "sendResultToServer()");
    }

    private void openConnection(){
        try {
            URL url = new URL("http://httpbin.org/");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    private class LabResults extends AsyncTask<String, Void, JSONObject> {

        ProgressDialog dialog = ProgressDialog.show(DetailActivity.this, "", "Sending data, Please wait...");
        HttpClient client;
        HttpPost post;

        @Override
        protected JSONObject doInBackground(String... params) {
            Log.i("Thread", "Sending Result to Server");

            try{
                client = new DefaultHttpClient();
                String url="http://172.22.64.156:8000/";
                post = new HttpPost(url);

                String key = "axis";
                String value = "100";
                List<NameValuePair> pairs = new ArrayList<>();
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

    // backend class for tests:

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

        private Context context;
        //ProgressDialog dialog = ProgressDialog.show(DetailActivity.this, "", "Sending data, Please wait...");

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
                //return myApiService.sayHi(name).execute().getData();
                return myApiService.setForm(name).execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
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
