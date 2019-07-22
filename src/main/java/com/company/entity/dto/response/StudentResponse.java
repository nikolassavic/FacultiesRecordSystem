package com.company.entity.dto.response;

import com.company.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class StudentResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Student student;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Student> studentList;

    public StudentResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
