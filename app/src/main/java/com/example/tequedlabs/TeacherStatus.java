package com.example.tequedlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class TeacherStatus extends AppCompatActivity {
    EditText editText1,editText2,editText3,editText4,selectDate;
    DatabaseReference databaseReference;
    String a;
    String b;
    String c1;
    Button button;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_status);
        editText1=findViewById(R.id.abc1);
        editText2=findViewById(R.id.et2);
        selectDate=(EditText) findViewById(R.id.et3);
        editText4=findViewById(R.id.et4);

        Bundle bundle= getIntent().getExtras();//get me the intenet+the extra msg tbat was passed
        if (bundle != null)
        {
            a=bundle.getString("hello");
//            editText1.setText(""+bundle.getString("hello"));

        }

        databaseReference= FirebaseDatabase.getInstance().getReference("StudentList").child(a);

        databaseReference.child("opt").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue(String.class);
               editText2.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReference("StudentList").child(a);

        databaseReference.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue(String.class);
                editText1.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == selectDate) {
                    Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(TeacherStatus.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                    mHour=mMonth+1;
                    c1=mDay+"/"+mHour+"/"+mYear;
                    System.out.println(c1);
                }

            }
        });

        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(TeacherStatus.this);
                    builder.setCancelable(false);
                    builder.setTitle("Status");
                    builder.setMessage("Is the student present?👀👀👀");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText4.setText("present");
                            b= editText4.getText().toString();


                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText4.setText("Absent");
                            b= editText4.getText().toString();
                        }
                    });

                builder.create().show();
            }
        });

        button=findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("Date").setValue(c1);
                    databaseReference.child("Status").setValue(b).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(TeacherStatus.this, "Status updated", Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(TeacherStatus.this, "Try again later", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });



            }
        });
    }
    }

