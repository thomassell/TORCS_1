package com.thomas_sell.torcs2;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 1/14/2015.
 */
public class App extends Application {

    private static WebData webData;
    private static GPS gps;
    private static List<EventFlyers> eventFlyers;
    private static List<RaceResults> raceResults;
    private static List<MessageData> messageData;

    private static <T> void Wait(T type)
    {
        while(type == null)
        {
            try{ Thread.sleep(200); }
            catch(InterruptedException ex){ Log.e("", ex.toString()); }
        }
    }

    public static List<EventFlyers> GetEventFlyers()
    {
        Wait(eventFlyers);
        return eventFlyers;
    }

    public static List<RaceResults> GetRaceResults()
    {
        Wait(raceResults);
        return raceResults;
    }

    public static List<MessageData> GetMessageData()
    {
        Wait(messageData);
        return messageData;
    }

    public static WebData GetWebData()
    {
        Wait(webData);
        return webData;
    }

    public static GPS GetGPS()
    {
        Wait(gps);
        return gps;
    }

    public static class WebData
    {
        public String PointsURL;
        public List<String> FlyerURL;
        public List<String> ResultsURL;

        @Override
        public String toString()
        {
            return "PointsURL: " + PointsURL + "\nFlyerURL: " + FlyerURL + "\nResultsURL: " + ResultsURL;
        }

        @Exclude
        public String ignoreThisField;
    }

    public static class GPS
    {
        public double latitude;
        public double longitude;
        public String track;

        @Override
        public String toString()
        {
            return "latitude: " + latitude + "\nlongitude: " + longitude + "\ntrack: " + track;
        }

        @Exclude
        public String ignoreThisField;
    }

    public static class EventFlyers
    {
        public String date;
        public int round;
        public String track;

        @Override
        public String toString()
        {
            return "date: " + date + "\nround: " + round + "\ntrack: " + track;
        }

        @Exclude
        public String ignoreThisField;
    }

    public static class RaceResults
    {
        public int EventID;
        public int round;
        public String track;

        @Override
        public String toString()
        {
            return "eventID: " + EventID + "\nround: " + round + "\ntrack: " + track;
        }

        @Exclude
        public String ignoreThisField;
    }

    public static class MessageData
    {
        public String message;
        public String date;

        public MessageData() {}

        @Override
        public String toString()
        {
            return "message: " + message + "\ndate: " + date;
        }

        @Exclude
        public String ignoreThisField;

        public MessageData(String m)
        {
            message = m;

            DateFormat df = new SimpleDateFormat("h:mm a MMM dd, yyyy");
            Date now = new Date();
            date = df.format(now);
        }

        public Map<String, Object> toMap()
        {
            HashMap<String, Object> result = new HashMap<>();
            result.put("message", message);
            result.put("date", date);
            return result;
        }
    }


    @Override
    public void onCreate(){
        super.onCreate();

        new LoadFirebase().execute("");
    }

    private static void load_WebData()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://torcs.firebaseio.com/WebData/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                webData = dataSnapshot.getValue(WebData.class);
                Log.d("", "Value is: " + webData.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private static void load_GPS()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://torcs.firebaseio.com/GPS/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gps = dataSnapshot.getValue(GPS.class);
                Log.d("", "Value is: " + gps.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private static void load_EventFlyers()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://torcs.firebaseio.com/EventFlyers/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventFlyers = new ArrayList<EventFlyers>();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    EventFlyers ev = ds.getValue(EventFlyers.class);
                    eventFlyers.add(ev);
                }
                Log.d("", "Value is: " + eventFlyers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private static void load_RaceResults()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://torcs.firebaseio.com/RaceResults/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                raceResults = new ArrayList<RaceResults>();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    RaceResults rr = ds.getValue(RaceResults.class);
                    raceResults.add(rr);
                }
                Log.d("", "Value is: " + raceResults);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private static void load_MessageData()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://torcs.firebaseio.com/MessageData/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageData = new ArrayList<MessageData>();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    MessageData md = ds.getValue(MessageData.class);
                    messageData.add(md);
                }
                Collections.reverse(messageData);
                Log.d("", "Value is: " + messageData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private static class LoadFirebase extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            load_WebData();
            load_GPS();
            load_EventFlyers();
            load_RaceResults();
            load_MessageData();
            return "result";
        }

        @Override
        protected void onPostExecute(String result)
        {
            Log.d("firebase:", "Loaded and done with thread");
        }
    }
}
