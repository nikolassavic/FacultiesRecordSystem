package com.company.entity;

import com.company.entity.dto.request.CreateFacultyRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faculties")
@DynamicUpdate
public class Faculty{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String name;
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

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "faculties_students",
          joinColumns = {@JoinColumn(name = "faculty_id")},
          inverseJoinColumns = {@JoinColumn(name = "student_ucid")})
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @JsonBackReference
  private List<Student> studentList = new ArrayList<>();

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

  public Faculty() {
  }

  public Faculty(CreateFacultyRequest createFacultyRequest){
    this.name = createFacultyRequest.getName();
    this.address = createFacultyRequest.getAddress();
    this.email = createFacultyRequest.getEmail();
    this.phone = createFacultyRequest.getPhone();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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


  public List<Student> getStudentList() {
    return studentList;
  }

  public void setStudentList(List<Student> studentList) {

    this.studentList = studentList;
  }

  @Override
  public String toString() {
    return "Faculty{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", created=" + created +
            ", updated=" + updated +
            '}';
  }
}
