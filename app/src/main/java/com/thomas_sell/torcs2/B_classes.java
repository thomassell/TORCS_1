package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class B_classes extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_b_classes);
		
		setTitle("Points Standings");
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.open_b :
				InvokeActivity(67);
				break;
			case R.id.two_fifty_b :
				InvokeActivity(52);
				break;
			case R.id.sport_b :
				InvokeActivity(171);
				break;
			case R.id.thirty_b :
				InvokeActivity(85);
				break;
			case R.id.fourty_b :
				InvokeActivity(87);
				break;
			case R.id.fifty_b :
				InvokeActivity(206);
				break;
			case R.id.wom_b :
				InvokeActivity(109);
				break;
		}
	}

	private void InvokeActivity(int classId)
	{
		Intent i = new Intent(this, Points_Standings_B_window.class);
		i.putExtra("url", App.GetWebData().PointsURL + classId);
		startActivity(i);
	}
}
