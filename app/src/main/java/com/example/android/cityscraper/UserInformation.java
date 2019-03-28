package com.example.android.cityscraper;

import androidx.annotation.NonNull;

public class UserInformation {
    public String Burglary;
    public String Drugs;
    public String Kidnappings;
    public String Murder;
    public String PUBG;
    String uid;
    String name,email;

    public UserInformation(){}

    public String getBurglary() {
        return Burglary;
    }

    public void setBurglary(String burglary) {
        Burglary = burglary;
    }

    public String getDrugs() {
        return Drugs;
    }

    public void setDrugs(String drugs) {
        Drugs = drugs;
    }

    public String getKidnappings() {
        return Kidnappings;
    }

    public void setKidnappings(String kidnappings) {
        Kidnappings = kidnappings;
    }

    public String getMurder() {
        return Murder;
    }

    public void setMurder(String murder) {
        Murder = murder;
    }

    public String getPUBG() {
        return PUBG;
    }

    public void setPUBG(String PUBG) {
        this.PUBG = PUBG;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserInformation{"+
                "uid= "+uid+'\''+
                ", name= "+name+'\''+
                ", email= "+email+'\''+
                '}';
    }
}
