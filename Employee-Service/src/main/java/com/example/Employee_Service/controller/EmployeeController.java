package com.example.Employee_Service.controller;

import com.example.Employee_Service.payload.APIResponseDto;
import com.example.Employee_Service.payload.EmployeeDto;
import com.example.Employee_Service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/es")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService =employeeService;
    }
    // http://localhost:8081/api/es/employee
    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto dto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    // http://localhost:8081/api/es/employee

    @GetMapping("/employee")
    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeDto> dtos = employeeService.getAllEmployees();
        return dtos;

    }
    // http://localhost:8081/api/es/employee/id/1

  /*  @GetMapping("/employee/id/{id}")
    public ResponseEntity<EmployeeDto> getEmployeebyId(@PathVariable("id") long id){
        EmployeeDto dto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }*/
  @GetMapping("/employee/id/{id}")
  public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") long id){
      APIResponseDto responseDto = employeeService.getEmployeeById(id);
      return new ResponseEntity<>(responseDto,HttpStatus.OK);
  }


    // http://localhost:8081/api/es/employee/update/1

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long id ,@RequestBody EmployeeDto employeeDto){
        EmployeeDto dto = employeeService.updateEmployeeById(id, employeeDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    // http://localhost:8081/api/es/employee/delete/1

    @DeleteMapping("employee/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee Entity Deleted !!!",HttpStatus.OK);
    }
 }
