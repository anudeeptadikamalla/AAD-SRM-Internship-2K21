package com.example.employeedirectory;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = EmployeeEntity.class,version = 1,exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();
    static EmployeeDatabase database;
    //for live data
    public static synchronized EmployeeDatabase getDatabase(Context context){
        if (database==null){
            database= Room.databaseBuilder(context,EmployeeDatabase.class,"srm")
                    .allowMainThreadQueries().build();
        }
        return database;
    }
}
