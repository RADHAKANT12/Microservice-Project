package com.example.Employee_Service.repository;

import com.example.Employee_Service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
