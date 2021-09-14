package com.example.employeedirectory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    EmployeeRepo employeeRepo;
    LiveData<List<EmployeeEntity>> listLiveData_model;
    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepo=new EmployeeRepo(application);
        listLiveData_model=employeeRepo.listLiveData;
    }
    public void insert(EmployeeEntity employeeEntity){
        employeeRepo.insert(employeeEntity);
    }
    public void update(EmployeeEntity employeeEntity){
        employeeRepo.update(employeeEntity);
    }
    public void delete(EmployeeEntity employeeEntity){
        employeeRepo.delete(employeeEntity);
    }
    public LiveData<List<EmployeeEntity>> liveData(){
        return listLiveData_model;
    }
}