package com.company.entity;

import com.company.entity.dto.request.CreateStudentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "students")
@DynamicUpdate
public class Student {

  @Id
  private String ucid; //jmbg
  @Column
  private String name;
  @Column
  private String surname;
  @Column
  @JsonFormat(
          timezone = "Europe/Belgrade",
          shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd")
  private Date dob;
  @Column
  private String address;
  @Column
  private String email;
  @Column
  private String phone;
  @Column(updatable = false)
  @JsonFormat(
          shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd hh:mm:ss")
  private Timestamp created;
  @Column
  @JsonFormat(
          shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd hh:mm:ss")
  private Timestamp updated;

  @ManyToMany(mappedBy = "studentList",cascade = {CascadeType.MERGE, CascadeType.PERSIST,
  CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<Faculty> facultyList;

  @PrePersist
  public void prePersist(){
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    created = currentTime;
    updated = currentTime;
  }

  @PreUpdate
  public void preUpdate(){
    updated = new Timestamp(System.currentTimeMillis());
  }

  public Student() {
  }

  public Student(CreateStudentRequest createStudentRequest){
    this.ucid = createStudentRequest.getUcid();
    this.name = createStudentRequest.getName();
    this.surname = createStudentRequest.getSurname();
    this.dob = createStudentRequest.getDob();
    this.address = createStudentRequest.getAddress();
    this.email = createStudentRequest.getEmail();
    this.phone = createStudentRequest.getPhone();
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


  public java.sql.Date getDob() {
    return dob;
  }

  public void setDob(java.sql.Date dob) {
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


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }


  public java.sql.Timestamp getUpdated() {
    return updated;
  }

  public void setUpdated(java.sql.Timestamp updated) {
    this.updated = updated;
  }


  public List<Faculty> getFacultyList() {
    return facultyList;
  }

  public void setFacultyList(List<Faculty> facultyList) {
    this.facultyList = facultyList;
  }

  @Override
  public String toString() {
    return "Student{" +
            "ucid='" + ucid + '\'' +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", dob=" + dob +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", created=" + created +
            ", updated=" + updated +
            ", facultyList=" + facultyList +
            '}';
  }
}
