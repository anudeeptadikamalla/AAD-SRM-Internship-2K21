package com.example.employeedirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.employeedirectory.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static EmployeeDatabase database;
    EmployeeEntity entity;
    ActivityMainBinding binding;
    List<EmployeeEntity> entityList;
    static EmployeeViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        /*database= Room.databaseBuilder(this,EmployeeDatabase.class,"srm")
                .allowMainThreadQueries()
                .build();*/
        //for live data
        viewModel=ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.listLiveData_model.observe(this, new Observer<List<EmployeeEntity>>() {
            @Override
            public void onChanged(List<EmployeeEntity> employeeEntities) {
                EmployeeAdapter adapter=new EmployeeAdapter(MainActivity.this,employeeEntities);
                binding.rec.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.rec.setAdapter(adapter);
            }
        });
    }

    public void saveData(View view) {
        entity=new EmployeeEntity();
        entity.setName(binding.etEmpname.getText().toString());
        entity.setEmp_id(binding.etEmpid.getText().toString());
        entity.setEmp_address(binding.etEmpadd.getText().toString());
        entity.setEmp_salary(binding.etEmpsalary.getText().toString());
        //database.employeeDAO().insert(entity);
        viewModel.insert(entity);
        Toast.makeText(this, "insert success \n"+
                binding.etEmpname.getText().toString(), Toast.LENGTH_SHORT).show();
        binding.etEmpname.setText("");
        binding.etEmpid.setText("");
        binding.etEmpsalary.setText("");
        binding.etEmpadd.setText("");


    }

   /* public void retriveData(View view) {
        entityList=database.employeeDAO().retrive();
        EmployeeAdapter adapter=new EmployeeAdapter(this,entityList);
        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        binding.rec.setAdapter(adapter);
    }*/
}