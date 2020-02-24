package com.example.tequedlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminAttendance extends AppCompatActivity {
EditText editText;
    ListView list;
    Button button;
    SearchView searchview;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_attendance);

        final ArrayList<String> namelist = new ArrayList<String>();
        final ArrayList<String> collegelist =new ArrayList<String>();
        final ArrayList<String> date = new ArrayList<String>();
        final ArrayList<String> status = new ArrayList<String>();
        final ArrayList<String> searchResults = new ArrayList<String>();


        final MyAttendanceListAdapter adapter=new MyAttendanceListAdapter(this, namelist, collegelist,date,status);
        list=findViewById(R.id.attendance);
        list.setAdapter((ListAdapter) adapter);



        databaseReference = FirebaseDatabase.getInstance().getReference("StudentList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                    maintitle.add((childDataSnapshot.getKey()));
                    namelist.add((java.lang.String) childDataSnapshot.child("name").getValue());
                    collegelist.add((java.lang.String) childDataSnapshot.child("opt").getValue());
                    date.add((java.lang.String) childDataSnapshot.child("Date").getValue());
                    status.add((java.lang.String) childDataSnapshot.child("Status").getValue());
                    list.setAdapter((ListAdapter) adapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
