package com.example.android.cityscraper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat; //
import android.support.v7.app.AppCompatActivity; //
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleMap mMap;
    ImageButton flora, fauna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    public void updateUI(final FirebaseUser user)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent goim=new Intent(MainActivity.this, MapsActivity.class);
                if (user !=null)
                {
                    MainActivity.this.startActivity(goim);
                    finish();
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this, SignIn.class);
                    MainActivity.this.startActivity(intent);
                    finish();
                }
            }
        },750);
    }
}
