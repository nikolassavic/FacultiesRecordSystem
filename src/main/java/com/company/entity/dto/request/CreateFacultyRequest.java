package com.company.entity.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateFacultyRequest {
    @NotNull(message="Name is required")
    @Size(min = 3, max = 30, message = "Maximum number of chars is 30")
    private String name;

    @NotNull(message="Address is required")
    @Size(min = 3, max = 30, message = "Maximum number of chars is 30")
    private String address;

    @NotNull(message="Email is required")
    @Email(message = "Invalid email format")
    @Size(min = 12, max = 30, message = "Maximum number of chars is 30")
    private String email;

    @NotNull(message="Phone is required")
    @Size(min = 6, max = 30, message = "Maximum number of chars is 30")
    private String phone;

    public CreateFacultyRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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