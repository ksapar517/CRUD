package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDto {

    private long id;
    private String name;
    private String lastname;
    private Date birthday;


    public void setError(String missingRequiredFields) {
    }

    public void setMessege(String missingRequiredFields) {
    }

    public void setStatusCode(int i) {
    }
}

