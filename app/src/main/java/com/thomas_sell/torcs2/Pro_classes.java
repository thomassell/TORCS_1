package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public class Pro_classes extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_pro_classes);
		
		setTitle("Points Standings");
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.pro_1 :
				InvokeActivity(47);
				break;
			case R.id.pro_2 :
				InvokeActivity(88);
				break;
		}
	}

	private void InvokeActivity(int classId)
	{
		Intent i = new Intent(this, Points_Standings_Pro_window.class);
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
