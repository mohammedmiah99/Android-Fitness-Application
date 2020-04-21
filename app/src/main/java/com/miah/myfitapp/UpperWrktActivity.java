package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UpperWrktActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] s1;
    String[] s2;
    String[] s3;
    String[] s4;
    String[] s5;
    String[] t1;
    String[] v1;
    int[] images = {R.drawable.benchimage, R.drawable.latpullimage, R.drawable.inclinebenchdiagram, R.drawable.bentoverrowimage,
            R.drawable.dbshoulderimage, R.drawable.lateralimage};
    int[] gifImages = {R.drawable.benchpress, R.drawable.latpullgif, R.drawable.inclinegif, R.drawable.bentoverrowgif,
            R.drawable.db_seated_shoulder_press, R.drawable.lateralraise};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper_wrkt);

        recyclerView = findViewById(R.id.recyclerView);
        ImageView backarrow2 = findViewById(R.id.backArrow2);

        s1 = getResources().getStringArray(R.array.exercisesUpper);
        s2 = getResources().getStringArray(R.array.descriptionUpper);
        s3 = getResources().getStringArray(R.array.setsUpper);
        s4 = getResources().getStringArray(R.array.repsUpper);
        t1 = getResources().getStringArray(R.array.timerUpper);
        v1 = getResources().getStringArray(R.array.urlUpper);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,s3,s4,images,gifImages,t1,v1);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backarrow2.setOnClickListener(v -> {
            Intent intent = new Intent(UpperWrktActivity.this, HomeActivity.class);
            startActivity(intent);
        });

    }
}
