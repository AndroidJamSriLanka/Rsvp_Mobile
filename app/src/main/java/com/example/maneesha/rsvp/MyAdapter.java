package com.example.maneesha.rsvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter {
    public final Context context;
    public MyAdapter(Context context, ArrayList<Event> arrayList) {

        super(context,0, arrayList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Event event = (Event)getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
        TextView title =(TextView)convertView.findViewById(R.id.tv_title);
        TextView date =(TextView)convertView.findViewById(R.id.tv_date);
        Button button = (Button)convertView.findViewById(R.id.btn_details);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Details.class);
                intent.putExtra("info",new Event(event.name,event.date, event.start_time,event.end_time,event.org_name));
                context.startActivity(intent);
            }
        });
        if(event.bitmap==null){
            imageView.setImageResource(R.drawable.home);
        }
        else {
            imageView.setImageBitmap(event.bitmap);
        }

        title.setText(event.name);
        date.setText(event.date);

        return convertView;
    }
}
