package com.company.entity.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class CreateStudentRequest {
    @NotNull(message="Jmbg is required")
    @Size(min = 13, max = 13, message = "It is necessary to enter 13 chars")
    private String ucid; //jmbg

    @NotNull(message="Name is required")
    @Size(min = 3, max = 30, message = "Maximum number of chars is 30")
    private String name;

    @NotNull(message="Surname is required")
    @Size(min = 3, max = 30, message = "Maximum number of chars is 30")
    private String surname;

    @NotNull(message="Date of birth is required")
    @DateTimeFormat
    private Date dob;

    @NotNull(message="Address is required")
    @Size(min = 3, max = 30, message = "Maximum number of chars is 30")
    private String address;

    @NotNull(message="Email is required")
    @Size(min = 12, max = 30, message = "Maximum number of chars is 30")
    private String email;

    @Size(min = 6, max = 30, message = "Maximum number of chars is 30")
    private String phone;

    public CreateStudentRequest() {
    }

    public String getUcid() {
        return ucid;
    }

    public void setUcid(String ucid) {
        this.ucid = ucid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
