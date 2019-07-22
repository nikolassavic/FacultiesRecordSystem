package com.company.dao;

import com.company.entity.Faculty;
import com.company.entity.Student;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {
    @Transactional
    public void createStudent(Student requestStudent, Faculty faculty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(requestStudent);
            faculty.getStudentList().add(requestStudent);
            session.merge(faculty);

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Transactional
    public Student updateStudent(Student requestStudent) {
        Student student = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(requestStudent);
            student = requestStudent;

            transaction.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return student;
    }

    @Transactional
    public Student getById(String ucid) {
        Student student = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            student = session.get(Student.class, ucid);

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return student;
    }

    @Transactional
    public List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            studentList = session.createQuery("from Student", Student.class).getResultList();

            transaction.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return studentList;
    }

    @Transactional
    public void addFacultyToStudent(Faculty faculty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(faculty);

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Transactional
    public void deleteStudent(String ucid){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.delete(session.load(Student.class, ucid));

            transaction.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
    }
}
