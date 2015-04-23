package com.example.maneesha.rsvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;




public class ListViewActivity extends Activity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        WebService webService = new WebService(ListViewActivity.this,"get","Loading");
        webService.asyncResponse=this;
        webService.execute("http://www.json-generator.com/api/json/get/bZxjfqqLfS");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
            ArrayList<Venue> arrayList = new ArrayList<Venue>();
            JSONArray jsonArray = new JSONArray(output);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jo = new JSONObject(jsonArray.getString(i));
                arrayList.add(new Venue(jo.getString("name"),jo.getString("description")));
            }

            MyAdapter myAdapter = new MyAdapter(ListViewActivity.this,arrayList);
            ListView listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
