package com.thomas_sell.torcs2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Points_Standings_Sxs_window extends Activity {

	private boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_points__standings_sxs_window);
		
		final ProgressBar progressBar = findViewById(R.id.progressBar);
		WebView webview = findViewById(R.id.webView);
		
		String url = "";
		Bundle extras = getIntent().getExtras();
		if (extras != null) 
		{
			url = extras.getString("url");	//get the value based on the "url"
		}
		
		if(isOnline())
		{
		    webview.setInitialScale(0);
	
		    webview.setWebViewClient(new WebViewClient() {
		        @Override
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {
		            view.loadUrl(url);
		            return true;
		        }
		        @Override
		        public void onPageFinished(WebView view, String url) {
		            progressBar.setVisibility(ProgressBar.GONE);
		        }
		    });

			WebSettings webSettings = webview.getSettings();
			webSettings.setJavaScriptEnabled(true);
			webSettings.setBuiltInZoomControls(true);
			webSettings.setUseWideViewPort(false);
	
		    webview.loadUrl(url);
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
