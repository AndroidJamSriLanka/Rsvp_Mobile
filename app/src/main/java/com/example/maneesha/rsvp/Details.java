package com.example.maneesha.rsvp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.maneesha.rsvp.R;

public class Details extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle intent = getIntent().getExtras();
        Event event=(Event)intent.get("info");

        Bitmap bitmap = event.bitmap;
        String name = event.name;
        String date  = event.date;
        String org_name = event.org_name;
        String start_time = event.start_time;
        String end_time = event.end_time;




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
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
}
