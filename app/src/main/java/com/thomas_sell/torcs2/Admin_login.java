package com.thomas_sell.torcs2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin_login extends Activity {

    private Button loginbutton;
    private EditText password;
    private EditText email;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        loginbutton = findViewById(R.id.login);

        progressBar = findViewById(R.id.progressBarLogin);


        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    //User is signed in
                    Log.d("signed in: ", user.getUid());
                }
                else {
                    //User is signed out
                    Log.d("signed out: ", "");
                }
            }
        };
    }

    public void onClick(View v)
    {
        if(v.getId() == R.id.login)
        {
            progressBar.setVisibility(ProgressBar.VISIBLE);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(loginbutton.getWindowToken(), 0);

            String emailtxt = email.getText().toString();
            String passwordtxt = password.getText().toString();

            if(emailtxt.equals("") || passwordtxt.equals(""))
            {
                Toast.makeText(getApplicationContext(),
                        "Email or password is empty",
                        Toast.LENGTH_LONG).show();
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
            else
            {
                Log.d("Admin_login", "try logging in with firebase");
                //send data to Firebase.com for verification
                auth.signInWithEmailAndPassword(emailtxt, passwordtxt)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("Admin_login.java", "signInWithEmail:onComplete: " + task.isSuccessful());

                                if (task.isSuccessful())    //login successful
                                {
                                    progressBar.setVisibility(ProgressBar.GONE);
                                    Intent intent = new Intent(Admin_login.this, Admin_push.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                } else    //login failed
                                {
                                    Log.w("Admin_login.java", "signInWithEmail: " + task.getException());
                                    Toast.makeText(getApplicationContext(),
                                            "Authentication failed",
                                            Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                                }
                            }
                        });
            }
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if(authListener != null)
            auth.removeAuthStateListener(authListener);
    }
}
