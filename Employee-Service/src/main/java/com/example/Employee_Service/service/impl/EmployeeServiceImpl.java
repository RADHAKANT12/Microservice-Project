package com.example.Employee_Service.service.impl;


import com.example.Employee_Service.entity.Employee;
import com.example.Employee_Service.exception.ResourceNotFoundException;
import com.example.Employee_Service.payload.APIResponseDto;
import com.example.Employee_Service.payload.DepartmentDto;
import com.example.Employee_Service.payload.EmployeeDto;
import com.example.Employee_Service.repository.EmployeeRepository;
import com.example.Employee_Service.service.ApiClient;
import com.example.Employee_Service.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepo;
    private ModelMapper mapper;
    private ApiClient apiClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo,ModelMapper mapper,ApiClient apiClient){
        this.employeeRepo=employeeRepo;
        this.mapper=mapper;
        this.apiClient=apiClient;

    }
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee employee1 = employeeRepo.save(employee);
        EmployeeDto dto = mapToDto(employee1);
        return dto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);
        return employee;

    }
    private EmployeeDto mapToDto(Employee employee1) {
        EmployeeDto dto = mapper.map(employee1, EmployeeDto.class);
        return dto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDto> dtos = employees.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());

        return dtos;
    }
    //@CircuitBreaker(name ="EMPLOYEE-BREAKERS", fallbackMethod = "getDefaultDepartment")
    @Retry(name ="EMPLOYEE-BREAKERS", fallbackMethod = "getDefaultDepartment")

    @Override
    public APIResponseDto getEmployeeById(long id) {
        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "Id", id)
        );
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        EmployeeDto employeeDto = mapToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
    public APIResponseDto getDefaultDepartment(long id, Exception exception) {
        // This method will be called if the circuit is open or if an exception occurs
        // You can return a default response here or perform other fallback logic
        // For now, let's create a default response with empty department
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepo.findById(id).get();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");
        EmployeeDto employeeDto = mapToDto(employee);


        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }



   /* @Override
    public EmployeeDto getEmployeeById(long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "Id", id)
        );
        return mapToDto(employee);
    }*/

    @Override
    public EmployeeDto updateEmployeeById(long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "Id", id)
        );
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee employee1 = employeeRepo.save(employee);

        return mapToDto(employee1);
    }

    @Override
    public void deleteEmployeeById(long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "Id", id)
        );
        employeeRepo.delete(employee);

    }
}
