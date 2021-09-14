package com.example.employeedirectory;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeRepo {
    LiveData<List<EmployeeEntity>> listLiveData;
    EmployeeDatabase database;
    public EmployeeRepo(Application application){
        database=EmployeeDatabase.getDatabase(application);
        listLiveData=database.employeeDAO().retriveliveData();
    }
    public class AsyncTaskForInsert extends AsyncTask<EmployeeEntity,Void,Void>{

        @Override
        protected Void doInBackground(EmployeeEntity... employeeEntities) {
            database.employeeDAO().insert(employeeEntities[0]);
            return null;
        }
    }
    public class AsyncTaskForUpdate extends AsyncTask<EmployeeEntity,Void,Void>{

        @Override
        protected Void doInBackground(EmployeeEntity... employeeEntities) {
            database.employeeDAO().update(employeeEntities[0]);
            return null;
        }
    }
    public class AsyncTaskForDelete extends AsyncTask<EmployeeEntity,Void,Void>{

        @Override
        protected Void doInBackground(EmployeeEntity... employeeEntities) {
            database.employeeDAO().delete(employeeEntities[0]);
            return null;
        }
    }
    public void insert(EmployeeEntity employeeEntity){
        new AsyncTaskForInsert().execute(employeeEntity);
    }
    public void update(EmployeeEntity employeeEntity){
        new AsyncTaskForUpdate().execute(employeeEntity);
    }
    public void delete(EmployeeEntity employeeEntity){
        new AsyncTaskForDelete().execute(employeeEntity);
    }

}
