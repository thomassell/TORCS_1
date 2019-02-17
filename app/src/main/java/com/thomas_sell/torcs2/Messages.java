package com.thomas_sell.torcs2;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Thomas on 4/23/2015.
 */
public class Messages extends Activity{

    private void LoadTable(TableLayout table)
    {
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        // title column
        TextView title = new TextView(this);
        title.setText("Messages");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);
        title.setMinHeight(75);
        title.setTextColor(Color.BLACK);

        table.addView(title);


        for (App.MessageData messageData : App.GetMessageData()) {
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0xffcccccc); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(5);
            gd.setStroke(1, 0xffcccccc);

            TextView datesLabel = new TextView(this);
            datesLabel.setText(messageData.date);
            datesLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            datesLabel.setMinHeight(50);
            datesLabel.setPadding(15, 5, 15, 5);
            datesLabel.setTextColor(Color.BLACK);
            datesLabel.setBackground(gd);

            TextView messagesLabel = new TextView(this);
            messagesLabel.setText(messageData.message);
            messagesLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            messagesLabel.setMinHeight(150);
            messagesLabel.setPadding(15, 5, 15, 5);
            messagesLabel.setTextColor(Color.BLACK);

            table.addView(datesLabel);
            table.addView(messagesLabel);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_messages);

        TableLayout table = findViewById(R.id.tableLayout);

        //load the message data from parse
        LoadTable(table);
    }
}
