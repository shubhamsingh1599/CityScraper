package com.example.android.cityscraper;

import android.content.Intent;
import android.support.annotation.NonNull; //
import android.support.v7.app.AppCompatActivity; //
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "SignUp";
    EditText emailid;
    EditText name;
    EditText pass;
    EditText passr;
    String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent signup = getIntent();
        mAuth = FirebaseAuth.getInstance();
        emailid=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.password);
        passr=(EditText)findViewById(R.id.conf_pass);
        name=(EditText)findViewById(R.id.name);
        name1 = name.getText().toString();
    }

    public void updateUI(FirebaseUser user)
    {
        UserProfileChangeRequest profileUpdates=new UserProfileChangeRequest.Builder()
                .setDisplayName(name1)
                .build();

        if (user !=null)
        {
            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent goi=new Intent(SignUp.this, MapsActivity.class);
                            Toast.makeText(getApplicationContext(), "SignUp Successful!", Toast.LENGTH_SHORT).show();
                            SignUp.this.startActivity(goi);
                            finish();
                        }
                    }
            );
        }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(SignUp.this,SignIn.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    protected void createAccount(View view)
    {
        String email=emailid.getText().toString().trim();
        String password=pass.getText().toString().trim();
        String password_re=passr.getText().toString().trim();

        if (TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher("@").matches() || Patterns.EMAIL_ADDRESS.matcher(".com").matches()) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password_re.equals(password))
        {
            Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUp.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    // ...
                }
            });
    }
}
