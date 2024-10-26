package com.example.Department_Service.payload;

public class DepartmentDto {
    private long Id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
