package com.entecmedia.loxxhairandmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by inter on 31-12-2016.
 */



public class CustomAdapter extends ArrayAdapter<String> {

    //adapter initialisation

    TextView textViewLeft, textViewRight;
    String singleTextLeftItem, singleTextRightItem;


    CustomAdapter(Context context, String textLeft[]) {
        super(context, R.layout.custom_row, textLeft);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        int[] intArray = new int[ContactActivity.info1.length];
        int sum = 0;



        for (int i = 0; i < ContactActivity.info1.length; i++) {

            if (i%2!=0)
            {

singleTextRightItem = getItem(i);

            }
        }

        singleTextRightItem = getItem(position);
        textViewLeft = (TextView) customView.findViewById(R.id.textViewLeft);
        textViewRight = (TextView) customView.findViewById(R.id.textViewRight);

        textViewLeft.setText(singleTextLeftItem);
        textViewRight.setText(singleTextLeftItem);

        return customView;
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

}



