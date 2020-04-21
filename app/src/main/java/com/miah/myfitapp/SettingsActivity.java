package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {
private Button btnSave, btnContact;
private ImageView btnBack;
private EditText editFName,editLName,editAge,editHeight, editWeight;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnContact = findViewById(R.id.btnContact);
        btnBack = findViewById(R.id.back);
        btnSave = findViewById(R.id.save);
        editFName = findViewById(R.id.editFName);
        editLName = findViewById(R.id.editLName);
        editAge = findViewById(R.id.editAge);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
            }
        });
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, ContactActivity.class));
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String newFName = editFName.getText().toString();
                final String newLName = editLName.getText().toString();
                final String newAge = editAge.getText().toString();
                final String newWeight = editWeight.getText().toString();
                final String newHeight = editHeight.getText().toString();

                auth = FirebaseAuth.getInstance();
                user = auth.getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());


                if (!newFName.isEmpty() && newFName.length()>2&&newFName.length()<15) {
                    reference.child("firstname").setValue(newFName);
                    Toast.makeText(SettingsActivity.this, "First name has been changed!", Toast.LENGTH_SHORT).show();
                }else{
                    editFName.setError("Please enter your First Name");
                    editFName.requestFocus();
                }
                if (!newLName.isEmpty() && newLName.length()>2&&newLName.length()<15) {
                    reference.child("lastname").setValue(newLName);
                    Toast.makeText(SettingsActivity.this, "Last name has been changed!", Toast.LENGTH_SHORT).show();
                }else{
                    editLName.setError("Please enter your Last Name");
                    editLName.requestFocus();
                }
                if (!newAge.isEmpty() && Integer.parseInt(newAge)<100 && Integer.parseInt(newAge)>=18) {
                    reference.child("age").setValue(newAge);
                    Toast.makeText(SettingsActivity.this, "Age has been changed!", Toast.LENGTH_SHORT).show();

                }else{
                    editAge.setError("Please enter your age (18-99)");
                    editAge.requestFocus();
                }
                if (!newWeight.isEmpty() && Integer.parseInt(newWeight)<=300 && Integer.parseInt(newWeight)>=40) {
                    reference.child("weight").setValue(newWeight);
                    Toast.makeText(SettingsActivity.this, "Weight has been changed!", Toast.LENGTH_SHORT).show();
                }else{
                    editWeight.setError("Please enter your weight (40-300)");
                    editWeight.requestFocus();
                }
                if (!newHeight.isEmpty() && Integer.parseInt(newHeight)<=230 && Integer.parseInt(newHeight)>=140) {
                    reference.child("height").setValue(newHeight);
                    Toast.makeText(SettingsActivity.this, "Height has been changed!", Toast.LENGTH_SHORT).show();
                }else{
                    editHeight.setError("Please enter your Height");
                    editHeight.requestFocus();
                }

                editWeight.setText("");
                editHeight.setText("");
                editFName.setText("");
                editLName.setText("");
                editAge.setText("");

            };


    });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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
