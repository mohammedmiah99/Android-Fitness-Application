package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PTActivity extends AppCompatActivity {
    private EditText txt_IDpt;
    private CardView btn_ppl, btn_UL, btn_ppl3;
    private ImageView backArrowpt;
    ArrayList<String> af = new ArrayList<>();
    DatabaseReference reference, ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt);

        txt_IDpt = findViewById(R.id.txt_IDpt);
        btn_ppl = findViewById(R.id.btnPPL);
        btn_ppl3 = findViewById(R.id.btnPPL3);
        btn_UL = findViewById(R.id.btnUL);
        backArrowpt = findViewById(R.id.backArrowpt);

        ref = FirebaseDatabase.getInstance().getReference("User");


        backArrowpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        btn_ppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key=childSnapshot.getKey();
                            af.add(key);
                        }                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                final String id = txt_IDpt.getText().toString();
                if(!id.isEmpty()&& af.contains(id)){
                    reference = FirebaseDatabase.getInstance().getReference().child("User").child(id);
                    reference.child("special").setValue("PPL6");
                    txt_IDpt.setText("");
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }else if (id.isEmpty()|| !af.contains(id)){
                    txt_IDpt.setError("Please enter a correct ID");
                    txt_IDpt.requestFocus();
                }

            }
        });

        btn_ppl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key=childSnapshot.getKey();
                            af.add(key);
                        }                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                final String id = txt_IDpt.getText().toString();
                if(!id.isEmpty()&& af.contains(id)){
                    reference = FirebaseDatabase.getInstance().getReference().child("User").child(id);
                    reference.child("special").setValue("PPL3");
                    txt_IDpt.setText("");
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }else if (id.isEmpty()|| !af.contains(id)){
                    txt_IDpt.setError("Please enter a correct ID");
                    txt_IDpt.requestFocus();
                }

            }
        });

        btn_UL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key=childSnapshot.getKey();
                            af.add(key);
                        }                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                final String id = txt_IDpt.getText().toString();
                if(!id.isEmpty()&& af.contains(id)){
                    reference = FirebaseDatabase.getInstance().getReference().child("User").child(id);
                    reference.child("special").setValue("UL4");
                    txt_IDpt.setText("");
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }else if (id.isEmpty()|| !af.contains(id)){
                    txt_IDpt.setError("Please enter a correct ID");
                    txt_IDpt.requestFocus();
                }

            }
        });
    }

}
