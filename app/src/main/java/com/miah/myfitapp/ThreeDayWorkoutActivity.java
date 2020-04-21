package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ThreeDayWorkoutActivity extends AppCompatActivity {

    private ImageView btnWorkout1, btnWorkout2, btnWorkout3, btnback;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_day_workout);

        btnback = findViewById(R.id.back);
        btnWorkout1 = findViewById(R.id.sd_wrkt1);
        btnWorkout2 = findViewById(R.id.sd_wrkt2);
        btnWorkout3 = findViewById(R.id.sd_wrkt3);



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i); }
        });
        btnWorkout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PushWrktActivity.class);
                startActivity(i); }
        });
        btnWorkout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToMain = new Intent(getApplicationContext(), PullWrktActivity.class);
                startActivity(intToMain); }
        });
        btnWorkout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToMain = new Intent(getApplicationContext(), LegWrktActivity.class);
                startActivity(intToMain); }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //bottomNavigationView.setSelectedItemId(R.id.workout99);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.workout99:
                        startActivity(new Intent(getApplicationContext()
                                ,WorkoutLogActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home99:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.diary99:
                        startActivity(new Intent(getApplicationContext()
                                ,DiaryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
