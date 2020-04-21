package com.miah.myfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText emailId, password, firstname, lastname, password2;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        mFirebaseAuth = FirebaseAuth.getInstance();
        firstname = findViewById(R.id.txt_fname);
        lastname = findViewById(R.id.txt_lname);
        emailId = findViewById(R.id.txt_email);
        password = findViewById(R.id.txt_password);
        password2 = findViewById(R.id.txt_password2);
        btnSignUp = findViewById(R.id.signUp);
        tvSignIn = findViewById(R.id.textView);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String pwd2 = password2.getText().toString();
                final String fname = firstname.getText().toString();
                final String lname = lastname.getText().toString();

                if (email.isEmpty()) {
                    emailId.setError("Please enter your Email");
                    emailId.requestFocus();
                } else if (fname.isEmpty()) {
                    firstname.setError("Please enter your First Name");
                    firstname.requestFocus();
                }else if(fname.length()< 2|fname.length()>15){
                    firstname.setError("First name must be between 2-15 characters long");
                    firstname.requestFocus();
                } else if (lname.isEmpty()) {
                    lastname.setError("Please enter your Last Name");
                    lastname.requestFocus();
                }else if(lname.length()< 2|lname.length()>15){
                    lastname.setError("Last name must be between 2-15 characters long");
                    lastname.requestFocus();
                } else if (!pwd.equals(pwd2)) {
                    password2.setError("Passwords do not match");
                    password2.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter a Password");
                    password.requestFocus();
                } else if (pwd2.isEmpty()) {
                    password2.setError("Please Re-enter Password");
                    password2.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {

                                Intent intent = new Intent(MainActivity.this, SecondRegActivity.class);
                                intent.putExtra("email", email);
                                intent.putExtra("firstname", fname);
                                intent.putExtra("lastname", lname);
                                startActivity(intent);

                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
