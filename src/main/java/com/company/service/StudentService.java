package com.company.service;

import com.company.dao.FacultyDao;
import com.company.dao.StudentDao;
import com.company.entity.Faculty;
import com.company.entity.Student;
import com.company.entity.dto.request.CreateStudentRequest;
import com.company.entity.dto.request.UpdateStudentRequest;
import com.company.entity.dto.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentDao studentDao;
    private final FacultyDao facultyDao;

    @Autowired
    public StudentService(StudentDao studentDao, FacultyDao facultyDao) {
        this.studentDao = studentDao;
        this.facultyDao = facultyDao;
    }

    public StudentResponse createStudent(int id, CreateStudentRequest createStudentRequest) {
        StudentResponse studentResponse = new StudentResponse();
        Student requestStudent = new Student(createStudentRequest);
        Student student = null;

        Faculty faculty = facultyDao.getById(id);
        studentDao.createStudent(requestStudent, faculty);
        student = studentDao.getById(createStudentRequest.getUcid());

        studentResponse.setMessage("Created");
        studentResponse.setStudent(student);

        return studentResponse;
    }

    public StudentResponse addFacultyToStudent(String ucid, int id){
        StudentResponse studentResponse = new StudentResponse();

        Student student = studentDao.getById(ucid);
        Faculty faculty = facultyDao.getById(id);

        faculty.getStudentList().add(student);

        studentDao.addFacultyToStudent(faculty);

        studentResponse.setMessage("Added");
        studentResponse.setStudent(studentDao.getById(ucid));

        return studentResponse;
    }

    public StudentResponse getStudent(String ucid) {
        StudentResponse studentResponse = new StudentResponse();

        Student student = studentDao.getById(ucid);

        studentResponse.setMessage("OK");
        studentResponse.setStudent(student);

        return studentResponse;
    }

    public StudentResponse getStudentList(int id) {
        StudentResponse studentResponse = new StudentResponse();
        Faculty faculty = null;

        faculty = facultyDao.getById(id);

        studentResponse.setMessage("OK");
        studentResponse.setStudentList(sortStudent(faculty.getStudentList()));

        return studentResponse;
    }

    public StudentResponse getStudentOfManyFaculties() {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentList(new ArrayList<>());

        List<Student> studentList = studentDao.getAllStudents();

        for (Student student : studentList) {
            if (student.getFacultyList().size() > 1){
                studentResponse.getStudentList().add(student);
            }
        }

        studentResponse.setMessage("OK");

        return studentResponse;
    }

    public StudentResponse updateStudent(String ucid, UpdateStudentRequest updateStudentRequest){
        StudentResponse studentResponse = new StudentResponse();

        Student dbStudent = studentDao.getById(ucid);


        if (updateStudentRequest.getUcid() != null && !updateStudentRequest.getUcid().equals(dbStudent.getUcid())){
            dbStudent.setUcid(updateStudentRequest.getUcid());
        }
        if (updateStudentRequest.getName() != null && !updateStudentRequest.getName().equals(dbStudent.getName())){
            dbStudent.setName(updateStudentRequest.getName());
        }
        if (updateStudentRequest.getSurname() != null && !updateStudentRequest.getSurname().equals(dbStudent.getSurname())){
            dbStudent.setSurname(updateStudentRequest.getSurname());
        }
        if (updateStudentRequest.getDob() != null && !updateStudentRequest.getDob().equals(dbStudent.getDob())){
            dbStudent.setDob(updateStudentRequest.getDob());
        }
        if (updateStudentRequest.getAddress() != null && !updateStudentRequest.getAddress().equals(dbStudent.getAddress())){
            dbStudent.setAddress(updateStudentRequest.getAddress());
        }
        if (updateStudentRequest.getEmail() != null && !updateStudentRequest.getEmail().equals(dbStudent.getEmail())){
            dbStudent.setEmail(updateStudentRequest.getEmail());
        }
        if (updateStudentRequest.getPhone() != null && !updateStudentRequest.getPhone().equals(dbStudent.getPhone())){
            dbStudent.setPhone(updateStudentRequest.getPhone());
        }

        Student student = studentDao.updateStudent(dbStudent);

        studentResponse.setMessage("Updated");
        studentResponse.setStudent(student);

        return studentResponse;
    }

    public StudentResponse deleteStudent(String ucid){
        StudentResponse studentResponse = new StudentResponse();

        Student student = studentDao.getById(ucid);

        for (Faculty faculty : student.getFacultyList()) {
            faculty.getStudentList().remove(student);
            facultyDao.updateFaculty(faculty);
        }

        studentDao.deleteStudent(ucid);
        studentResponse.setMessage("Deleted");
        return studentResponse;
    }

    private static List<Student> sortStudent(List<Student> studentList) {
        List<Student> list = studentList;
        Boolean flag = true;
        Student temp;
        while (flag) {
            flag = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if ((list.get(i).getName().concat(list.get(i).getSurname()))
                        .compareToIgnoreCase(list.get(i + 1).getName().concat(list.get(i + 1).getSurname())) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    flag = true;
                }
            }
        }
        return list;
    }
}
