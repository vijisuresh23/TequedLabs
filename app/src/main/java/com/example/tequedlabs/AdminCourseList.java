package com.example.tequedlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class AdminCourseList extends AppCompatActivity {
    ListView list;
    Button button;
    String value;
    DatabaseReference databaseReference;

    ArrayList<String>  maintitle = new ArrayList<String>();{



    };


    ArrayList<String> subtitle =new ArrayList<String>();{
//        subtitle.add("Sub Title 1");
//        subtitle.add("Sub Title 2");
//        subtitle.add("Sub Title 3");
//        subtitle.add("Sub Title 1");
//        subtitle.add("Sub Title 2");
//        subtitle.add("Sub Title 3");
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
        setContentView(R.layout.activity_admin_course_list);
        button= findViewById(R.id.add);
        final MyListAdapter adapter=new MyListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);


        {
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
                        System.out.println(childDataSnapshot.child("ai").getValue());   //gives the value for given keyname
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminCourseList.this,AdminAddCourse.class);
                startActivity(intent);
               finish();


            }
        });









    }



}





