package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Points_Standings extends Activity {

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_points__standings);
    }
	public void onClick(View v) 
	{
		if(v.getId() == R.id.class_pro)
		{
			Intent x = new Intent(this, Pro_classes.class);
			startActivity(x);
		}
		else if(v.getId() == R.id.class_a)
		{
			Intent x = new Intent(this, A_classes.class);
			startActivity(x);
		}
		else if(v.getId() == R.id.class_b)
		{
			Intent x = new Intent(this, B_classes.class);
			startActivity(x);
		}
		else if(v.getId() == R.id.class_c)
		{
			Intent x = new Intent(this, C_classes.class);
			startActivity(x);
		}
		else if(v.getId() == R.id.class_d)
		{
			Intent x = new Intent(this, D_classes.class);
			startActivity(x);
		}
		else if(v.getId() == R.id.class_open)
		{
			Intent x = new Intent(this, Open_classes.class);
			startActivity(x);
		}
		else if(v.getId() == R.id.class_sxs)
		{
			Intent x = new Intent(this, Sxs_classes.class);
			startActivity(x);
		}
	}
}
