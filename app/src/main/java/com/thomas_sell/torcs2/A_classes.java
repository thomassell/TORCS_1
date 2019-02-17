package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class A_classes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_a_classes);

		setTitle("Points Standings");
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.vet_35_a :
				InvokeActivity(245);
				break;
			case R.id.open_a :
				InvokeActivity(66);
				break;
			case R.id.two_fifty_a :
				InvokeActivity(51);
				break;
			case R.id.sport_a :
				InvokeActivity(102);
				break;
			case R.id.thirty_a :
				InvokeActivity(84);
				break;
			case R.id.fourty_a :
				InvokeActivity(86);
				break;
			case R.id.fifty_a :
				InvokeActivity(58);
				break;
			case R.id.sixty_a :
				InvokeActivity(261);
				break;
			case R.id.eighty_a :
				InvokeActivity(259);
				break;
			case R.id.wom_a :
				InvokeActivity(81);
				break;
		}
	}

	private void InvokeActivity(int classId)
	{
		Intent i = new Intent(this, Points_Standings_A_window.class);
		i.putExtra("url", App.GetWebData().PointsURL + classId);
		startActivity(i);
	}
}
