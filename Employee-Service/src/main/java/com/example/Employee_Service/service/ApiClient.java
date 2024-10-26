package com.example.Employee_Service.service;

import com.example.Employee_Service.payload.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "DEPARTMENT-SERVICE", url = "http://localhost:8080" )
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {
    // http://localhost:8080/api/ds/department/departmentCode/IT001

    @GetMapping("/api/ds/department/departmentCode/{departmentCode}")
    DepartmentDto getDepartmentByCode(@PathVariable("departmentCode") String departmentCode);




}
