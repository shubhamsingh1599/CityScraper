package com.example.android.cityscraper;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull; //
import android.support.v7.app.AppCompatActivity; //
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

//import androidx.appcompat.app.AppCompatActivity;

public class Nearby extends AppCompatActivity {
    String cityi;
    //DatabaseReference databaseCity;
    ListView ListViewCity;

    //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("/AIzaSyDio1JKTGApRuGyrkzkq5mrM7TQJrJsw4Y");
    public String Burglary;
    public String Drugs;
    public String Kidnappings;
    public String Murder;
    public String PUBG;
    float kid,bur,mur,dr,pu;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        Intent n=getIntent();
        Bundle bundle = getIntent().getExtras();
        cityi = bundle.getString("next");

        cityi=toTitleCase(cityi);

        if(cityi.equals("Andheri"))
        {
            kid=20;
            bur=350;
            mur=5;
            dr=2000;
            pu=4500;
        }
        else if(cityi.equals("Nerul")){
            kid=10;
            bur=120;
            mur=2;
            dr=1000;
            pu=2750;
        }
        else if(cityi.equals("Bandra")){
            kid=17;
            bur=200;
            mur=4;
            dr=1700;
            pu=3750;
        }
        else if(cityi.equals("Vile Parle")){
            kid=13;
            bur=150;
            mur=3;
            dr=1250;
            pu=3000;
        }
        else if(cityi.equals("Vileparle"))
        {
            kid=13;
            bur=150;
            mur=3;
            dr=1250;
            pu=3000;
        }
        else
        {
            cityi+="(No Data Available)";
        }

       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        /*
        DatabaseReference City = rootRef.child("City");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();

                    DatabaseReference keyRef = rootRef.child("City").child(cityi).child("Kidnappings");
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                String name = ds1.getValue(String.class);
                                Log.d("TAG", name);
                                Kidnappings=name;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    keyRef.addListenerForSingleValueEvent(valueEventListener);

                    DatabaseReference keyRe = rootRef.child("City").child(cityi).child("Drugs");
                    ValueEventListener valueEventListene = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                String name = ds1.getValue(String.class);
                                Log.d("TAG", name);
                                Drugs=name;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    keyRe.addListenerForSingleValueEvent(valueEventListene);

                    DatabaseReference keyR = rootRef.child("City").child(cityi).child("Bulglary");
                    ValueEventListener valueEventListen = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                String name = ds1.getValue(String.class);
                                Log.d("TAG", name);
                                Burglary=name;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    keyR.addListenerForSingleValueEvent(valueEventListen);

                    DatabaseReference keyr = rootRef.child("City").child(cityi).child("Murder");
                    ValueEventListener valueEventListe = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                String name = ds1.getValue(String.class);
                                Log.d("TAG", name);
                                Murder=name;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    keyr.addListenerForSingleValueEvent(valueEventListe);

                    DatabaseReference ke = rootRef.child("City").child(cityi).child("PUBG");
                    ValueEventListener valueEventList = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                String name = ds1.getValue(String.class);
                                Log.d("TAG", name);
                                PUBG=name;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    ke.addListenerForSingleValueEvent(valueEventList);
                }
                kid=Float.valueOf(Kidnappings);
                dr=Float.valueOf(Drugs);
                mur=Float.valueOf(Murder);
                bur=Float.valueOf(Burglary);
                pu=Float.valueOf(PUBG);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        City.addListenerForSingleValueEvent(eventListener);
        */

        TextView c=(TextView)findViewById(R.id.count);
        c.setText(cityi);

        HorizontalBarChart chart = (HorizontalBarChart)findViewById(R.id.chart);

        BarDataSet set1;
        set1 = new BarDataSet(getDataSet(), "Numbers");

        set1.setColors(Color.parseColor("#F78B5D"),
                Color.parseColor("#FCB232"),
                Color.parseColor("#FDD930"),
                Color.parseColor("#ADD137"),
                Color.parseColor("#A0C25A"));

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        // hide Y-axis
        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(false);

        // custom X-axis labels
        String[] values = new String[]{"Kidnappings", "Burglary", "Murder", "Drugs", "PUBG"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));

        chart.setData(data);

        // custom description
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

        // hide legend
        chart.getLegend().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setNoDataText("No Data Available!");
        chart.setDrawBorders(false);

        chart.animateY(1000);
        chart.invalidate();
        xAxis.setGranularity(1f);
        chart.setScaleEnabled(false);
    }

    /*private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren())
        {
            UserInformation uInfo=new UserInformation();
            uInfo.setBurglary(ds.child(City).getValue(UserInformation.class).getBurglary()));
        }
    }*/

    public class MyXAxisValueFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public MyXAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }

    }

    private ArrayList<BarEntry> getDataSet() {

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        BarEntry v1e1 = new BarEntry(0, kid);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(1, bur);
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(2, dr);
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(3, mur);
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(4, pu);
        valueSet1.add(v1e5);

        return valueSet1;
    }

    public void onBackPressed(View view)
    {
        Intent map=new Intent(Nearby.this, MapsActivity.class);
        startActivity(map);
        finish();
    }

    public static String toTitleCase(String input) {
        String f="";
        if(input!=null) {
            input = input.toLowerCase();
            char c = input.charAt(0);
            String s = new String("" + c);
            f = s.toUpperCase();
            return f + input.substring(1);
        }
        else
        {
            return "Vile Parle";
        }
    }
}
