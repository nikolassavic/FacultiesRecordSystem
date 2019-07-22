package com.company.service;

import com.company.dao.FacultyDao;
import com.company.entity.Faculty;
import com.company.entity.dto.request.CreateFacultyRequest;
import com.company.entity.dto.request.UpdateFacultyRequest;
import com.company.entity.dto.response.FacultyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FacultyService {

    private final FacultyDao facultyDao;

    @Autowired
    public FacultyService(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    public FacultyResponse createFaculty(CreateFacultyRequest createFacultyRequest){
        FacultyResponse facultyResponse = new FacultyResponse();
        Faculty requestFaculty = new Faculty(createFacultyRequest);

        Faculty faculty = facultyDao.createFaculty(requestFaculty);
        faculty.setStudentList(new ArrayList<>());

        facultyResponse.setMessage("Created");
        facultyResponse.setFaculty(faculty);

        return facultyResponse;
    }

    public FacultyResponse updateFaculty(int id, UpdateFacultyRequest updateFacultyRequest) {
        FacultyResponse facultyResponse = new FacultyResponse();

        Faculty dbFaculty = facultyDao.getById(id);

        if (updateFacultyRequest.getName() != null && !updateFacultyRequest.getName().equals(dbFaculty.getName())){
            dbFaculty.setName(updateFacultyRequest.getName());
        }
        if (updateFacultyRequest.getAddress() != null && !updateFacultyRequest.getAddress().equals(dbFaculty.getAddress())){
            dbFaculty.setAddress(updateFacultyRequest.getAddress());
        }

        if (updateFacultyRequest.getEmail() != null && !updateFacultyRequest.getEmail().equals(dbFaculty.getEmail())){
            dbFaculty.setEmail(updateFacultyRequest.getEmail());
        }
        if (updateFacultyRequest.getPhone() != null && !updateFacultyRequest.getPhone().equals(dbFaculty.getPhone())){
            dbFaculty.setPhone(updateFacultyRequest.getPhone());
        }

        Faculty faculty = facultyDao.updateFaculty(dbFaculty);
        faculty.setStudentList(new ArrayList<>());

        facultyResponse.setMessage("Updated");
        facultyResponse.setFaculty(faculty);

        return facultyResponse;
    }

    public FacultyResponse deleteFaculty(int id) {
        FacultyResponse facultyResponse = new FacultyResponse();

        Faculty faculty = facultyDao.getById(id);

        for (int i = faculty.getStudentList().size() - 1; i >= 0; i--){
            if (faculty.getStudentList().get(i).getFacultyList().size() > 1){
                faculty.getStudentList().remove(faculty.getStudentList().get(i));
            }
        }

        facultyDao.updateFaculty(faculty);

        facultyDao.deleteFaculty(id);

        facultyResponse.setMessage("Deleted");
        return facultyResponse;
    }

    public FacultyResponse getAllFaculties() {
        FacultyResponse facultyResponse = new FacultyResponse();
        facultyResponse.setMessage("OK");
        facultyResponse.setFacultyList(facultyDao.getAllFaculties());
        return facultyResponse;
    }
}
