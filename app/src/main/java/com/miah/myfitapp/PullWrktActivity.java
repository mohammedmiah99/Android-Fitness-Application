package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PullWrktActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[], s3[], s4[],s5[],t1[], v1[];
    int images[] = {R.drawable.pullup, R.drawable.row, R.drawable.facepull,R.drawable.reversecurl,
    R.drawable.hammercurl, R.drawable.supercurl};
    int gifImages[] = {R.drawable.pullupgif, R.drawable.dbrowgif, R.drawable.facepullgif,R.drawable.reversecurlgif,
            R.drawable.hammercurlgif, R.drawable.bicepcurl};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_wrkt);

        recyclerView = findViewById(R.id.recyclerView);
        ImageView backarrow2 = findViewById(R.id.backArrow2);


        s1 = getResources().getStringArray(R.array.exercises);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.sets);
        s4 = getResources().getStringArray(R.array.reps);
        t1 = getResources().getStringArray(R.array.timer);
        v1 = getResources().getStringArray(R.array.url);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,s3,s4,images,gifImages,t1,v1);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backarrow2.setOnClickListener(v -> {
            Intent intent = new Intent(PullWrktActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
