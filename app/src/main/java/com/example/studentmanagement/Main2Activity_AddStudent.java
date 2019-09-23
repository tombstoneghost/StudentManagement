package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Main2Activity_AddStudent extends AppCompatActivity {

    EditText name, college, phone;
    Button addStudent;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__add_student);

        name=findViewById(R.id.name);
        college=findViewById(R.id.college);
        phone=findViewById(R.id.phoneNumber);

        addStudent=findViewById(R.id.saveButton);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("student_table");

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentName = name.getText().toString();
                String studentCollege = college.getText().toString();
                long studentPhone = Long.parseLong(phone.getText().toString());

                HashMap<String, Object> values = new HashMap<>();
                values.put("name", studentName);
                values.put("college", studentCollege);
                values.put("phone", studentPhone);

                databaseReference.push().setValue(values);

                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_LONG).show();

                finish();

            }
        });

    }

}
