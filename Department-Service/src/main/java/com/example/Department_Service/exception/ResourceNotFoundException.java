package com.example.Department_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldValue;
    private long fieldId;

    public ResourceNotFoundException(String resourceName,String fieldValue,long fieldId){
        super(String.format("%s not found with '%s':%S",resourceName,fieldValue,fieldId));
        this.resourceName=resourceName;
        this.fieldValue=fieldValue;
        this.fieldId=fieldId;

    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public long getFieldId() {
        return fieldId;
    }
}
