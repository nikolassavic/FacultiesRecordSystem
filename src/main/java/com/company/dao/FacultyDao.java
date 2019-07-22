package com.company.dao;

import com.company.util.HibernateUtil;
import com.company.entity.Faculty;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FacultyDao {
    @Transactional
    public Faculty createFaculty(Faculty requestFaculty){
        Faculty faculty = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(requestFaculty);
            faculty = requestFaculty;

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return faculty;
    }

    @Transactional
    public Faculty updateFaculty(Faculty requestFaculty) {
        Faculty faculty = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(requestFaculty);
            faculty = requestFaculty;

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return faculty;
    }

    @Transactional
    public Faculty getById(int id){
        Faculty faculty = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            faculty = session.get(Faculty.class, id);

            transaction.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return faculty;
    }

    @Transactional
    public List<Faculty> getAllFaculties() {
        List<Faculty> facultyList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            facultyList = session.createQuery("from Faculty order by name asc", Faculty.class).getResultList();

            transaction.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return facultyList;
    }

    @Transactional
    public void deleteFaculty(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(session.load(Faculty.class, id));

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
