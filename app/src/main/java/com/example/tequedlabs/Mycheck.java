package com.example.tequedlabs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

class Mycheck extends ArrayAdapter<CheckBox> {
    private final Activity context;
    private ArrayList<CheckBox> maintitle=new ArrayList<CheckBox>();

    public Mycheck(TeacherAttendance teacherAttendance, ArrayList<CheckBox> check) {
        super(teacherAttendance,R.layout.check,check);
        this.context=teacherAttendance;
        this.maintitle=check;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.check, null,true);

        CheckBox titleText = (CheckBox) rowView.findViewById(R.id.checkbox);
        titleText.setText((CharSequence) maintitle.get(position));



        return rowView;

    };
}
