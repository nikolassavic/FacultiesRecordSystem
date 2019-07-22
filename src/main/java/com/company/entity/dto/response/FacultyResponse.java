package com.company.entity.dto.response;

import com.company.entity.Faculty;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class FacultyResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Faculty faculty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Faculty> facultyList;

    public FacultyResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }
}
