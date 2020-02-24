package com.example.tequedlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdminAddCourse extends AppCompatActivity {
    EditText ed1, ed2;
    Button button;
    DatabaseReference databaseReference;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_course);

        ed1 = findViewById(R.id.edittext1);
        ed2 = findViewById(R.id.edittext2);
        button = findViewById(R.id.button);


        button.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String a = ed1.getText().toString();
                final String b = ed2.getText().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference("CourseList").child(a);
                databaseReference.child("courseName").setValue(b).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminAddCourse.this, "Data Added", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AdminAddCourse.this, NewCourse.class);
//                            intent.putExtra("message", a);
                            startActivity(intent);
                            finish();
//                            onBackPressed();

                        } else {
                            Toast.makeText(AdminAddCourse.this, "Try again later", Toast.LENGTH_SHORT).show();
                        }

                    }

                });

            }
        });


    }




    }

