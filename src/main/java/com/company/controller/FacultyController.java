package com.company.controller;

import com.company.entity.dto.request.CreateFacultyRequest;
import com.company.entity.dto.request.UpdateFacultyRequest;
import com.company.entity.dto.response.FacultyResponse;
import com.company.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFaculty(@Valid @RequestBody CreateFacultyRequest createFacultyRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        FacultyResponse facultyResponse = facultyService.createFaculty(createFacultyRequest);
        return new ResponseEntity (facultyResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateFaculty(@PathVariable int id, @RequestBody UpdateFacultyRequest updateFacultyRequest,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        FacultyResponse facultyResponse = facultyService.updateFaculty(id, updateFacultyRequest);
        return new ResponseEntity (facultyResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteFaculty(@PathVariable int id) {
        if (id == 0){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        FacultyResponse facultyResponse = facultyService.deleteFaculty(id);
        return new ResponseEntity(facultyResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllFaculties(){
        FacultyResponse facultyList = facultyService.getAllFaculties();
        return new ResponseEntity(facultyList, HttpStatus.OK);
    }
}