package com.example.maneesha.rsvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ActivityMain extends Activity implements AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main);
        WebService webService = new WebService(ActivityMain.this,"get","Loading");
        webService.asyncResponse=this;
        webService.execute("http://www.json-generator.com/api/json/get/bZxjfqqLfS");



       /*TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Upcoming Events");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Upcoming Events");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Registered Events");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Registered Events");
        tabHost.addTab(tabSpec);
        */


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

    @Override
    public void processFinish(String output) {
        System.out.println(output);

        try {
            ArrayList<Event> arrayList = new ArrayList<Event>();
            JSONArray jsonArray = new JSONArray(output);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jo = new JSONObject(jsonArray.getString(i));
                arrayList.add(new Event(jo.getString("image_url"),jo.getString("name"),jo.getString("date")));
            }

            MyAdapter myAdapter = new MyAdapter(ActivityMain.this,arrayList);
            ListView listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
