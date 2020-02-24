package com.example.tequedlabs;
import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.tequedlabs.R.*;
import static com.example.tequedlabs.R.layout.mainlist;

class MyStudentListAdapter extends ArrayAdapter<String>  {
private final Activity context;

        ArrayList<String> namelist=new ArrayList<String>();
        ArrayList<String> collegelist=new ArrayList<String>();
        ArrayList<String> courselist=new ArrayList<String>();
        ArrayList<String> dateofjoin=new ArrayList<String>();

public MyStudentListAdapter(Activity context,ArrayList<String> namelist,ArrayList<String> collegelist,ArrayList<String> courselist,ArrayList<String> dateofjoin){

        // TODO Auto-generated constructor stub
    super( context, mainlist,namelist);
        this.context=context;
        this.namelist=namelist;
        this.collegelist=collegelist;
        this.courselist=courselist;
        this.dateofjoin=dateofjoin; }


        public View getView(int position, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(mainlist,null,true);

            TextView name = (TextView) rowView.findViewById(R.id.text2);
            TextView college = (TextView) rowView.findViewById(id.text4);
            TextView course = (TextView) rowView.findViewById(id.text6);
            TextView  date = (TextView) rowView.findViewById(id.text8);
//        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        name.setText(namelist.get(position));
        college.setText(collegelist.get(position));
       course.setText(courselist.get(position));
        date.setText(dateofjoin.get(position));
//        subtitleText.setText(subtitle.get(position));

        return rowView;
        }


    }
