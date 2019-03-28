package com.example.android.cityscraper;

import androidx.annotation.NonNull;
public class Message {
    String message;
    String name;
    String key;

    public Message() {}

    public Message(String message, String name) {
        this.message = message;
        this.name = name;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) { this.key = key; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @NonNull
    @Override
    public String toString() {
        return "Message{"+
                "message= "+message+'\''+
                ", name= "+name+'\''+
                ", key= "+key+'\''+
                '}';
    }
}

