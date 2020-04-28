package com.miah.myfitapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdRegActivity extends AppCompatActivity {
    Spinner spinAmount, spinExp, spinActvity, spinGoal, spinPt;
    Button btnLogout, btnReg;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Intent intent = getIntent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_reg);
        btnLogout = findViewById(R.id.logout);
        btnReg = findViewById(R.id.reg);
        spinAmount = (Spinner) findViewById(R.id.spin_amount);
        spinExp = (Spinner) findViewById(R.id.spin_experience);
        spinActvity = (Spinner) findViewById(R.id.spin_activity);
        spinGoal = (Spinner) findViewById(R.id.spin_goal);
        spinPt = (Spinner) findViewById(R.id.spin_pt);


        final String lastname = getIntent().getStringExtra("lastname");
        final String firstname = getIntent().getStringExtra("firstname");
        final String email = getIntent().getStringExtra("email");

        final String gender = getIntent().getStringExtra("gender");
        final String weight = getIntent().getStringExtra("weight");
        final String age = getIntent().getStringExtra("age");
        final String height = getIntent().getStringExtra("height");


        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goal = spinGoal.getSelectedItem().toString();
                String amount = spinAmount.getSelectedItem().toString();
                String exp = spinExp.getSelectedItem().toString();
                String activity = spinActvity.getSelectedItem().toString();
                String pt = spinPt.getSelectedItem().toString();
                String special = "No";

                User information = new User(
                        firstname,
                        lastname,
                        email,
                        weight,
                        height,
                        age,
                        gender,
                        goal,
                        amount,
                        activity,
                        exp,
                        pt,
                        special
                        );

                FirebaseDatabase.getInstance().getReference("User")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent i = new Intent(ThirdRegActivity.this,HomeActivity.class);
                        startActivity(i);

                    }
                });




            }
        });

    }
}
