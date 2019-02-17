package com.thomas_sell.torcs2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Thomas on 5/27/2016.
 */
public class SplashActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);

       Intent main = new Intent(this, MainActivity.class);
       startActivity(main);
       finish();
   }
}
