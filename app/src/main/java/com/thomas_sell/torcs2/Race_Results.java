package com.thomas_sell.torcs2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import java.util.List;
import java.util.Vector;

public class Race_Results extends FragmentActivity implements PopupMenu.OnMenuItemClickListener {

	private int event_id;
    private int[] event_num;
    private Button round_1, round_2, round_3, round_4, round_5, round_6, round_7, round_8, round_9, round_10;
    private Vector<Button> vecTrack;

	public Race_Results() {
		vecTrack = new Vector<>();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_race__results);

        round_1 = findViewById(R.id.round_1);
        round_2 = findViewById(R.id.round_2);
        round_3 = findViewById(R.id.round_3);
        round_4 = findViewById(R.id.round_4);
        round_5 = findViewById(R.id.round_5);
        round_6 = findViewById(R.id.round_6);
        round_7 = findViewById(R.id.round_7);
        round_8 = findViewById(R.id.round_8);
        round_9 = findViewById(R.id.round_9);
        round_10 = findViewById(R.id.round_10);
        vecTrack.addElement(round_1);
        vecTrack.addElement(round_2);
        vecTrack.addElement(round_3);
        vecTrack.addElement(round_4);
        vecTrack.addElement(round_5);
        vecTrack.addElement(round_6);
        vecTrack.addElement(round_7);
        vecTrack.addElement(round_8);
        vecTrack.addElement(round_9);
        vecTrack.addElement(round_10);

