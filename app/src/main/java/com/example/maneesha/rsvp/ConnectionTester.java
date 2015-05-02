package com.example.maneesha.rsvp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by Raveen on 3/13/2015.
 */
public class ConnectionTester {



    public boolean hasInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isAvailable();

        return isConnected;
    }

    public void internetDialog(final Context context, final Activity activity){
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle("You are not connected to the Internet").setCancelable(false);
        adb.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        }).setPositiveButton("Turn Internet on", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                context.startActivity(intent);
                activity.finish();
            }
        });
        adb.show();
    }
}

