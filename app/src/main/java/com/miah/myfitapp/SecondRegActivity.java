package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondRegActivity extends AppCompatActivity {

    EditText age, gender, height, weight;
    Spinner spinGender;
    Button btnNext,btnLogout;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_reg);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        btnNext = findViewById(R.id.next);
        age = findViewById(R.id.txt_age);
        spinGender = (Spinner) findViewById(R.id.spin_gender);
        //gender = findViewById(R.id.txt_gender);
        height = findViewById(R.id.txt_height);
        weight = findViewById(R.id.txt_weight);
        Intent intent = getIntent();

        final String firstname = intent.getStringExtra("firstname");
        final String lastname = intent.getStringExtra("lastname");
        final String email = intent.getStringExtra("email");


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String weight1 = weight.getText().toString();
                final String age1 = age.getText().toString();
                final String height1 = height.getText().toString();
                final String gender1 = spinGender.getSelectedItem().toString();


                if (!(!age1.isEmpty() && Integer.parseInt(age1)<100 && Integer.parseInt(age1)>=18)) {
                    age.setError("Please enter your age (18-99)");
                    age.requestFocus();
                } else if (!(!weight1.isEmpty() && Integer.parseInt(weight1)<=300 && Integer.parseInt(weight1)>=40)) {
                    weight.setError("Please enter your Weight in kilograms");
                    weight.requestFocus();
                } else if (!(!height1.isEmpty() && Integer.parseInt(height1)<=230 && Integer.parseInt(height1)>=140)) {
                    height.setError("Please enter your Height in centimeters");
                    height.requestFocus();
                } else if (gender1.isEmpty()) {
                    gender.setError("Please enter your Gender");
                    gender.requestFocus();
                } else {

                                Intent intent = new Intent(SecondRegActivity.this, ThirdRegActivity.class);
                               intent.putExtra("age", age1);
                                intent.putExtra("weight", weight1);
                                intent.putExtra("height", height1);
                                intent.putExtra("gender", gender1);

                                intent.putExtra("firstname", firstname);
                                intent.putExtra("lastname", lastname);
                                intent.putExtra("email", email);

                                startActivity(intent);

                }
            }
        });


    }

}
