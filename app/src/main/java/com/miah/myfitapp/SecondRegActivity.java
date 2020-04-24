package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

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
    SwitchCompat switchCompat;
    EditText age, gender, height, weight;
    Spinner spinGender;
    Button btnNext,btnLogout;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String  b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_reg);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        btnNext = findViewById(R.id.next);
        switchCompat = findViewById(R.id.imperialButton12);
        age = findViewById(R.id.txt_age);
        spinGender = (Spinner) findViewById(R.id.spin_gender);
        //gender = findViewById(R.id.txt_gender);
        height = findViewById(R.id.txt_height);
        weight = findViewById(R.id.txt_weight);
        Intent intent = getIntent();

        final String firstname = intent.getStringExtra("firstname");
        final String lastname = intent.getStringExtra("lastname");
        final String email = intent.getStringExtra("email");
        b = "false";


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final String age1 = age.getText().toString();
                final String height1 = height.getText().toString();
                String weight1 = weight.getText().toString();
                final String gender1 = spinGender.getSelectedItem().toString();

                 */
                String weight1="";
                String height1="";
                final String age1 = age.getText().toString();
                final String gender1 = spinGender.getSelectedItem().toString();

                if (b.equals("true")){
                    double d = Double.parseDouble(weight.getText().toString())/2.205;
                    int y = (int) Math.round(d);
                    weight1 = Integer.toString(y);

                    double dx = Double.parseDouble(height.getText().toString())*30.48;
                    int yx = (int) Math.round(dx);
                    height1 = Integer.toString(yx);

                }
                else if (b.equals("false")){
                    weight1 = weight.getText().toString().trim();

                    if(!height.getText().toString().trim().isEmpty()){
                    double ddd = Double.parseDouble(height.getText().toString().trim());
                    int gg = (int) Math.round(ddd);
                    height1 = Integer.toString(gg);
                    }
                }


                if (!(!age1.isEmpty() && Integer.parseInt(age1)<100 && Integer.parseInt(age1)>=18)) {
                    age.setError("Please enter your age (18-99)");
                    age.requestFocus();
                } else if (!(!weight1.isEmpty() && Integer.parseInt(weight1)<=600 && Integer.parseInt(weight1)>=30)) {
                    weight.setError("Please enter your Weight in kilograms");
                    weight.requestFocus();
                }
                else if (!(!height1.isEmpty() && Integer.parseInt(height1)<=250 && Integer.parseInt(height1)>=4)) {
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

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCompat.isChecked()){
                    b = "true";
                    height.setHint(" Your Height (Ft)");
                    weight.setHint(" Your Weight (lbs)");
                }else{
                    b = "false";
                    height.setHint(" Your Height (cm)");
                    weight.setHint(" Your Weight (kg)");
                }
            }
        });


    }

}
