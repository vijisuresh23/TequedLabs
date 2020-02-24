package com.example.tequedlabs;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class AdminAnnouncement extends AppCompatActivity implements View.OnClickListener {
    EditText selectDate, selectTime;
    EditText text, caption;
    Button button,button1;
    private int mYear, mMonth, mDay, mHour, mMinute,mH;
    DatabaseReference databaseReference;
    String date,time,a,b;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_announcement);
        selectDate = (EditText) findViewById(R.id.date);
        selectTime = (EditText) findViewById(R.id.time);
        text = findViewById(R.id.text);
        caption = findViewById(R.id.caption);
        button = findViewById(R.id.post);
        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);


    }

    public void onClick(View view) {

        if (view == selectDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
          mH= mMonth-1;
            date = mDay + "/" + mH + "/" + mYear;
        }
        if (view == selectTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            selectTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

            time=mHour+":"+mMinute;
        }

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final String a = caption.getText().toString();
        String b = text.getText().toString();
        databaseReference= FirebaseDatabase.getInstance().getReference("Announcement").child(a);
        databaseReference.child("Date").setValue(date);
        databaseReference.child("Time").setValue(time);
        databaseReference.child("Data").setValue(b).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AdminAnnouncement.this, "Done", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdminAnnouncement.this,Announcement.class);
                startActivity(intent);



                } else {
                    Toast.makeText(AdminAnnouncement.this, "Try again later", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
});






    }
}

