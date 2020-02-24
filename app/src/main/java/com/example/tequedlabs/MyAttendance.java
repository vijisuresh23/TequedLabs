package com.example.tequedlabs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MyAttendance extends ArrayAdapter<String> {
    private final Activity context;
    private ArrayList<String> maintitle=new ArrayList<String>();

    public MyAttendance(TeacherAttendance teacherAttendance, ArrayList<String> student) {
        super(teacherAttendance,R.layout.attendancemark,student);
        this.context=teacherAttendance;
        this.maintitle=student;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.attendancemark, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.abc1);
        titleText.setText(maintitle.get(position));



        return rowView;

    };
}
