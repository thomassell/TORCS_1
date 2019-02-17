package com.thomas_sell.torcs2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class MainActivity extends Activity {
	 	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

        FirebaseMessaging.getInstance().subscribeToTopic("torcs");
        FirebaseInstanceId.getInstance().getToken();
	}
	public void onClick(View v) 
	{
		if(v.getId() == R.id.button1)
		{
			Intent flyers = new Intent(this, Display_flyers.class);
			startActivity(flyers);
		}
		else if(v.getId() == R.id.button2)
		{
			Intent results = new Intent(this, Race_Results.class);
			startActivity(results);
		}
		else if(v.getId() == R.id.button3)
		{
			Intent points = new Intent(this, Points_Standings.class);
			startActivity(points);
		}
		else if(v.getId() == R.id.button4)
		{
			Intent browser_TON = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.texasoffroad.net/forum/ubbthreads.php/forums/143/1/Texas_Off_Road_Championship_Se"));
			startActivity(browser_TON);
		}
		else if(v.getId() == R.id.button5)
		{
			Intent sponsors = new Intent(this, Sponsors.class);
			startActivity(sponsors);
		}
        else if(v.getId() == R.id.GPS)
        {
            GPS_maps();
        }
        else if(v.getId() == R.id.pictures)
        {
            Intent pictures = new Intent(this, Pictures.class);
            startActivity(pictures);
        }
        else if(v.getId() == R.id.Messages) {
            Intent messages = new Intent(this, Messages.class);
            startActivity(messages);
        }
	}

    private void GPS_maps()
    {
        App.GPS gps = App.GetGPS();
        double latitude = gps.latitude;
        double longitude = gps.longitude;
        String track = gps.track;

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:<"+latitude+">,<"+longitude+">?q=<"+latitude+">,<"+longitude+">("+track+")"));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");		//only open google maps app
        //checks if google maps app is on device
        if(isCallable(intent))
        {
            startActivity(intent);
        }
        else
        {
            show_alert();
        }
    }
    private boolean isCallable(Intent intent)
    {
        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void show_alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Google Maps not detected");
        builder.setMessage("Please download Google Maps to use this feature")
                .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.cancel();
                        download_google_maps();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void download_google_maps()
    {
        final String appPackageName = "com.google.android.apps.maps";
        try
        {
            //open google play to download app
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        }
        catch (android.content.ActivityNotFoundException anfe)
        {
            //opens google play in browser
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        //return true;
        
     // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.about_app) 
        {
        	Intent about = new Intent(this, About_app.class);
			startActivity(about);
            return true;
        }
        else if (id == R.id.admin)
        {
            Intent admin = new Intent(this, Admin_login.class);
            startActivity(admin);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
