package com.thomas_sell.torcs2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;


public class Display_flyers_window extends Activity {

    private boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_flyer__window);

        WebView mWebView = findViewById(R.id.webView);

        mWebView.setBackgroundColor(Color.DKGRAY);

        int roundNum = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            roundNum = extras.getInt("round");	//get the value based on the "url"
        }

        App.WebData webData = App.GetWebData();
        String url = webData.FlyerURL.get(0) + roundNum + webData.FlyerURL.get(1);

        if(isOnline())
        {
			/*opens in app using google drive*/
            mWebView.getSettings().setJavaScriptEnabled(true);
            //mWebView.loadUrl("https://docs.google.com/viewer?url="+round_num_url);
            mWebView.loadUrl("https://drive.google.com/viewerng/viewer?url=" + url);
		    
		    /*saves pdf and then opens it in browser (at least on blackberry)*/
            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Internet");
            builder.setMessage("Check your wifi or cellular connection.")
                    .setCancelable(false)
                    .setPositiveButton("Go back", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}