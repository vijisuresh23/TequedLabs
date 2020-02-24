package com.example.tequedlabs;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
class MyListAdapter extends ArrayAdapter<String>  {

    private final Activity context;
    private ArrayList<String> maintitle=new ArrayList<String>();
    private ArrayList<String> subtitle=new ArrayList<String>();
    private ArrayList<Integer> imgid=new ArrayList<Integer>();
    public MyListAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle, ArrayList<Integer> imgid) {
        super((Context) context, R.layout.row, maintitle);
        // TODO Auto-generated constructor stub

        this.context= (Activity) context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid= imgid;

    }



    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        titleText.setText(maintitle.get(position));
        imageView.setImageResource(imgid.get(position));


        return rowView;

    };
}
