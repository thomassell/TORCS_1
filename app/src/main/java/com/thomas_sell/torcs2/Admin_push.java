package com.thomas_sell.torcs2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Admin_push extends Activity {

    private Button submit;
    private EditText message;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_push);

        message = findViewById(R.id.push_message);
        submit = findViewById(R.id.submit);

        progressBar = findViewById(R.id.progressBarPush);

        FirebaseAuth.getInstance().signOut();   //not sure if this is needed
    }

    public void onClick(View v)
    {
        if(v.getId() == R.id.submit)
        {
            progressBar.setVisibility(ProgressBar.VISIBLE);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(submit.getWindowToken(), 0);

            String message_str = message.getText().toString();
            if(message_str.length() == 0)
            {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                show_alert();
            }
            else
            {
                //firebase MessageData && MessageQueue (used for push notifications thru aws)
                App.MessageData md = new App.MessageData(message_str);
                Log.i("checking date & time: ", md.toString());

                DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://torcs.firebaseio.com/MessageQueue/");
                Map<String, Object> notification = new HashMap<>();
                notification.put("message", md.message);
                ref.push().setValue(notification);
                ref.updateChildren(notification);

                ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://torcs.firebaseio.com/MessageData/");
                Map<String, Object> values = md.toMap();
                Map<String, Object> updates = new HashMap<>();
                updates.put(Integer.toString(App.GetMessageData().size()), values);
                ref.updateChildren(updates);

                progressBar.setVisibility(ProgressBar.GONE);
            }
        }
    }

    private void show_alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Can't send push notification");
        builder.setMessage("No message")
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
}