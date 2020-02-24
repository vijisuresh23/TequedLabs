package com.example.tequedlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherAttendance extends AppCompatActivity {

    ListView listView,listView1;
 Button button;
    int count=0;
    String name;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance);
        final ArrayList<String> student = new ArrayList<String>();
        final ArrayList<String> rollnumber = new ArrayList<String>();
   final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,student);
        listView = findViewById(R.id.listview);

        listView.setAdapter(arrayAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("StudentList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                    maintitle.add((childDataSnapshot.getKey()));
                    student.add((java.lang.String) childDataSnapshot.child("name").getValue());
                    rollnumber.add((java.lang.String) childDataSnapshot.getKey());
                    System.out.println(student);
                    System.out.println(rollnumber);
                     count=count+1;

                    listView.setAdapter(arrayAdapter);
                }
                System.out.println(count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<=position;i++){

                    if(position==i){
                        Intent intent=new Intent(TeacherAttendance.this,TeacherStatus.class);
                        for(int j = 0; j < rollnumber.size(); j++) {
                            if(i==j)
//                            System.out.print(houseAddress.get(i));
                            {
                              name= rollnumber.get(i).toString();
                            }
                        }
                        intent.putExtra("hello",name);
                        startActivity(intent);
                    }
                }


            }
        });








    }
    }

