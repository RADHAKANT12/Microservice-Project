package com.example.Employee_Service.service;

import com.example.Employee_Service.payload.APIResponseDto;
import com.example.Employee_Service.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    public EmployeeDto saveEmployee(EmployeeDto employeeDto);
    public List<EmployeeDto> getAllEmployees();

    //public EmployeeDto getEmployeeById(long id);
    public APIResponseDto getEmployeeById(long id);

    public EmployeeDto updateEmployeeById(long id,EmployeeDto employeeDto);
    void deleteEmployeeById(long id);

}
