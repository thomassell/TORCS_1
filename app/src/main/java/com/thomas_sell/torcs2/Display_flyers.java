package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class Display_flyers extends Activity {

	private Vector<Button> vecTrack;
	private Vector<TextView> vecDate;

	public Display_flyers() {
		vecTrack = new Vector<>();
		vecDate = new Vector<>();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_display_flyers);
		
		setTitle("Event Flyers");

        connect_screen_code();
		AddFirebase();
	}

    //make connections between screen and code
    private void connect_screen_code()
    {
        Button flyer_1 = findViewById(R.id.Flyer_1);
        Button flyer_2 = findViewById(R.id.Flyer_2);
        Button flyer_3 = findViewById(R.id.Flyer_3);
        Button flyer_4 = findViewById(R.id.Flyer_4);
        Button flyer_5 = findViewById(R.id.Flyer_5);
        Button flyer_6 = findViewById(R.id.Flyer_6);
        Button flyer_7 = findViewById(R.id.Flyer_7);
        Button flyer_8 = findViewById(R.id.Flyer_8);
        Button flyer_9 = findViewById(R.id.Flyer_9);
        Button flyer_10 = findViewById(R.id.Flyer_10);
        vecTrack.addElement(flyer_1);
        vecTrack.addElement(flyer_2);
        vecTrack.addElement(flyer_3);
        vecTrack.addElement(flyer_4);
        vecTrack.addElement(flyer_5);
        vecTrack.addElement(flyer_6);
        vecTrack.addElement(flyer_7);
        vecTrack.addElement(flyer_8);
        vecTrack.addElement(flyer_9);
        vecTrack.addElement(flyer_10);

        TextView date_1 = findViewById(R.id.date_1);
        TextView date_2 = findViewById(R.id.date_2);
        TextView date_3 = findViewById(R.id.date_3);
        TextView date_4 = findViewById(R.id.date_4);
        TextView date_5 = findViewById(R.id.date_5);
        TextView date_6 = findViewById(R.id.date_6);
        TextView date_7 = findViewById(R.id.date_7);
        TextView date_8 = findViewById(R.id.date_8);
        TextView date_9 = findViewById(R.id.date_9);
        TextView date_10 = findViewById(R.id.date_10);
        vecDate.addElement(date_1);
        vecDate.addElement(date_2);
        vecDate.addElement(date_3);
        vecDate.addElement(date_4);
        vecDate.addElement(date_5);
        vecDate.addElement(date_6);
        vecDate.addElement(date_7);
        vecDate.addElement(date_8);
        vecDate.addElement(date_9);
        vecDate.addElement(date_10);
    }
	
	private void AddFirebase()
	{
		List<App.EventFlyers> eventFlyers = App.GetEventFlyers();

		for (int x = 0; x < eventFlyers.size(); ++x)
		{
			Button tempButton = vecTrack.get(x);
			TextView tempText = vecDate.get(x);
			App.EventFlyers tempEvent = eventFlyers.get(x);

			tempButton.setText(tempEvent.track);
			tempText.setText(tempEvent.date);
		}
	}

	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.Flyer_1 :
				InvokeActivity(1);
				break;
			case R.id.Flyer_2 :
				InvokeActivity(2);
				break;
			case R.id.Flyer_3 :
				InvokeActivity(3);
				break;
			case R.id.Flyer_4 :
				InvokeActivity(4);
				break;
			case R.id.Flyer_5 :
				InvokeActivity(5);
				break;
			case R.id.Flyer_6 :
				InvokeActivity(6);
				break;
			case R.id.Flyer_7 :
				InvokeActivity(7);
				break;
			case R.id.Flyer_8 :
				InvokeActivity(8);
				break;
			case R.id.Flyer_9 :
				InvokeActivity(9);
				break;
			case R.id.Flyer_10 :
				InvokeActivity(10);
				break;
		}
    }

    private void InvokeActivity(int round)
	{
		Intent i = new Intent(this, Display_flyers_window.class);
		i.putExtra("round", round);
		startActivity(i);
	}
}
