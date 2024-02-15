package com.entecmedia.loxxhairandmore;

import android.os.Build;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

public class MainActivity2 extends AppCompatActivity {

    RelativeLayout

            blueButton,
            pinkButton,
            yellowButton,
            orangeButton;

    ImageView
            openinghours_imageView,
            appointment_imageView,
            information_imageView,
            tariff_imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        // match xml views with java variables

        blueButton = (RelativeLayout) findViewById(R.id.blueButton);
        pinkButton = (RelativeLayout) findViewById(R.id.pinkButton);
        yellowButton = (RelativeLayout) findViewById(R.id.yellowButton);
        orangeButton = (RelativeLayout) findViewById(R.id.orangeButton);

        information_imageView = (ImageView) findViewById(R.id.information_imageView);
        openinghours_imageView = (ImageView) findViewById(R.id.imageView6);
        appointment_imageView = (ImageView) findViewById(R.id.transitionIcon);
        tariff_imageView = (ImageView) findViewById(R.id.tariff_imageView);

        pinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, OpeninghoursActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    //create shared element activity transition

                    Pair<View, String> pair1 = Pair.create((View)pinkButton, pinkButton.getTransitionName());
                    Pair<View, String> pair2 = Pair.create((View)blueButton, blueButton.getTransitionName());

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity2.this, pair1, pair2);
                    startActivity(intent, options.toBundle());
                }
                else {
                    startActivity(intent);
                }
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, InformationActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    //create shared element activity transition

                    Pair<View, String> pair1 = Pair.create((View)information_imageView, information_imageView.getTransitionName());
                    Pair<View, String> pair2 = Pair.create((View)openinghours_imageView, openinghours_imageView.getTransitionName());
                    Pair<View, String> pair3 = Pair.create((View)appointment_imageView, appointment_imageView.getTransitionName());
                    Pair<View, String> pair4 = Pair.create((View)tariff_imageView, tariff_imageView.getTransitionName());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity2.this, pair1, pair2, pair3, pair4);
                    startActivity(intent, options.toBundle());
                }
                else {
                    startActivity(intent);
                }
            }
        });

        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, WebViewActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    //create shared element activity transition

                    Pair<View, String> pair1 = Pair.create((View)appointment_imageView, appointment_imageView.getTransitionName());
                    Pair<View, String> pair2 = Pair.create((View)tariff_imageView, tariff_imageView.getTransitionName());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity2.this, pair1, pair2);
                    startActivity(intent, options.toBundle());
                }
                else {
                    startActivity(intent);
                }

            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, ContactActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    //create shared element activity transition

                    Pair<View, String> pair1 = Pair.create((View)yellowButton, yellowButton.getTransitionName());
                    Pair<View, String> pair2 = Pair.create((View)orangeButton, orangeButton.getTransitionName());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity2.this, pair1, pair2);
                    startActivity(intent, options.toBundle());
                }
                else {
                    startActivity(intent);
                }

            }
        });

    }
}

