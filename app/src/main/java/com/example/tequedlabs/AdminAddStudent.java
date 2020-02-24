package com.example.tequedlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AdminAddStudent extends AppCompatActivity {
EditText textView1,textView2,textView3,selectDate,editText5;
Button button;
 DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);
        textView1=findViewById(R.id.text2);
        textView2=findViewById(R.id.text4);
        textView3=findViewById(R.id.text6);
        selectDate=findViewById(R.id.text8);
        editText5=findViewById(R.id.text10);
        button=findViewById(R.id.addstudent);

   String a= String.valueOf(textView1.getText());
        String b= String.valueOf(textView2.getText());
        String c= String.valueOf(textView3.getText());
        String d= String.valueOf(selectDate.getText());
        String q= String.valueOf(editText5.getText());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a= String.valueOf(textView1.getText());
                String b= String.valueOf(textView2.getText());
                String c= String.valueOf(textView3.getText());
                String d= String.valueOf(selectDate.getText());
                String q= String.valueOf(editText5.getText());
                databaseReference = FirebaseDatabase.getInstance().getReference("StudentList").child(String.valueOf(q));
                databaseReference.child("name").setValue(a);
                databaseReference.child("college").setValue(b);
                databaseReference.child("opt").setValue(c);
                databaseReference.child("dateOfJoin").setValue(d).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminAddStudent.this, "Data Added", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AdminAddStudent.this, NewStudentList.class);
//                            intent.putExtra("message", a);
                            startActivity(intent);
                            finish();
////                            onBackPressed();

                        } else {
                            Toast.makeText(AdminAddStudent.this, "Try again later", Toast.LENGTH_SHORT).show();
                        }

                    }

                });

            }
        });


    }
        }



