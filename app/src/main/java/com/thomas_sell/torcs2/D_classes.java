package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class D_classes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_d_classes);
		
		setTitle("Points Standings");
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.open_d_sat :
				InvokeActivity(230);
				break;
			case R.id.sport_d_sun :
				InvokeActivity(229);
				break;
		}
	}

	private void InvokeActivity(int classId)
	{
		Intent i = new Intent(this, Points_Standings_D_window.class);
		i.putExtra("url", App.GetWebData().PointsURL + classId);
		startActivity(i);
	}
}
