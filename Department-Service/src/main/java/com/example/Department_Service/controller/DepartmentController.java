package com.example.Department_Service.controller;

import com.example.Department_Service.payload.DepartmentDto;
import com.example.Department_Service.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ds")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;

    }
    // http://localhost:8080/api/ds/department
    // http://localhost:8080/swagger-ui/index.html#
    @PostMapping("/department")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto dto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    // http://localhost:8080/api/ds/department/departmentCode/IT001

    @GetMapping("/department/departmentCode/{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("departmentCode") String departmentCode){
        DepartmentDto dto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    // http://localhost:8080/api/ds/department
    // http://localhost:9191/api/ds/department


    @GetMapping("/department")
    public List<DepartmentDto> getAllDepartments(){
        List<DepartmentDto> dtos = departmentService.getAllDepartments();
        return dtos;
    }
    // http://localhost:8080/api/ds/department/1

    @GetMapping("/department/id/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") long id){
        DepartmentDto dto = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    // http://localhost:8080/api/ds/department/1

    @PutMapping("/department/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable("id") long id,
                                                              @RequestBody DepartmentDto departmentDto){
        DepartmentDto dto = departmentService.updateDepartment(id, departmentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    // http://localhost:8080/api/ds/department/1

    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") long id){
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<String>("Department entity deleted",HttpStatus.OK);
    }
}
