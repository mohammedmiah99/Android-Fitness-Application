package com.miah.myfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.core.stock.scrollerseries.Line;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.CircleShapeRenderer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WorkoutLogActivity extends AppCompatActivity {
    private EditText txt_SQUAT, txt_SQUATREPS, txt_DEADLIFT, txt_DEADLIFTREPS, txt_BENCH, txt_BENCHREPS;
    private Button btnAddWorkout, btnDelWorkout;
    private ScatterChart lineChart;
    ImageView btnBack;
    private DatabaseHandler myhelper;
    SQLiteDatabase sqLiteDatabase;

    ScatterDataSet lineDataSet = new ScatterDataSet(null, null);
    ScatterDataSet lineDataSet2 = new ScatterDataSet(null, null);
    ScatterDataSet lineDataSet3 = new ScatterDataSet(null, null);
    ArrayList<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
    ScatterData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_log);
        lineChart = findViewById(R.id.linechart);
        txt_SQUAT = findViewById(R.id.txt_SQUAT99);
        txt_SQUATREPS = findViewById(R.id.txt_SQUATREPS99);
        txt_DEADLIFT = findViewById(R.id.txt_DEADLIFT99);
        txt_DEADLIFTREPS = findViewById(R.id.txt_DEADLIFTREPS99);
        txt_BENCH = findViewById(R.id.txt_BENCH99);
        txt_BENCHREPS = findViewById(R.id.txt_BENCHREPS99);
        btnBack = findViewById(R.id.back);
        btnDelWorkout = findViewById(R.id.delWorkout);
        btnAddWorkout = findViewById(R.id.addWorkout99);
        myhelper = new DatabaseHandler(this);
        sqLiteDatabase = myhelper.getWritableDatabase();

        exqInsert();
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setGranularity(1f);
        lineDataSet.setScatterShapeSize(10);
        YAxis y2 = lineChart.getAxisRight();
        y2.setEnabled(false);
        lineDataSet.setValues(getDataValues());
        lineDataSet.setLabel("Squat");
        lineDataSet.setScatterShapeSize(30);
        lineDataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        lineDataSet.setColor(R.color.blue);

        lineDataSet2.setValues(getDataValues2());
        lineDataSet2.setLabel("Deadlift");
        lineDataSet2.setScatterShapeSize(30);
        lineDataSet2.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        lineDataSet2.setColor(R.color.green);

        lineDataSet3.setValues(getDataValues3());
        lineDataSet3.setLabel("Bench");
        lineDataSet3.setScatterShapeSize(30);
        lineDataSet3.setScatterShape(ScatterChart.ScatterShape.TRIANGLE);


        dataSets.clear();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet3);
        lineData = new ScatterData(dataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutLogActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnDelWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myhelper.removeAll();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.workout99);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.workout99:
                        return true;
                    case R.id.home99:
                        startActivity(new Intent(getApplicationContext()
                                , HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.diary99:
                        startActivity(new Intent(getApplicationContext()
                                , DiaryActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }


    private void exqInsert() {
        btnAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_SQUAT.getText().toString().isEmpty()) {
                    txt_SQUAT.setError("Please enter your Squats for the week");
                    txt_SQUAT.requestFocus();
                } else if (txt_SQUATREPS.getText().toString().isEmpty()) {
                    txt_SQUATREPS.setError("Please enter your Squats reps");
                    txt_SQUATREPS.requestFocus();
                } else if (txt_DEADLIFT.getText().toString().isEmpty()) {
                    txt_DEADLIFT.setError("Please enter your Deadlifts for the week");
                    txt_DEADLIFT.requestFocus();
                } else if (txt_DEADLIFTREPS.getText().toString().isEmpty()) {
                    txt_DEADLIFTREPS.setError("Please enter your Deadlifts reps");
                    txt_DEADLIFTREPS.requestFocus();
                } else if (txt_BENCH.getText().toString().isEmpty()) {
                    txt_BENCH.setError("Please enter your Bench Press for the week");
                    txt_BENCH.requestFocus();
                } else if (txt_BENCHREPS.getText().toString().isEmpty()) {
                    txt_BENCHREPS.setError("Please enter your Bench Press reps");
                    txt_BENCHREPS.requestFocus();
                } else if (!txt_SQUAT.getText().toString().isEmpty() || !txt_SQUATREPS.getText().toString().isEmpty() ||
                        !txt_DEADLIFT.getText().toString().isEmpty() || !txt_DEADLIFTREPS.getText().toString().isEmpty() ||
                        !txt_BENCHREPS.getText().toString().isEmpty() || !txt_BENCH.getText().toString().isEmpty()) {

                    final int xVal = Integer.parseInt(txt_SQUATREPS.getText().toString());
                    final int yVal = Integer.parseInt(txt_SQUAT.getText().toString());
                    final int xVal2 = Integer.parseInt(txt_DEADLIFTREPS.getText().toString());
                    final int yVal2 = Integer.parseInt(txt_DEADLIFT.getText().toString());
                    final int xVal3 = Integer.parseInt(txt_BENCHREPS.getText().toString());
                    final int yVal3 = Integer.parseInt(txt_BENCH.getText().toString());


                    XAxis xAxis = lineChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setDrawGridLines(false);
                    xAxis.setAvoidFirstLastClipping(true);
                    xAxis.setGranularity(1f);
                    lineDataSet.setScatterShapeSize(10);

                    YAxis y2 = lineChart.getAxisRight();
                    y2.setEnabled(false);

                    myhelper.insertToData(xVal, yVal, xVal2, yVal2, xVal3, yVal3);
                    lineDataSet.setValues(getDataValues());
                    lineDataSet.setLabel("Squat");
                    lineDataSet.setScatterShapeSize(30);
                    lineDataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
                    lineDataSet.setColor(R.color.blue);

                    lineDataSet2.setValues(getDataValues2());
                    lineDataSet2.setLabel("Deadlift");
                    lineDataSet2.setScatterShapeSize(30);
                    lineDataSet2.setScatterShape(ScatterChart.ScatterShape.SQUARE);
                    lineDataSet2.setColor(R.color.green);

                    lineDataSet3.setValues(getDataValues3());
                    lineDataSet3.setLabel("Bench");
                    lineDataSet3.setScatterShapeSize(30);
                    lineDataSet3.setScatterShape(ScatterChart.ScatterShape.TRIANGLE);
                    lineDataSet3.setColor(R.color.orange);

                    dataSets.clear();
                    dataSets.add(lineDataSet);
                    dataSets.add(lineDataSet2);
                    dataSets.add(lineDataSet3);
                    lineData = new ScatterData(dataSets);
                    lineChart.clear();
                    lineChart.setData(lineData);
                    lineChart.invalidate();


                    erasetxt();
                }

            }
        });
    }

    private void erasetxt() {
        txt_SQUAT.setText("");
        txt_SQUATREPS.setText("");
        txt_DEADLIFT.setText("");
        txt_DEADLIFTREPS.setText("");
        txt_BENCH.setText("");
        txt_BENCHREPS.setText("");
    }

    private ArrayList<Entry> getDataValues() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        String[] columns = {"xValues", "yValues"};
        Cursor cursor = sqLiteDatabase.query("myTable", columns, null, null, null, null, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dataVals.add(new Entry(cursor.getFloat(0), cursor.getFloat(1)));
        }
        return dataVals;
    }

    private ArrayList<Entry> getDataValues2() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        String[] columns = {"xValues2", "yValues2"};
        Cursor cursor = sqLiteDatabase.query("myTable", columns, null, null, null, null, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dataVals.add(new Entry(cursor.getFloat(0), cursor.getFloat(1)));
        }
        return dataVals;
    }

    private ArrayList<Entry> getDataValues3() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        String[] columns = {"xValues3", "yValues3"};
        Cursor cursor = sqLiteDatabase.query("myTable", columns, null, null, null, null, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dataVals.add(new Entry(cursor.getFloat(0), cursor.getFloat(1)));
        }
        return dataVals;
    }

}
