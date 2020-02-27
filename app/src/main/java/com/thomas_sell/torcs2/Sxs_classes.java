package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public class Sxs_classes extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_sxs_classes);
		
		setTitle("Points Standings");
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.sxs_pro :
				InvokeActivity(269);
				break;
			case R.id.sxs_turbo :
				InvokeActivity(257);
				break;
			case R.id.sxs_non_turbo :
				InvokeActivity(258);
				break;
			case R.id.sxs_800 :
				InvokeActivity(256);
				break;
			case R.id.sxs_women :
				InvokeActivity(263);
				break;
		}
	}

	private void InvokeActivity(int classId)
	{
		Intent i = new Intent(this, Points_Standings_Sxs_window.class);
		i.putExtra("url", App.GetWebData().PointsURL + classId);
		startActivity(i);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  // We do nothing here. We're only handling this to keep orientation
	  // or keyboard hiding from causing the WebView activity to restart.
	}
}
