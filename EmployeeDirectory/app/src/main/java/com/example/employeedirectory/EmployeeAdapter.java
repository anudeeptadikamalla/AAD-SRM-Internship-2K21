package com.example.employeedirectory;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewholder> {
    Context ct;
    List<EmployeeEntity> entityList;

    public EmployeeAdapter(Context ct, List<EmployeeEntity> entityList) {
        this.ct = ct;
        this.entityList = entityList;
    }

    @NonNull
    @Override
    public EmployeeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewholder(LayoutInflater.from(ct)
                .inflate(R.layout.each_employee_row,parent,false));



    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewholder holder, int position) {
        holder.emp_id.setText(entityList.get(position).getEmp_id());
        holder.name.setText(entityList.get(position).getName());
        holder.address.setText(entityList.get(position).getEmp_address());
        holder.salary.setText(entityList.get(position).getEmp_salary());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.viewModel.delete(entityList.get(position));
                Toast.makeText(ct, "Deleted :"+entityList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    public class EmployeeViewholder extends RecyclerView.ViewHolder {
        TextView edit,delete,name,address,emp_id,salary;

        public EmployeeViewholder(@NonNull View itemView) {
            super(itemView);
            edit=itemView.findViewById(R.id.tv_edit);
            delete=itemView.findViewById(R.id.tv_delete);
            name=itemView.findViewById(R.id.tv_Name);
            address=itemView.findViewById(R.id.tv_Address);
            salary=itemView.findViewById(R.id.tv_salary);
            emp_id=itemView.findViewById(R.id.tv_id);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String e_name=name.getText().toString();
                    String e_address=address.getText().toString();
                    String e_salary=salary.getText().toString();
                    String e_Id=emp_id.getText().toString();
                    Intent i=new Intent(ct, UpdateActivity.class);
                    i.putExtra("name_key",e_name);
                    i.putExtra("address_key",e_address);
                    i.putExtra("salary_key",e_salary);
                    i.putExtra("id_key",e_Id);
                    ct.startActivity(i);
                }
            });
            
        }
    }
}