package com.example.Department_Service.service;

import com.example.Department_Service.payload.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    public DepartmentDto saveDepartment(DepartmentDto departmentDto);

    public DepartmentDto getDepartmentByCode(String departmentCode);
    public List<DepartmentDto> getAllDepartments();
    public DepartmentDto getDepartmentById(long id);
    public DepartmentDto updateDepartment(long id,DepartmentDto departmentDto);

    public void deleteDepartmentById(long id);
}
