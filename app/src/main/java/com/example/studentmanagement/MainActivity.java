package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button add_Details, view_Details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_Details=findViewById(R.id.addButton);
        view_Details=findViewById(R.id.displayButton);

        add_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                Intent intent = new Intent(MainActivity.this, Main2Activity_AddStudent.class);

                startActivity(intent);

            }
        });

        view_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                Intent intent = new Intent(MainActivity.this, Main3Activity.class);

                startActivity(intent);

            }
        });
    }
}
