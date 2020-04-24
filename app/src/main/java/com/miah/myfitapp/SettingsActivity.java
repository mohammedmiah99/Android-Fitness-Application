package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

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
    String  b;
    SwitchCompat switchCompat;

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

        switchCompat = findViewById(R.id.imperialButton2);
        b = "false";


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
                //final String newWeight = editWeight.getText().toString();
                //final String newHeight = editHeight.getText().toString();
                String newWeight="";
                String newHeight="";

              /*  if (b.equals("true")){
                    double d = Double.parseDouble(editWeight.getText().toString())/2.205;
                    int y = (int) Math.round(d);
                    newWeight = Integer.toString(y);

                    double dx = Double.parseDouble(editHeight.getText().toString())*30.48;
                    int yx = (int) Math.round(dx);
                    newHeight = Integer.toString(yx);

                }
                else if (b.equals("false")){
                    newWeight = editWeight.getText().toString().trim();

                    if(!editHeight.getText().toString().trim().isEmpty()){
                        double ddd = Double.parseDouble(editHeight.getText().toString().trim());
                        int gg = (int) Math.round(ddd);
                        newHeight = Integer.toString(gg);
                    }
                }

               */



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
                if (!editWeight.getText().toString().trim().isEmpty() && Integer.parseInt(editWeight.getText().toString().trim())<=600
                        && Integer.parseInt(editWeight.getText().toString().trim())>=30) {
                    if (b.equals("true")){
                        double d = Double.parseDouble(editWeight.getText().toString())/2.205;
                        int y = (int) Math.round(d);
                        newWeight = Integer.toString(y);
                    }
                    else if (b.equals("false")){
                        newWeight = editWeight.getText().toString().trim();
                    }
                    reference.child("weight").setValue(newWeight);
                    Toast.makeText(SettingsActivity.this, "Weight has been changed!", Toast.LENGTH_SHORT).show();
                }else{
                    editWeight.setError("Please enter your weight (30-600)");
                    editWeight.requestFocus();
                }
                if (!editHeight.getText().toString().trim().isEmpty() && Double.parseDouble(editHeight.getText().toString().trim())<=250.0
                        && Double.parseDouble(editHeight.getText().toString().trim())>=4.0) {
                    if (b.equals("true")){
                        double dx = Double.parseDouble(editHeight.getText().toString())*30.48;
                        int yx = (int) Math.round(dx);
                        newHeight = Integer.toString(yx);

                    }
                    else if (b.equals("false")){
                        if(!editHeight.getText().toString().trim().isEmpty()){
                            double ddd = Double.parseDouble(editHeight.getText().toString().trim());
                            int gg = (int) Math.round(ddd);
                            newHeight = Integer.toString(gg);
                        }
                    }
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

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCompat.isChecked()){
                    b = "true";
                    editHeight.setHint("Change Your Height (Ft)");
                    editWeight.setHint("Change Your Weight (lbs)");
                }else{
                    b = "false";
                    editHeight.setHint("Change Your Height (cm)");
                    editWeight.setHint("Change Your Weight (kg)");
                }
            }
        });
}
}
