package com.example.android.cityscraper;

import android.support.annotation.NonNull; //
import android.support.annotation.Nullable; //
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle; //
import android.support.v7.widget.LinearLayoutManager; //
import android.support.v7.widget.RecyclerView; //
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

public class ChatThreads extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference messagedb;
    FirebaseAuth auth;
    FirebaseDatabase database;
    MessageAdapter messageAdapter;
    UserInformation u;
    List<Message> messages;

    RecyclerView rvMessage;
    EditText etMessage;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_threads);

        init();

    }

    private void init() {
        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        u=new UserInformation();

        rvMessage=(RecyclerView)findViewById(R.id.rvMessage);
        etMessage=(EditText) findViewById(R.id.etMessage);
        imageButton=(ImageButton)findViewById(R.id.btnSend);

        imageButton.setOnClickListener(this);
        messages = new ArrayList<Message>();

    }

    @Override
    public void onClick(View v) {
        if(!TextUtils.isEmpty(etMessage.getText().toString()))
        {
            Message message=new Message(etMessage.getText().toString(),u.getName());
            etMessage.setText("");
            messagedb.push().setValue(message);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseUser currentUser=auth.getCurrentUser();

        u.setUid(currentUser.getUid());
        u.setEmail(currentUser.getEmail());

        database.getReference("UserInformation").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u=dataSnapshot.getValue(UserInformation.class);
                u.setUid(currentUser.getUid());
                AllMethods.name=u.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        messagedb=database.getReference("messages");
        messagedb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message=dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                messages.add(message);
                displayMessages(messages);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message=dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());

                List<Message> newMessages=new ArrayList<Message>();

                for(Message m:messages)
                {
                    if(m.getKey().equals(message.getKey()))
                    {
                        newMessages.add(message);
                    }
                    else
                    {
                        newMessages.add(m);
                    }
                }
                messages=newMessages;
                displayMessages(messages);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Message message=dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());

                List<Message> newMessages=new ArrayList<Message>();

                for(Message m:messages)
                {
                    if(m.getKey().equals(message.getKey()))
                    {
                        newMessages.add(m);
                    }
                }
                messages=newMessages;
                displayMessages(messages);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void displayMessages(List<Message> messages) {
        rvMessage.setLayoutManager(new LinearLayoutManager(ChatThreads.this));
        messageAdapter=new MessageAdapter(ChatThreads.this,messages,messagedb);
        rvMessage.setAdapter(messageAdapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        messages=new ArrayList<>();
    }
}

