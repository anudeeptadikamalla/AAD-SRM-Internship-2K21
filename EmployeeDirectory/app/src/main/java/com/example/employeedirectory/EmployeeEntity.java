package com.example.employeedirectory;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "emptable")
public class EmployeeEntity {
    @ColumnInfo(name = "empName")
    String name;
    @ColumnInfo(name = "empId")
    @PrimaryKey
    @NonNull
    String emp_id;
    @ColumnInfo(name = "empSalary")
    String emp_salary;
    @ColumnInfo(name = "empAddress")
    String emp_address;

    /*right click->generate getters and setters->ok->select all*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(String emp_salary) {
        this.emp_salary = emp_salary;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }
}
