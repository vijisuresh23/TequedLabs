package com.example.tequedlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewStudentList extends AppCompatActivity {
    ListView list;
//    Button button;
    SearchView searchView;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student_list);
        final ArrayList<String> namelist = new ArrayList<String>();{



        }
        final ArrayList<String> collegelist =new ArrayList<String>();{

        }
        final ArrayList<String> courselist = new ArrayList<String>();




        final ArrayList<String> dateofjoin =new ArrayList<String>();{

        }


        final MyStudentListAdapter adapter=new MyStudentListAdapter(this, namelist, collegelist,courselist,dateofjoin);
        list=findViewById(R.id.list1);
        list.setAdapter((ListAdapter) adapter);



        databaseReference = FirebaseDatabase.getInstance().getReference("StudentList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                    maintitle.add((childDataSnapshot.getKey()));
                    namelist.add((java.lang.String) childDataSnapshot.child("name").getValue());
                    collegelist.add((java.lang.String) childDataSnapshot.child("college").getValue());
                    courselist.add((java.lang.String) childDataSnapshot.child("opt").getValue());
                    dateofjoin.add((java.lang.String) childDataSnapshot.child("dateOfJoin").getValue());
                    list.setAdapter((ListAdapter) adapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
