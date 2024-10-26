package com.example.Department_Service.service.impl;

import com.example.Department_Service.entity.Department;
import com.example.Department_Service.exception.ResourceNotFoundException;
import com.example.Department_Service.payload.DepartmentDto;
import com.example.Department_Service.repository.DepartmentRepository;
import com.example.Department_Service.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private ModelMapper mapper;
    private DepartmentRepository departmentRepo;

    public DepartmentServiceImpl(ModelMapper mapper,DepartmentRepository departmentRepo){
        this.mapper=mapper;
        this.departmentRepo=departmentRepo;

    }
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = mapToEntity(departmentDto);
        Department department1 = departmentRepo.save(department);
        DepartmentDto dto = mapToDto(department1);
        return dto;
    }

 


    private Department mapToEntity(DepartmentDto departmentDto) {
        Department department = mapper.map(departmentDto, Department.class);
        return department;
    }
    private DepartmentDto mapToDto(Department department1) {
        DepartmentDto departmentDto = mapper.map(department1, DepartmentDto.class);
        return departmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentDto> dtos = departments.stream().map(department -> mapToDto(department)).collect(Collectors.toList());
        return dtos;
    }



    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepo.findByDepartmentCode(departmentCode);
        return mapToDto(department);
    }
    @Override
    public DepartmentDto getDepartmentById(long id) {
        Department department = departmentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("department", "Id", id)

        );
        return mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(long id, DepartmentDto departmentDto) {
        Department department = departmentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("department", "Id", id)

        );
        department.setId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());
        Department department1 = departmentRepo.save(department);

        return mapToDto(department1);
    }

    @Override
    public void deleteDepartmentById(long id) {
        Department department = departmentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("department", "Id", id)

        );
        departmentRepo.delete(department);

    }
}
