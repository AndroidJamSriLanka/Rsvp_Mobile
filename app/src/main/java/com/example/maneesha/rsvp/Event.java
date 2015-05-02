package com.example.maneesha.rsvp;


import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Maneesha on 01-May-15.
 */
public class Event implements Serializable{
    public Bitmap bitmap;
    public String name;
    public String date;
    public String start_time;
    public String end_time;
    public String org_name;


    public Event(String name, String date, String start_time,String end_time,String org_name, Bitmap bitmap){

        this.bitmap =bitmap;
        this.name =name;
        this.date =date;
        this.start_time =start_time;
        this.end_time =end_time;
        this.org_name =org_name;

    }
    public Event(String name, String date, String start_time,String end_time,String org_name){

        //this.bitmap =bitmap;
        this.name =name;
        this.date =date;
        this.start_time =start_time;
        this.end_time =end_time;
        this.org_name =org_name;

    }
}
