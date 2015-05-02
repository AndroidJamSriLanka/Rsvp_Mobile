package com.example.maneesha.rsvp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ActivityMain extends Activity implements AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main);
        ConnectionTester ct = new ConnectionTester();
        if(ct.hasInternet(ActivityMain.this)) {
            load_content();
        }
        else{
            ct.internetDialog(ActivityMain.this, ActivityMain.this);
            Toast.makeText(ActivityMain.this, "No Internet", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void load_content(){
        WebService webService = new WebService(ActivityMain.this,"get","Loading");
        webService.asyncResponse=this;
        webService.execute("http://www.json-generator.com/api/json/get/bUlQAxseOG?indent=2");
    }

    @Override
    public void processFinish(String output) {
        System.out.println(output);

        try {
            ArrayList<Event> arrayList = new ArrayList<Event>();
            JSONArray jsonArray = new JSONArray(output);
            for(int i=0; i<jsonArray.length(); i++){
                LoadImage loadImage= new LoadImage();

                JSONObject jo = new JSONObject(jsonArray.getString(i));
                Bitmap bitmap = loadImage.execute(jo.getString("cover_url")).get();
                arrayList.add(new Event(jo.getString("name"),jo.getString("date"), jo.getString("start_time"),
                        jo.getString("end_time"),jo.getString("org_name"),bitmap));
            }

            MyAdapter myAdapter = new MyAdapter(ActivityMain.this,arrayList);
            ListView listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(myAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class LoadImage extends AsyncTask<String, String, Bitmap> {
        Bitmap bitmap;
        public Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
