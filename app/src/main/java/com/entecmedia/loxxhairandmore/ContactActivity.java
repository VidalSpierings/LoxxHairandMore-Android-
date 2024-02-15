package com.entecmedia.loxxhairandmore;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {


    ListView
            listViewLeft,
            listViewRight;

    String [] emptyList = {""};

    ArrayAdapter<String> adapter;

    static String[] info1;

    int offset = 0;

    TextView custom_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_contact2);

        //initialise items for listView on the right

        info1 = new String[]{getString(R.string.short1), "", "", "", getString(R.string.washing), "€ 3,-", getString(R.string.cutting), "€ 19,-", getString(R.string.cutting_and_drying),
                "€ 24,-", getString(R.string.cutting_and_styling), "€ 45,-", getString(R.string.color), "€ 35,-", getString(R.string.total_coloring), "€ 40,-",
                getString(R.string.kamst_bars), "€ 35 /  € 40,-", getString(R.string.lowlights), "€ 40,- / € 45,-", getString(R.string.extra_color), "€ 10,-", getString(R.string.permanent),
                "€ 81,-", getString(R.string.mask), "€ 15,-", getString(R.string.trimming), "€ 7,50", getString(R.string.pony), "€ 7,50", "", "", getString(R.string.long1), "", "", "",
                getString(R.string.washing), "€ 3,-", getString(R.string.cutting), "€ 24,-", getString(R.string.cutting_and_drying), "€ 29,-",
                getString(R.string.cutting_and_styling), getString(R.string.from_fifty_five), getString(R.string.color), getString(R.string.from_forty_five), getString(R.string.total_coloring),
                getString(R.string.from_fifty), getString(R.string.kamst_bars), "€50,- / € 55,-", getString(R.string.lowlights), "€ 10,-", getString(R.string.extra_color), getString(R.string.from_ninety), getString(R.string.permanent),
                "€ 15,-", getString(R.string.mask), "€ 7,50", getString(R.string.trimming), "€ 7,50", getString(R.string.pony)};

        // set statusbar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


        listViewRight = (ListView) findViewById(R.id.listViewRight);

        adapter = new ArrayAdapter<>(this, R.layout.custom_row2, emptyList);

        listViewRight.setAdapter(new CustomAdapater2());

        }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        //set empty adapter to allow an empty listView to animate when onBackPressed() is instantiated

        listViewRight.setAdapter(adapter);
    }

    // listView initialisation

    class SingleRow{

        String textRight;
        String textLeft;

        SingleRow(String textLeft, String textRight){

            this.textLeft = textLeft;
            this.textRight = textRight;

        }

    }

    class CustomAdapater2 extends BaseAdapter{

        ArrayList<SingleRow> list;

        CustomAdapater2(){

            list = new ArrayList<>();

            String[] descriptions =

                    {getString(R.string.short1), "", getString(R.string.washing), getString(R.string.cutting), getString(R.string.cutting_and_drying),
                            getString(R.string.cutting_and_styling), getString(R.string.color), getString(R.string.total_coloring),
                            getString(R.string.kamst_bars) , getString(R.string.lowlights), getString(R.string.extra_color), getString(R.string.permanent),
                            getString(R.string.mask), getString(R.string.trimming), getString(R.string.pony), "", getString(R.string.long1), "",
                            getString(R.string.washing), getString(R.string.cutting), getString(R.string.cutting_and_drying),
                            getString(R.string.cutting_and_styling), getString(R.string.color), getString(R.string.total_coloring),
                            getString(R.string.kamst_bars) , getString(R.string.lowlights), getString(R.string.extra_color), getString(R.string.permanent),
                            getString(R.string.mask), getString(R.string.trimming), getString(R.string.pony)};

            String[] rates =

                    {"", "", "€ 3,-", "€ 19,-",
                            "€ 24,-", "€ 45,-", "€ 35,-",
                            "€ 40,-", "€ 35 /  € 40,-", "€ 40,- / € 45,-", "€ 10,-",
                            "€ 81,-", "€ 15,-", "€ 7,50" , "€ 7,50",
                            "", "", "", "€ 3,-", "€ 24,-", "€ 29,-",
                            getString(R.string.from_fifty_five), getString(R.string.from_forty_five), getString(R.string.from_fifty),
                            "€ 45 /  € 50,-", "€50,- / € 55,-", "€ 10,-", getString(R.string.from_ninety),
                            "€ 15,-", "€ 7,50", "€ 7,50"};

            for(int i=0;i<rates.length; i++){

            list.add(new SingleRow(descriptions[i], rates[i]));

            }

        }

        @Override
        public int getCount() {

            return list.size();

        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.custom_row, parent, false);

            TextView textLeft = (TextView) row.findViewById(R.id.textViewLeft);
            TextView textRight = (TextView) row.findViewById(R.id.textViewRight);

            SingleRow row2 = list.get(position);

            textLeft.setText(row2.textLeft);
            textRight.setText(row2.textRight);

            return row;
        }

    }

    }





