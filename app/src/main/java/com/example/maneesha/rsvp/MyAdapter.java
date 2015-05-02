package com.example.maneesha.rsvp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter {
    public MyAdapter(Context context, ArrayList<Event> arrayList) {
        super(context,0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = (Event)getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        TextView title =(TextView)convertView.findViewById(R.id.tv_title);
        TextView date =(TextView)convertView.findViewById(R.id.tv_date);

        imageView.setImageURI(Uri.parse(event.image_url));
        title.setText(event.name);
        date.setText(event.date);

        return convertView;
    }
}
