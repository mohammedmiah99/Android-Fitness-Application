package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PushWrktActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] s1;
    String[] s2;
    String[] s3;
    String[] s4;
    String[] s5;
    String[] t1;
    String[] v1;
    int[] images = {R.drawable.benchimage, R.drawable.dbshoulderimage, R.drawable.dipimage, R.drawable.cableflyimage,
            R.drawable.skullcrushimage, R.drawable.lateralimage};
    int[] gifImages = {R.drawable.benchpress, R.drawable.db_seated_shoulder_press, R.drawable.dip, R.drawable.cablefly,
            R.drawable.dbskullcrusher, R.drawable.lateralraise};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_wrkt);

        recyclerView = findViewById(R.id.recyclerView77);
        ImageView backarrow2 = findViewById(R.id.backArrow277);

        s1 = getResources().getStringArray(R.array.exercisesPush);
        s2 = getResources().getStringArray(R.array.descriptionPush);
        s3 = getResources().getStringArray(R.array.setsPush);
        s4 = getResources().getStringArray(R.array.repsPush);
        t1 = getResources().getStringArray(R.array.timerPush);
        v1 = getResources().getStringArray(R.array.urlPush);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,s3,s4,images,gifImages,t1,v1);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backarrow2.setOnClickListener(v -> {
            Intent intent = new Intent(PushWrktActivity.this, HomeActivity.class);
            startActivity(intent);
        });

    }
}
