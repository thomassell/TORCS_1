package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class C_classes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_c_classes);
		
		setTitle("Points Standings");
	}
	
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.open_c :
				InvokeActivity(79);
				break;
			case R.id.two_fifty_c :
				InvokeActivity(80);
				break;
			case R.id.sport_c :
				InvokeActivity(55);
				break;
			case R.id.thirty_c :
				InvokeActivity(56);
				break;
			case R.id.fourty_c :
				InvokeActivity(57);
				break;
			case R.id.fifty_c :
				InvokeActivity(207);
				break;
			case R.id.sixty_c :
				InvokeActivity(262);
				break;
			case R.id.eighty_c :
				InvokeActivity(260);
				break;
			case R.id.wom_c :
				InvokeActivity(114);
				break;
		}
	}

	private void InvokeActivity(int classId)
	{
		Intent i = new Intent(this, Points_Standings_C_window.class);
		i.putExtra("url", App.GetWebData().PointsURL + classId);
		startActivity(i);
	}
}
