package com.miah.myfitapp;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity {
    CardView btnLogout, btnViewDiary, btnViewWorkout, btnSettings, btnWorkoutLog,btnAlarm, btnpt;
    TextView name,txt_ptarea;
    private ImageView ptimage;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnWorkoutLog = findViewById(R.id.workoutLog);
        btnLogout = findViewById(R.id.logout);
        btnViewWorkout = findViewById(R.id.workout);
        btnViewDiary = findViewById(R.id.diary);
        btnSettings = findViewById(R.id.settings);
        btnAlarm = findViewById(R.id.alarm);
        btnpt = findViewById(R.id.pt);
        txt_ptarea = findViewById(R.id.txt_ptarea);
        name = (TextView) findViewById(R.id.txt_ffname);
        ptimage = findViewById(R.id.ptimage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home99);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.diary99:
                        startActivity(new Intent(getApplicationContext()
                        ,DiaryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home99:
                        return true;
                    case R.id.workout99:
                        startActivity(new Intent(getApplicationContext()
                                ,WorkoutLogActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        //name.setText(user.getUid());

        //reference = FirebaseDatabase.getInstance().getReference().child(user.getUid());
        //name.setText(reference.child("User").child("firstname").getKey());

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fname = dataSnapshot.child("firstname").getValue().toString();
                String pt = dataSnapshot.child("pt").getValue().toString();
                String id = dataSnapshot.getKey();
                name.setText("Welcome back " + fname);


                if(pt.equals("No")){
                    txt_ptarea.setText("Your personal key is: \n "+id + "\nGive this to your Personal Trainer!");
                }else{
                    ptimage.setVisibility(View.VISIBLE);
                    txt_ptarea.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intToMain); }
                });

        btnViewDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DiaryActivity.class));
            }
        });

        btnWorkoutLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, WorkoutLogActivity.class));
            }
        });

        btnViewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String amount = dataSnapshot.child("amount").getValue().toString();
                        String special = dataSnapshot.child("special").getValue().toString();

                        if (special.equals("PPL6")){
                            startActivity(new Intent(HomeActivity.this, SixDayWorkoutActivity.class));
                        } else if (special.equals("UL4")){
                            startActivity(new Intent(HomeActivity.this, FourDayWorkoutActivity.class));
                        }else if (special.equals("PPL3")){
                            startActivity(new Intent(HomeActivity.this, ThreeDayWorkoutActivity.class));
                        }


                        else if (amount.equals("2")){
                            startActivity(new Intent(HomeActivity.this, TwoDayWorkoutActivity.class));
                        }
                        else if (amount.equals("3")){
                            startActivity(new Intent(HomeActivity.this, ThreeDayWorkoutActivity.class));
                        }
                        else if (amount.equals("4")){
                            startActivity(new Intent(HomeActivity.this, FourDayWorkoutActivity.class));
                        }
                        else if (amount.equals("5")){
                            startActivity(new Intent(HomeActivity.this, FiveDayWorkoutActivity.class));
                        }
                        else if (amount.equals("6")){
                            startActivity(new Intent(HomeActivity.this, SixDayWorkoutActivity.class));
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BlogActivity.class));
            }
        });

        btnpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String amount = dataSnapshot.child("pt").getValue().toString();

                        if(amount.equals("Yes")){
                            startActivity(new Intent(HomeActivity.this, PTActivity.class));
                        }
                        else if (amount.equals("No")){

                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });            }
        });

    }


}
