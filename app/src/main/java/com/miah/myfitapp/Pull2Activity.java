package com.miah.myfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Pull2Activity extends AppCompatActivity {
    private MediaPlayer mediaPlayer1;
    ProgressBar progressBar;
    ImageView mainImageView;
    TextView title, description, sets, reps,timer1;
    String data1, data2, data3, data4, dataTime, dataVideo;
    int myGifImage;
    private Button btnDone, btnRest;
    private int count=1;
    private WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull2);
        mediaPlayer1 = MediaPlayer.create(this,R.raw.radar);
        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        sets = findViewById(R.id.sets);
        reps = findViewById(R.id.reps);
        btnDone = findViewById(R.id.btnDone);
        btnRest = findViewById(R.id.btnRest);
        timer1 = findViewById(R.id.timer1);
        progressBar = findViewById(R.id.progressBar);



        getData();
        setData();

        btnRest.setOnClickListener(new View.OnClickListener() {
            int result = Integer.parseInt(dataTime);
            int set = Integer.parseInt(data3);
            @Override
            public void onClick(View v) {
                count++;
                btnRest.setClickable(false);
                new CountDownTimer(result,1000){
                    @Override
                    public void onTick(long l) {
                        timer1.setText(""+l/1000);
                        int inum = (int)l;
                        progressBar.setProgress(inum/1000);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onFinish() {
                        timer1.setText(""+result/1000);
                        mediaPlayer1.start();
                        progressBar.setVisibility(View.INVISIBLE);
                        progressBar.setProgress(result/1000);
                        Toast.makeText(Pull2Activity.this,"Rest Over!",Toast.LENGTH_SHORT).show();
                        btnRest.setText("Start Rest "+count);
                        btnRest.setClickable(true);
                        if(count >= set){
                            btnRest.setText("Done");
                            startActivity(new Intent(Pull2Activity.this, PullWrktActivity.class));

                            //finish();
                        }
                    }
                }.start();
            }
        });
    }
    private void getData(){
        if (getIntent().hasExtra("myGifImage")&&getIntent().hasExtra("data1")&&
        getIntent().hasExtra("data2")&&getIntent().hasExtra("data3")&&getIntent().hasExtra("data4")
        &&getIntent().hasExtra("timer1")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            myGifImage = getIntent().getIntExtra("myGifImage",1);
            dataTime = getIntent().getStringExtra("timer1");
            dataVideo = getIntent().getStringExtra("video1");


        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        title.setText(data1);
        description.setText(data2);
        sets.setText(data3);
        reps.setText(data4);
        mainImageView.setImageResource(myGifImage);
        int result = Integer.parseInt(dataTime);
        timer1.setText(""+result/1000);
        btnRest.setText("Start Rest 1");
    }

    public void videoIMG(View v){
        webView1 = new WebView(this);
        setContentView(webView1);
        webView1.loadUrl(dataVideo);
    }
}
