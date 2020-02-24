package com.example.tequedlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText email;
EditText password;
Button admin;
Button teacher;
FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.et1);
        password=findViewById(R.id.et2);
        admin=findViewById(R.id.button1);
        teacher=findViewById(R.id.button2);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void goToAdminHomePage(View view) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Enter Email Id");
            email.setText("");
            email.requestFocus();
            return;
        }
        if (password.getText().toString().equals("") || password.getText().toString().length() < 6) {
            password.setError("Enter a Valid Password");
            password.setText("");
            password.requestFocus();
            return;
        }
        String Admin= "admin";
     if(email.getText().toString().startsWith("admin")){
         firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()) {

                     Intent intent= new Intent(MainActivity.this,AdminHomePage.class);
                     startActivity(intent);

                 }
             }
         });

     }
else{
         email.setError("username or password invalid ");
         email.setText("");
         password.setText("");
         email.requestFocus();
         return;
     }

    }

    public void goToTeacherHomePage(View view) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Enter Email Id");
            email.setText("");
            email.requestFocus();
            return;
        }
        if (password.getText().toString().equals("") || password.getText().toString().length() < 6) {
            password.setError("Enter a Valid Password");
            password.setText("");
            password.requestFocus();
            return;
        }

        if(email.getText().toString().startsWith("teacher")){
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent intent= new Intent(MainActivity.this,TeacherHomePage.class);
                        startActivity(intent);

                    }
                }
            });

        }
        else{
            email.setError("Enter  valid Email Id");
            email.setText("");
            password.setText("");
            email.requestFocus();
            return;
        }




    }
}

