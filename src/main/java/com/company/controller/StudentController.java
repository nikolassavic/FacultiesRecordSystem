package com.company.controller;

import com.company.entity.dto.request.CreateStudentRequest;
import com.company.entity.dto.request.UpdateStudentRequest;
import com.company.entity.dto.response.StudentResponse;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/faculties/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFaculty(@PathVariable int id, @Valid @RequestBody CreateStudentRequest createStudentRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        StudentResponse studentResponse = studentService.createStudent(id, createStudentRequest);
        return new ResponseEntity (studentResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ucid}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateStudent(@PathVariable String ucid, @RequestBody UpdateStudentRequest updateStudentRequest,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        StudentResponse studentResponse = studentService.updateStudent(ucid, updateStudentRequest);
        return new ResponseEntity (studentResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{ucid}/faculties/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addFacultyToStudent(@PathVariable String ucid, @PathVariable int id){
        if (ucid.length() != 13 || id == 0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        StudentResponse studentResponse = studentService.addFacultyToStudent(ucid, id);
        return new ResponseEntity(studentResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{ucid}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudent(@PathVariable String ucid){
        if (ucid == null || ucid.equals("")){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        StudentResponse studentResponse = studentService.getStudent(ucid);
        return new ResponseEntity(studentResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/faculties/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudentList(@PathVariable int id){
        if (id == 0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        StudentResponse studentResponse = studentService.getStudentList(id);
        return new ResponseEntity(studentResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/faculties/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStudentOfManyFaculties(){
        StudentResponse studentResponse = studentService.getStudentOfManyFaculties();
        return new ResponseEntity( studentResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ucid}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteStudent(@PathVariable String ucid){
        if (ucid.length() != 13){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        StudentResponse studentResponse = studentService.deleteStudent(ucid);
        return new ResponseEntity(studentResponse, HttpStatus.OK);
    }
}