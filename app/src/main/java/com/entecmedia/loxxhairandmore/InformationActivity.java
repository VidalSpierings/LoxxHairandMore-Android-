package com.entecmedia.loxxhairandmore;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    RelativeLayout
            blueButton,
    //E-mail
    pinkButton,
    //call
    orangeButton,
    //Facebook
    yellowButton;
    //maps

    String FACEBOOK_URL = "https://www.facebook.com/Karinimagestyling/";
    String FACEBOOK_PAGE_ID = "ts";

    //method to get the right URL to use in the intent

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_information);

        blueButton = (RelativeLayout) findViewById(R.id.blueButton);
        orangeButton = (RelativeLayout) findViewById(R.id.orangeButton);
        pinkButton = (RelativeLayout) findViewById(R.id.pinkButton);
        yellowButton = (RelativeLayout) findViewById(R.id.yellowButton);

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{getString(R.string.e_mail_string)});
                startActivity(intent);
            }
        });

        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse(getFacebookPageURL(getApplicationContext()));

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent);

            }
        });

        pinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = getString(R.string.action_call_phone_number_string);

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phone));

                startActivity(intent);

            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse(getString(R.string.google_maps_location_url_string));

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent);

            }
        });





    }
}
