package com.example.employeedirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.employeedirectory.databinding.ActivityMainBinding;
import com.example.employeedirectory.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding updateBinding;
    EmployeeEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateBinding= DataBindingUtil.setContentView(this,R.layout.activity_update);
        updateBinding.etEmpaddupdate.setText(getIntent().getStringExtra("name_key"));
        updateBinding.etEmpaddupdate.setText(getIntent().getStringExtra("address_key"));
        updateBinding.etEmpsalaryupdate.setText(getIntent().getStringExtra("salary_key"));
        updateBinding.etEmpidupdate.setText(getIntent().getStringExtra("id_key"));
    }

    public void updateData(View view) {
        entity=new EmployeeEntity();
        entity.setName(updateBinding.etEmpnameupdate.getText().toString());
        entity.setEmp_salary(updateBinding.etEmpsalaryupdate.getText().toString());
        entity.setEmp_address(updateBinding.etEmpaddupdate.getText().toString());
        entity.setEmp_id(updateBinding.etEmpidupdate.getText().toString());
        /*MainActivity.database.employeeDAO().update(entity);*/
        Toast.makeText(this, "updated :"+updateBinding.etEmpnameupdate.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}