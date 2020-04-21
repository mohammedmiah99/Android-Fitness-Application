package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

public class DiaryActivity extends AppCompatActivity {
    private DatabaseHelper db;
    AnyChartView anyChartView;
    public String[] titles = {"Carbs","Protein","Fats"};
    EditText edit_notes,edit_kcal,edit_protein,edit_carbs,edit_fats;
    Button  btnAddFood, btnNewDay;
    private ImageView btnBack;
    TextView text_bmr, text_protein, text_carbs, text_fats, text_notes;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    Double bmr, kcals, gramsProtein, gramsCarbs, gramsFats, proteinCals, carbCals, fatsCals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        btnNewDay = findViewById(R.id.newDay);
        btnBack = findViewById(R.id.backArrow);
        text_bmr = (TextView) findViewById(R.id.txt_Kcal);
        text_protein = (TextView) findViewById(R.id.txt_protein);
        text_carbs = (TextView) findViewById(R.id.txt_carbs);
        text_fats = (TextView) findViewById(R.id.txt_fats);
        text_notes = (TextView) findViewById(R.id.txt_notes);
        anyChartView = findViewById(R.id.piechart);
        db = new DatabaseHelper(this);


        //h
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String w = dataSnapshot.child("weight").getValue().toString();
                Double weight = Double.parseDouble(w);
                String h = dataSnapshot.child("height").getValue().toString();
                Double height = Double.parseDouble(h);
                String a = dataSnapshot.child("age").getValue().toString();
                Double age = Double.parseDouble(a);

                String gender = dataSnapshot.child("gender").getValue().toString();
                String activity = dataSnapshot.child("activity").getValue().toString();
                String goal = dataSnapshot.child("goal").getValue().toString();

                if (gender.equals("Male"))
                    bmr = (10.0 * weight) + (6.25 * height) - (5.0 * age) + 5.0;
                else if (gender.equals("Female"))
                    bmr = (10.0 * weight) + (6.25 * height) - (5.0 * age) - 161.0;

                if (activity.equals("Sedentary")){
                    kcals = bmr * 1.2;
                }
                else if (activity.equals("Lightly Active")){
                    kcals = bmr * 1.375;
                }
                else if (activity.equals("Moderately Active")){
                    kcals = bmr * 1.55;
                }
                else if (activity.equals("Very Active")){
                    kcals = bmr * 1.725;
                }
                else if (activity.equals("Super Active")){
                    kcals = bmr * 1.9;
                }

                if(goal.equals("Gain Muscle")){
                    kcals = kcals + 500;
                }
                if (goal.equals("Lose Weight")){
                    kcals = kcals - 500;
                }

                Double tempKcal = kcals;

                gramsProtein = 2.7 * weight;
                proteinCals = gramsProtein * 4;

                tempKcal = tempKcal - proteinCals;

                fatsCals = (kcals * 25)/100;
                gramsFats = fatsCals/9;

                carbCals = tempKcal;
                gramsCarbs = carbCals/4;

                int intProteinG = (int) Math.rint(gramsProtein);
                String stringProteinG = Integer.toString(intProteinG);
                text_protein.setText("Protein: "+stringProteinG+"g");

                int intFatsG = (int) Math.rint(gramsFats);
                String stringFatsG = Integer.toString(intFatsG);
                text_fats.setText("Fats: "+stringFatsG+"g");

                int intCarbsG = (int) Math.rint(gramsCarbs);
                String stringCarbsG = Integer.toString(intCarbsG);
                text_carbs.setText("Carbohydrates: "+stringCarbsG+"g");

                int intkcal = (int) Math.rint(kcals);
                String stringkcals = Integer.toString(intkcal);
                text_bmr.setText("Calories: "+stringkcals+"kcal");

              //  int[] splita = {intCarbsG,intProteinG,intFatsG};
               // setupPieChart(splita);

                Cursor cursor = db.getFood();
                if (!(cursor.getCount()== 0)){
                    getTheFood();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnAddFood = findViewById(R.id.addFood);
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, HomeActivity.class);
                startActivity(intent); }
        });

        btnNewDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.removeAll();
                getTheFood();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.diary99);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.diary99:
                        return true;
                    case R.id.home99:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));
                        overridePendingTransition(0,0);
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


    }




    public void setupPieChart(int[] s){
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i = 0; i <titles.length; i++){
            dataEntries.add(new ValueDataEntry(titles[i],s[i]));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }



    private void getTheFood(){
        Cursor cursor = db.getFood();
        String[] movedKcal = new String[cursor.getCount()];
        int[] intMovedKcal = new int[movedKcal.length];

        String[] movedCarbs = new String[cursor.getCount()];
        int[] intMovedCarbs = new int[movedCarbs.length];

        String[] movedProtein = new String[cursor.getCount()];
        int[] intMovedProtein = new int[movedProtein.length];

        String[] movedFats = new String[cursor.getCount()];
        int[] intMovedFats = new int[movedFats.length];

        if(cursor.moveToFirst()) {
            int i = 0;
            do {
                movedKcal[i] =cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_KCALS));
                intMovedKcal[i] = Integer.parseInt(movedKcal[i]);

                movedCarbs[i] =cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CARBS));
                intMovedCarbs[i] = Integer.parseInt(movedCarbs[i]);

                movedProtein[i] =cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PROTEIN));
                intMovedProtein[i] = Integer.parseInt(movedProtein[i]);

                movedFats[i] =cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_FATS));
                intMovedFats[i] = Integer.parseInt(movedFats[i]);

                i++;
            } while (cursor.moveToNext());
        }

        int kcalSum = 0;
        for (int f : intMovedKcal)
            kcalSum += f;
        double newCal = kcals - kcalSum;
        int intNewCals = (int) Math.rint(newCal);
        String stringCals = Integer.toString(intNewCals);
        text_bmr.setText("Calories: "+stringCals+"kcal");

        int carbSum = 0;
        for (int f : intMovedCarbs)
            carbSum += f;
        double newCarb = gramsCarbs - carbSum;
        int intNewCarb = (int) Math.rint(newCarb);
        String stringCarbsG = Integer.toString(intNewCarb);
        text_carbs.setText("Carbohydrates: "+stringCarbsG+"g");

        int proteinSum = 0;
        for (int f : intMovedProtein)
            proteinSum += f;
        double newProtein = gramsProtein - proteinSum;
        int intNewProtein = (int) Math.rint(newProtein);
        String stringProteinG = Integer.toString(intNewProtein);
        text_protein.setText("Protein: "+stringProteinG+"g");

        int fatSum = 0;
        for (int f : intMovedFats)
            fatSum += f;
        double newFats = gramsFats - fatSum;
        int intNewFats = (int) Math.rint(newFats);
        String stringFatsG = Integer.toString(intNewFats);
        text_fats.setText("Fats: "+stringFatsG+"g");

        int[] splitas = {intNewCarb,intNewProtein,intNewFats};
        setupPieChart(splitas);
    }


}



