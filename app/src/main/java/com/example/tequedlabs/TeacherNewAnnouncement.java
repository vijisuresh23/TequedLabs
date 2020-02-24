package com.example.tequedlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherNewAnnouncement extends AppCompatActivity {
    ListView listView;
    String a;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_new_announcement);
        listView=findViewById(R.id.list_item);
        final ArrayList<String> list=new ArrayList<String>();
        final ArrayList<String> rollnumber = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(TeacherNewAnnouncement.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
//            System.out.println(a);


        databaseReference = FirebaseDatabase.getInstance().getReference("Announcement");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                    maintitle.add((childDataSnapshot.getKey()));
                    list.add((java.lang.String) childDataSnapshot.getKey());
                    rollnumber.add((java.lang.String) childDataSnapshot.getKey());
                    listView.setAdapter(arrayAdapter);
                    System.out.println("list="+list);
                    System.out.println("rollnumber="+rollnumber);
                }

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
                        for(int j = 0; j <rollnumber.size(); j++) {
                            if(i==j)
//                            System.out.print(houseAddress.get(i));
                            {
                                a= rollnumber.get(i).toString();
                                databaseReference = FirebaseDatabase.getInstance().getReference("Announcement").child(a);
                                databaseReference.child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String value= dataSnapshot.getValue(String.class);
                                        AlertDialog.Builder builder =new AlertDialog.Builder(TeacherNewAnnouncement.this);
                                        builder.setCancelable(false);
                                        builder.setTitle(a);
                                        builder.setMessage(value);
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {


                                            }
                                        });



                                        builder.create().show();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                    }
                }

            }
        });



    }
}
