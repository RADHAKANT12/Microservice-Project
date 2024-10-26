package com.example.Department_Service.payload;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorDetails {
    private Date timeStamp;
    private String description;
    private String message;

    public ErrorDetails(Date timeStamp,String description,String message){
        this.timeStamp=timeStamp;
        this.description=description;
        this.message=message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
