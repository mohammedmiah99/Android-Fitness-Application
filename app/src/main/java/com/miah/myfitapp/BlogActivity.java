package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlogActivity extends AppCompatActivity {

    private EditText searchfilter;
    private Button btn_submit, btn_del;
    private ImageView btnBack98;
    private ListView listView;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference, reference11, reference12;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> al = new ArrayList<>();
    private ArrayList<String> a2 = new ArrayList<>();
    private ArrayList<String> a3 = new ArrayList<>();
    String emailchange;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        btn_del = findViewById(R.id.btn_del);
        searchfilter = findViewById(R.id.searchfilter);
        btnBack98 = findViewById(R.id.btn_back98);
        btn_submit = findViewById(R.id.btn_submit);
        listView = findViewById(R.id.listviewNote);
        reference = FirebaseDatabase.getInstance().getReference("blog");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference11 = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());
        reference11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                emailchange = dataSnapshot.child("email").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String note = ds.child("note").getValue(String.class);
                    String name = ds.child("name").getValue(String.class);
                    String email = ds.child("email").getValue(String.class);
                    String title = ds.child("title").getValue(String.class);
                    al.add(email);
                    a2.add(name);
                    a3.add(note);

                    arrayList.add(title+"\n"+note+"\n"+"Post by: "+name+"\n"+email);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int counter = 0; counter < arrayList.size(); counter++) {

                    if (arrayList.get(counter).contains(arrayList.get(position))){
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { al.get(counter)});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding you MyFitApp post titled: "+a2.get(counter));
                        intent.putExtra(Intent.EXTRA_TEXT, "Dear Fellow MyFitApp User, this is a reply to your message: "+ a3.get(counter));
                        startActivity(Intent.createChooser(intent, ""));
                    }}}

        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToMain = new Intent(BlogActivity.this, AddToBlogActivity.class);
                startActivity(intToMain);
            }
        });

        btnBack98.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlogActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        searchfilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (BlogActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query query = ref.child("blog").orderByChild("email").equalTo(emailchange);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot Snapshot: dataSnapshot.getChildren()) {
                            Snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Intent intToMain = new Intent(BlogActivity.this, HomeActivity.class);
                startActivity(intToMain);

            }
        });

    }

}
