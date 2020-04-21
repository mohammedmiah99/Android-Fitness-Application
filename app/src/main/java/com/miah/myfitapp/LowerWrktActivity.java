package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LowerWrktActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] s1;
    String[] s2;
    String[] s3;
    String[] s4;
    String[] s5;
    String[] t1;
    String[] v1;
    int[] images = {R.drawable.squat, R.drawable.stiffimage, R.drawable.legpressimage, R.drawable.goodmorningimage,
            R.drawable.calfraise, R.drawable.legex};
    int[] gifImages = {R.drawable.bbback_squat, R.drawable.stiffgif, R.drawable.legpressgif, R.drawable.goodmorninggif,
            R.drawable.calfraisegif, R.drawable.legexgif};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_wrkt);

        recyclerView = findViewById(R.id.recyclerView);
        ImageView backarrow2 = findViewById(R.id.backArrow2);

        s1 = getResources().getStringArray(R.array.exercisesLower);
        s2 = getResources().getStringArray(R.array.descriptionLower);
        s3 = getResources().getStringArray(R.array.setsLower);
        s4 = getResources().getStringArray(R.array.repsLower);
        t1 = getResources().getStringArray(R.array.timerLower);
        v1 = getResources().getStringArray(R.array.urlLower);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,s3,s4,images,gifImages,t1,v1);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backarrow2.setOnClickListener(v -> {
            Intent intent = new Intent(LowerWrktActivity.this, HomeActivity.class);
            startActivity(intent);
        });

    }
}
