package com.toonext.officeframe.jdbi;


import com.toonext.UserSession;
import com.toonext.officeframe.api.Employee;

public class EmployeeDAO {
    public EmployeeDAO(UserSession ses) {
    }

    public Employee findByUserId(Long id) {
        return new Employee();
    }
}
