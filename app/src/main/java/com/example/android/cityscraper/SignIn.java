package com.example.android.cityscraper;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull; //
import android.support.v7.app.AppCompatActivity; //
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
    private static final String TAG = "SignIn";
    private FirebaseAuth mAuth;
    private String email,pass;
    private EditText email_id;
    private EditText password;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Intent intent=getIntent();
        in = new Intent(SignIn.this, MapsActivity.class);
        mAuth = FirebaseAuth.getInstance();
    }

    protected void signUpint(View view) {
        Intent signup = new Intent(SignIn.this, SignUp.class);
        SignIn.this.startActivity(signup);
        finish();
    }

    public void signIn_b(View view) {
        email_id = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        email = email_id.getText().toString().trim();
        pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher("@").matches() || Patterns.EMAIL_ADDRESS.matcher(".com").matches()) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //SignIn.this.startActivity(in);
                            updateUI(user);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }

                    protected void updateUI(FirebaseUser user) {
                        if (user != null) {
                            SignIn.this.startActivity(in);
                            finish();
                        } else {
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}





