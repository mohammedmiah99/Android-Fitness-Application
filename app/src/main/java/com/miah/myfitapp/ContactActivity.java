package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ContactActivity extends AppCompatActivity {
    private Button btnSend;
    private ImageView btnBack;
    private EditText txt_subject, txt_notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnBack = findViewById(R.id.btn_back44);
        btnSend = findViewById(R.id.btn_submit44);
        txt_notes = findViewById(R.id.txt_note44);
        txt_subject = findViewById(R.id.txt_subject44);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String note = txt_notes.getText().toString();
                final String subject = txt_subject.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "miahincorporated@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, note);
                startActivity(Intent.createChooser(intent, ""));


            }
        });


    }
}
