package com.entecmedia.loxxhairandmore;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OpeninghoursActivity extends AppCompatActivity {

    ListView
            listViewLeft,
            listViewRight;

    String[] emptyList = {""};


    ArrayAdapter<String> adapter;

    ArrayAdapter<String> adapter2;

    ArrayAdapter<String> adapter3;

    View clickSource;
    View touchSource;

    int offset = 0;

    String
            monday,
            tuesday,
            wednesday,
            thursday,
            friday,
            saturday,
            sunday;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_openinghours);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        Firebase.setAndroidContext(this);

        String[] days = {getString(R.string.monday_string), getString(R.string.tuesday_string), getString(R.string.wednesday_string), getString(R.string.thursday_string), getString(R.string.friday_string), getString(R.string.saturday_string), getString(R.string.sunday_string)};

        String[] hours = {getString(R.string.closed), getString(R.string.ten_till_six), getString(R.string.ten_till_six), getString(R.string.twelve_till_seven), getString(R.string.ten_till_six), getString(R.string.nine_till_three), getString(R.string.closed)};

        ArrayAdapter listAdapter = new ArrayAdapter<>(this, R.layout.custom_row2, days);

        //match xml views with Java variables

        listViewLeft = findViewById(R.id.listViewLeft);

        listViewRight = findViewById(R.id.listViewRight);

        adapter = new ArrayAdapter<>(this, R.layout.custom_row2, days);

        adapter2 = new ArrayAdapter<>(this, R.layout.custom_row2, hours);

        adapter3 = new ArrayAdapter<>(this, R.layout.custom_row2, emptyList);

        listViewLeft.setAdapter(listAdapter);
        listViewRight.setAdapter(adapter2);

        // allow both listViews to scroll simultaneously

        listViewLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (touchSource == null) {

                    touchSource = v;

                }
                if (v == touchSource) {

                    listViewRight.dispatchTouchEvent(event);
                    if (event.getAction() == MotionEvent.ACTION_UP) {

                        clickSource = v;
                        touchSource = null;

                    }

                }

                return false;
            }
        });

        listViewRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (touchSource == null) {

                    touchSource = v;

                }
                if (v == touchSource) {

                    listViewLeft.dispatchTouchEvent(event);
                    if (event.getAction() == MotionEvent.ACTION_UP) {

                        clickSource = v;
                        touchSource = null;

                    }

                }

                return false;
            }
        });

        listViewLeft.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view == clickSource)
                    listViewRight.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });

        listViewRight.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view == clickSource)
                    listViewLeft.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });
    }

    @Override
    public void onBackPressed() {

        // set empty listViews for when onBackPressed() is instantiated for a better looking Activity transition animation

        listViewLeft.setAdapter(adapter3);
        listViewRight.setAdapter(adapter3);

        super.onBackPressed();
    }

}
