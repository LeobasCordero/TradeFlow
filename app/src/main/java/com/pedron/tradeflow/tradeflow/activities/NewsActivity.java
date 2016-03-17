package com.pedron.tradeflow.tradeflow.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.util.SwipeDetector;

/**
 * Created by leocg on 16/03/2016.
 */
public class NewsActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        b = (Button)findViewById(R.id.next);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);

            actionBar.setDisplayShowCustomEnabled(true);

            LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflator.inflate(R.layout.custom_imageview, null);

            actionBar.setCustomView(v);
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, StoresActivity.class));
            }
        });

//        SwipeDetector activitySwipeDetector = new SwipeDetector(this);
//        RelativeLayout lowestLayout = (RelativeLayout)this.findViewById(R.id.news_activity);
//        lowestLayout.setOnTouchListener(activitySwipeDetector);
//        startActivity(activitySwipeDetector.intent);
    }

}
