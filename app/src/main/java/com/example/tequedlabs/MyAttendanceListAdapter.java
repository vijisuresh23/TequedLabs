package com.example.tequedlabs;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ArrayList;

import static com.example.tequedlabs.R.layout.check;
import static com.example.tequedlabs.R.layout.mainlist;

class MyAttendanceListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    ArrayList<String> namelist=new ArrayList<String>();
    ArrayList<String> collegelist=new ArrayList<String>();
    ArrayList<String> courselist=new ArrayList<String>();
    ArrayList<String> dateofjoin=new ArrayList<String>();

    public MyAttendanceListAdapter(AdminAttendance adminAttendance, ArrayList<String> namelist, ArrayList<String> collegelist, ArrayList<String> date, ArrayList<String> status) {

        // TODO Auto-generated constructor stub
        super( adminAttendance, check,namelist);
        this.context=adminAttendance;
        this.namelist=namelist;
        this.collegelist=collegelist;
        this.courselist=date;
        this.dateofjoin=status;
    }

    public View getView(int position, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(check,null,true);

        TextView name = (TextView) rowView.findViewById(R.id.text2);
        TextView college = (TextView) rowView.findViewById(R.id.text4);
        TextView course = (TextView) rowView.findViewById(R.id.text6);
        TextView  date = (TextView) rowView.findViewById(R.id.text8);
        name.setText(namelist.get(position));
        college.setText(collegelist.get(position));
        course.setText(courselist.get(position));
        date.setText(dateofjoin.get(position));


        return rowView;
    }
}


