package com.example.studentmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.StudentViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getName();

    private final ListItemClickListener itemClickListener;

    private static int viewHolderCount;

    private ArrayList<StudentClass> studentClassArrayList;

    public interface ListItemClickListener{
        void onListItemClickListener(int clickedItemIndex);
    }

    public RecyclerAdapter(ArrayList<StudentClass> studentClassArrayList, ListItemClickListener itemClickListener)
    {
        this.studentClassArrayList=studentClassArrayList;
        this.itemClickListener=itemClickListener;
        viewHolderCount=0;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_layout, parent,
                false);

        StudentViewHolder studentViewHolder = new StudentViewHolder(itemView);

        viewHolderCount = viewHolderCount + 1;

        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.StudentViewHolder holder, int position) {
        StudentClass student = studentClassArrayList.get(position);
        holder.studentName.setText(student.name);
        holder.studentCollege.setText(student.college);
        holder.studentPhone.setText(""+student.phone);
    }

    @Override
    public int getItemCount() {
        return studentClassArrayList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView studentName, studentCollege, studentPhone;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName=itemView.findViewById(R.id.recycler_std_name);
            studentCollege=itemView.findViewById(R.id.recycler_std_college);
            studentPhone=itemView.findViewById(R.id.recycler_std_phone);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onListItemClickListener(clickedPosition);
        }
    }
}
