package com.example.tequedlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NewCourse extends AppCompatActivity {
    ListView list;
//    Button button;
    String value;
    DatabaseReference databaseReference;

    ArrayList<String> maintitle = new ArrayList<String>();
    {


    };

    ArrayList<String> subtitle =new ArrayList<String>();{

    };

    ArrayList<Integer> imgid=new ArrayList<Integer>();{
        imgid.add(R.mipmap.ai);
        imgid.add(R.mipmap.ml);
        imgid.add(R.mipmap.iot);
        imgid.add(R.mipmap.vr);
        imgid.add(R.mipmap.aad);
        imgid.add(R.mipmap.wb);
        imgid.add(R.drawable.ml);
        imgid.add(R.mipmap.wb);
        imgid.add(R.drawable.ml);
        imgid.add(R.drawable.ml);
        imgid.add(R.mipmap.wb);
        imgid.add(R.drawable.ml);


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
//        button= findViewById(R.id.add);
        final MyListAdapter adapter=new MyListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);



        databaseReference = FirebaseDatabase.getInstance().getReference("CourseList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    maintitle.add((java.lang.String) childDataSnapshot.child("courseName").getValue());
                    subtitle.add((java.lang.String) childDataSnapshot.child((childDataSnapshot.getKey())).getValue());
                    list.setAdapter(adapter);

                    System.out.println((childDataSnapshot.getKey()));
                    //displays the key for the node
                    System.out.println(childDataSnapshot.child("ai").getValue());
                    list.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
