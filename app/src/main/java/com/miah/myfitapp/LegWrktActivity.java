package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LegWrktActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] s1;
    String[] s2;
    String[] s3;
    String[] s4;
    String[] s5;
    String[] t1;
    String[] v1;
    int[] images = {R.drawable.squat, R.drawable.deadlift, R.drawable.hipthrusts, R.drawable.dblunge,
            R.drawable.legex, R.drawable.legcurl};
    int[] gifImages = {R.drawable.bbback_squat, R.drawable.deadliftgif, R.drawable.hipthrustsgif, R.drawable.dblungegif,
            R.drawable.legexgif, R.drawable.legcurlgif};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_wrkt);

        recyclerView = findViewById(R.id.recyclerView);
        ImageView backarrow2 = findViewById(R.id.backArrow2);

        s1 = getResources().getStringArray(R.array.exercisesLeg);
        s2 = getResources().getStringArray(R.array.descriptionLeg);
        s3 = getResources().getStringArray(R.array.setsLeg);
        s4 = getResources().getStringArray(R.array.repsLeg);
        t1 = getResources().getStringArray(R.array.timerLeg);
        v1 = getResources().getStringArray(R.array.urlLegs);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,s3,s4,images,gifImages,t1,v1);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backarrow2.setOnClickListener(v -> {
            Intent intent = new Intent(LegWrktActivity.this, HomeActivity.class);
            startActivity(intent);
        });

    }
}
