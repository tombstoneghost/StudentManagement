package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener {

    private static final String TAG = Main3Activity.class.getName();

    ProgressBar progressBar;
    StudentClass studentClass;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<StudentClass> studentData = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("student_table");

        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.RecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerAdapter = new RecyclerAdapter(studentData, this);

        recyclerView.setAdapter(recyclerAdapter);

        Log.d(TAG, "database values" + databaseReference);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    Log.d(TAG, "data value: " + data);
                    String name =String.valueOf(data.child("name").getValue());
                    long phone = Long.parseLong(String.valueOf(data.child("phone").getValue()));
                    String college = String.valueOf(data.child("college").getValue());

                    studentData.add(new StudentClass(name, college, phone));
                }

                recyclerAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                /*Log.d(TAG,databaseError.getMessage());*/

                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onListItemClickListener(int clickedItemIndex) {

    }
}
