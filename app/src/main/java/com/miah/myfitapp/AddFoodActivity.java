package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import static com.miah.myfitapp.DatabaseHelper.TABLE_NAME;

public class AddFoodActivity extends AppCompatActivity {
    String notes, kcal, carbs, protein, fats;
    EditText text_notes, text_kcal, text_protein, text_carbs, text_fats;
    private Button btnAddFood, btnDeleteFood;
    private ImageView btnBack;
    private DatabaseHelper db;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        text_notes = findViewById(R.id.txt_notes);
        text_kcal = findViewById(R.id.txt_kcals);
        text_carbs = findViewById(R.id.txt_carbs);
        text_protein = findViewById(R.id.txt_protein);
        text_fats = findViewById(R.id.txt_fats);
        btnAddFood = findViewById(R.id.addFood);
        btnDeleteFood = findViewById(R.id.delete);
        listView = (ListView) findViewById(R.id.listView);
        btnBack = findViewById(R.id.back);

        db = new DatabaseHelper(this);
        showFood();

        btnDeleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = db.deleteData(text_notes.getText().toString());
                // db.removeAll();
                showFood();
                eraseText();

            }
        });

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String notes = text_notes.getText().toString();
                final String kcal = text_kcal.getText().toString();
                final String carbs = text_carbs.getText().toString();
                final String protein = text_protein.getText().toString();
                final String fats = text_fats.getText().toString();


                addFood();
                showFood();
                eraseText();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddFoodActivity.this, DiaryActivity.class);
                intent.putExtra("kcal", kcal);
                intent.putExtra("carbs", carbs);
                intent.putExtra("protein", protein);
                intent.putExtra("fats", fats);
                startActivity(intent);
            }
        });


    }

    private void addFood() {
        final String notes = text_notes.getText().toString();
        final String kcal = text_kcal.getText().toString();
        final String carbs = text_carbs.getText().toString();
        final String protein = text_protein.getText().toString();
        final String fats = text_fats.getText().toString();

        if (TextUtils.isEmpty(notes)) {
            Toast.makeText(this, "You did not enter Notes", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(kcal)) {
            Toast.makeText(this, "You did not enter Kcals", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(carbs)) {
            Toast.makeText(this, "You did not enter Carbs", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(protein)) {
            Toast.makeText(this, "You did not enter Protein", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(fats)) {
            Toast.makeText(this, "You did not enter Fats", Toast.LENGTH_SHORT).show();
            return;
        }

        if (db.addFood(notes, kcal, carbs, protein, fats)) {
            Toast.makeText(this, "Food added!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Food NOT added!", Toast.LENGTH_LONG).show();

        }
        eraseText();
    }

    private void showFood() {
        Cursor cursor = db.getFood();
        if (cursor.moveToFirst()) {
            String[] notes = new String[cursor.getCount()];

            int i = 0;
            do {
                notes[i] = "Notes: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTES)) + ", K: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_KCALS))
                        + ", C: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CARBS)) + ", P: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PROTEIN)) +
                        ", F: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_FATS));
                i++;
            } while (cursor.moveToNext());
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);

            listView.setAdapter(adapter);
        }
    }

    private void eraseText() {
        text_notes.setText("");
        text_fats.setText("");
        text_protein.setText("");
        text_carbs.setText("");
        text_kcal.setText("");

    }

}
