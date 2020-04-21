package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddToBlogActivity extends AppCompatActivity {

        private EditText txt_blog, txt_title11;
        private Button btn_submit;
        private ImageView btnBack99;
        private ListView listView;
        FirebaseAuth auth, auth1;
        FirebaseUser user, user1;
        DatabaseReference reference, reference1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_to_blog);
            auth1 = FirebaseAuth.getInstance();
            user1 = auth1.getCurrentUser();
            btnBack99 = findViewById(R.id.btn_back99);

            txt_blog = findViewById(R.id.txt_blog11);
            txt_title11 = findViewById(R.id.txt_title11);

            btn_submit = findViewById(R.id.btn_submit11);
            reference = FirebaseDatabase.getInstance().getReference("blog");

            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addBlog();
                    Intent intToMain = new Intent(AddToBlogActivity.this, BlogActivity.class);
                    startActivity(intToMain);
                }
            });
            btnBack99.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AddToBlogActivity.this, BlogActivity.class);
                    startActivity(intent);
                }
            });

        }

        public void addBlog(){
            String note = txt_blog.getText().toString().trim();
            String title = txt_title11.getText().toString().trim();


            reference1 = FirebaseDatabase.getInstance().getReference().child("User").child(user1.getUid());
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("firstname").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();

                    if(!TextUtils.isEmpty(note)|!TextUtils.isEmpty(title)){
                        String id  = reference.push().getKey();
                        Blog blog = new Blog(id,note,name,email,title);
                        reference.child(id).setValue(blog);
                        Toast.makeText(AddToBlogActivity.this,"Added",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(AddToBlogActivity.this,"Enter note and title", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


