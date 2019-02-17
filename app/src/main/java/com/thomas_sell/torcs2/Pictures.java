package com.thomas_sell.torcs2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Thomas Sell on 12/22/2014.
 */
public class Pictures extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_picture);

    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.Mary)
        {
            Intent browser_Mary = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stoptimephotography.net/"));
            startActivity(browser_Mary);
        }
        else if (v.getId() == R.id.Chris)
        {
            Intent browser_Chris = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flickr.com/photos/cdn_bacon099/sets/"));
            startActivity(browser_Chris);
        }
        else if (v.getId() == R.id.Davey)
        {
            Intent browser_Davey = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dk28photography.pixieset.com"));
            startActivity(browser_Davey);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pictures, menu);
        // Inflate the menu items for use in the action bar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.info)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Picture Information");
            builder.setMessage("If you have a picture website and wish to have a link in the app please contact Thomas Sell")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
