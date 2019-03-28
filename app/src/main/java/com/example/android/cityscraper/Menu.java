package com.example.android.cityscraper;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull; //
import android.support.v7.app.AppCompatActivity; //
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

//import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Intent out;
    String TAG = "Menu";
    String name, emailid;
    TextView dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            emailid = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

        dp=(TextView)findViewById(R.id.name_id);
        dp.setText(name);
    }

        @Override
        public void onBackPressed () {
            Intent map = new Intent(Menu.this, MapsActivity.class);
            startActivity(map);
            finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        }

        public void chatT (View v)
        {
            Intent i = new Intent(Menu.this, ChatThreads.class);
            startActivity(i);
        }

        public void logout (View view)
        {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            try {
                mAuth.signOut();
                out = new Intent(Menu.this, SignIn.class);
                Toast.makeText(this, "User Sign out!", Toast.LENGTH_SHORT).show();
                startActivity(out);
                finish();
            } catch (Exception e) {
                Log.e(TAG, "onClick: Exception " + e.getMessage(), e);
            }
        }

}
