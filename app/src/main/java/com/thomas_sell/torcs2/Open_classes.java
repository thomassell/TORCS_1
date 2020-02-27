package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Open_classes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_open_classes);
		
		setTitle("Points Standings");
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.jr :
				InvokeActivity(226);
				break;
			case R.id.fifty_cc :
				InvokeActivity(199);
				break;
			case R.id.fifty_cc_mini :
				InvokeActivity(247);
				break;
			case R.id.sixty_cc_mini :
				InvokeActivity(246);
				break;
			case R.id.supermini :
				InvokeActivity(108);
				break;
			case R.id.wom_open :
				InvokeActivity(219);
				break;
			case R.id.double_decade :
				InvokeActivity(211);
				break;
			case R.id.sixty_five_plus :
				InvokeActivity(265);
				break;
		}
	}

	private void InvokeActivity(int classId)
	{
		Intent x = new Intent(this, Points_Standings_Open_window.class);
		x.putExtra("url", App.GetWebData().PointsURL + classId);
		startActivity(x);
	}
}
