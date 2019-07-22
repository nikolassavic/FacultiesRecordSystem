package com.company.dao;

import com.company.entity.Client;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class ClientDao {

    @Transactional
    public Client getByUserName(String username){
        Client client = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("from Client where username like :username", Client.class);
            query.setParameter("username", username);
            client = (Client) query.getSingleResult();

            transaction.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
        return client;
    }
}
