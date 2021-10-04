package com.example.myfirebasedb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    DisplayActivity ctx;
    List<StudentModel> list;

    public MyAdapter(DisplayActivity ctx, List<StudentModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(ctx)
                .inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.t_name.setText(list.get(position).getName());
        holder.t_email.setText(list.get(position).getEmail());
        holder.t_mobile.setText(list.get(position).getMobile());
        holder.t_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference= FirebaseDatabase.getInstance()
                        .getReference();
                Query query=reference.child("student").orderByChild("mobile")
                        .equalTo(list.get(position).getMobile());
                final HashMap<String ,Object> map=new HashMap<>();
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds:snapshot.getChildren()){
                            map.put("mobile",123456789);
                            ds.getRef().updateChildren(map);
                        }
                        Toast.makeText(ctx, "updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        holder.t_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference= FirebaseDatabase.getInstance()
                        .getReference();
                Query query=reference.child("student").orderByChild("mobile")
                        .equalTo(list.get(position).getMobile());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds:snapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(ctx, "removed", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // reference.child(list.get(position).getName()).removeValue();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t_name, t_email, t_mobile,t_edit,t_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t_email = itemView.findViewById(R.id.tv_email);
            t_name = itemView.findViewById(R.id.tv_name);
            t_mobile = itemView.findViewById(R.id.tv_mobile);
            t_edit= itemView.findViewById(R.id.tv_edit);
            t_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}