		addFirebase();
	}

	private void addFirebase()
	{
        event_num = new int[10];
        List<App.RaceResults> raceResults = App.GetRaceResults();

		for (int x = 0; x < raceResults.size(); ++x) {
			App.RaceResults tempRace = raceResults.get(x);
			if (tempRace.EventID != 0)    //EventID is in firebase (i.e., race results have been posted)
			{
				Button tempButton = vecTrack.get(x);

				tempButton.setText(tempRace.track);
				event_num[x] = tempRace.EventID;
				System.out.println("event number " + event_num[x]);
			}
		}
	}

	public void onClick(View v)
	{
		if(v.getId() == R.id.round_1)
		{
			if(round_1.getText().equals("Round 1"))
			{
				show_alert(round_1.getText().toString());
			}
			else
			{
				showPopup(v);
				event_id = event_num[0];
			}
		}
		else if(v.getId() == R.id.round_2)
		{
			if(round_2.getText().equals("Round 2"))
			{
				show_alert(round_2.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[1];
			}
		}
		else if(v.getId() == R.id.round_3)
		{
			if(round_3.getText().equals("Round 3"))
			{
				show_alert(round_3.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[2];
			}
		}
		else if(v.getId() == R.id.round_4)
		{
			if(round_4.getText().equals("Round 4"))
			{
				show_alert(round_4.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[3];
			}
		}
		else if(v.getId() == R.id.round_5)
		{
			if(round_5.getText().equals("Round 5"))
			{
				show_alert(round_5.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[4];
			}
		}
		else if(v.getId() == R.id.round_6)
		{
			if(round_6.getText().equals("Round 6"))
			{
				show_alert(round_6.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[5];
			}
		}
		else if(v.getId() == R.id.round_7)
		{
			if(round_7.getText().equals("Round 7"))
			{
				show_alert(round_7.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[6];
			}
		}
		else if(v.getId() == R.id.round_8)
		{
			if(round_8.getText().equals("Round 8"))
			{
				show_alert(round_8.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[7];
			}
		}
		else if(v.getId() == R.id.round_9)
		{
			if(round_9.getText().equals("Round 9"))
			{
				show_alert(round_9.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[8];
			}
		}
		else if(v.getId() == R.id.round_10)
		{
			if(round_10.getText().equals("Round 10"))
			{
				show_alert(round_10.getText().toString());
			}
			else
			{
				showPopup(v);
                event_id = event_num[9];
			}
		}
    }

	private boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	private void show_alert(String button_text)
	{
		if(isOnline())
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Not available");
			builder.setMessage(button_text + " race results have not been posted yet.")
			       .setCancelable(false)
			       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                //do things
			        	   dialog.cancel();
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
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

	private void showPopup(View v)
    {
		PopupMenu popup = new PopupMenu(this, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.race_results_class_selector_menu, popup.getMenu());
	    popup.setOnMenuItemClickListener(this);
	    popup.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item)
	{
		switch (item.getItemId())
		{
	        case R.id.pro_1:
	        	InvokeActivity(47);
	            return true;
	        case R.id.pro_2:
	        	InvokeActivity(88);
	            return true;

			case R.id.vet_35_a:
				InvokeActivity(245);
				return true;
	        case R.id.open_a:
	        	InvokeActivity(66);
	            return true;
	        case R.id.two_fifty_a:
	        	InvokeActivity(51);
	            return true;
	        case R.id.sport_a:
	        	InvokeActivity(102);
	            return true;
	        case R.id.thirty_a:
	        	InvokeActivity(84);
	            return true;
	        case R.id.fourty_a:
	        	InvokeActivity(86);
	            return true;
	        case R.id.fifty_a:
	        	InvokeActivity(58);
	        	return true;
			case R.id.sixty_a:
				InvokeActivity(261);
				return true;
			case R.id.eighty_a:
				InvokeActivity(259);
				return true;
	        case R.id.wom_a:
	        	InvokeActivity(81);
	            return true;

	        case R.id.open_b:
	        	InvokeActivity(67);
	            return true;
	        case R.id.two_fifty_b:
	        	InvokeActivity(52);
	            return true;
	        case R.id.sport_b:
	        	InvokeActivity(171);
	            return true;
	        case R.id.thirty_b:
	        	InvokeActivity(85);
	            return true;
	        case R.id.fourty_b:
	        	InvokeActivity(87);
	            return true;
	        case R.id.fifty_b:
	        	InvokeActivity(206);
	            return true;
			case R.id.wom_b:
	        	InvokeActivity(109);
	            return true;

	        case R.id.open_c:
	        	InvokeActivity(79);
	            return true;
	        case R.id.two_fifty_c:
	        	InvokeActivity(80);
	            return true;
	        case R.id.sport_c:
	        	InvokeActivity(55);
	            return true;
	        case R.id.thirty_c:
	        	InvokeActivity(56);
	            return true;
	        case R.id.fourty_c:
	        	InvokeActivity(57);
	            return true;
	        case R.id.fifty_c:
	        	InvokeActivity(207);
	            return true;
			case R.id.sixty_c:
				InvokeActivity(262);
				return true;
			case R.id.eighty_c:
				InvokeActivity(260);
				return true;
	        case R.id.wom_c:
	        	InvokeActivity(114);
	            return true;

	        case R.id.open_d:
	        	InvokeActivity(230);
	            return true;
	        case R.id.sport_d:
	        	InvokeActivity(229);
	            return true;

	        case R.id.fifty_cc_open:
	        	InvokeActivity(199);
	            return true;
			case R.id.fifty_cc_mini:
				InvokeActivity(247);
				return true;
	        case R.id.jr_beg:
	        	InvokeActivity(226);
	            return true;
	        case R.id.sixty_cc_mini:
				InvokeActivity(246);
				return true;
            case R.id.supermini:
            	InvokeActivity(108);
	            return true;
            case R.id.wom_open:
            	InvokeActivity(219);
                return true;
	        case R.id.double_decade:
	        	InvokeActivity(211);
	            return true;
	        case R.id.sixty_plus:
	        	InvokeActivity(59);
	            return true;
			case R.id.sixty_five_plus:
				InvokeActivity(265);
				return true;

			case R.id.sxs_turbo:
				InvokeActivity(257);
				return true;
			case R.id.sxs_non_turbo:
				InvokeActivity(258);
				return true;
			case R.id.sxs_800:
				InvokeActivity(256);
				return true;
			case R.id.sxs_women:
				InvokeActivity(263);
				return true;

	        default:
	            return false;
	    }
	}

	private void InvokeActivity(int classId)
	{
		List<String> resultsUrl = App.GetWebData().ResultsURL;
		final String full_url = resultsUrl.get(0);
		final String small_url = resultsUrl.get(1);

		Intent i = new Intent(this, Race_Results_window.class);
		i.putExtra("url", full_url + event_id + small_url + classId);
		startActivity(i);
	}
}
